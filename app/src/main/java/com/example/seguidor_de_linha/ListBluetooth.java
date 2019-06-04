package com.example.seguidor_de_linha;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

public class ListBluetooth extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter = null;

    private ProgressBar progressBar;

    private ArrayList<BluetoothDevice> bluetoothDispositivosPareados, bluetoothDispositivosEncontrados;

    private BluetoothListAdapter adapterPareados;
    private BluetoothListAdapter adapterEncontrados;

    public static final String PREFS_NAME_BlUETOOTH = "device";
    ListView lvBluetoothEncontrados;
    ListView lvBluetoothPareados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bluetooth);
//        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(!bluetoothAdapter.isEnabled()){
            Intent solicita = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(solicita, 1);
        }

        registerReceiver(receberInfo, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        registerReceiver(receberInfo, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));

        progressBar = findViewById(R.id.progressBar);
        lvBluetoothEncontrados = findViewById(R.id.lv_bluetooth_encontrados);
        lvBluetoothPareados = findViewById(R.id.lv_bluetooth_pareados);

        progressBar.setVisibility(View.INVISIBLE);

        bluetoothDispositivosPareados = new ArrayList<>();
        bluetoothDispositivosEncontrados = new ArrayList<>();

        adapterPareados = new BluetoothListAdapter(this,bluetoothDispositivosPareados);
        adapterEncontrados = new BluetoothListAdapter(this,bluetoothDispositivosEncontrados);

        lvBluetoothPareados.setAdapter(adapterPareados);
        lvBluetoothEncontrados.setAdapter(adapterEncontrados);

        setDynamicHeight(lvBluetoothEncontrados);
        setDynamicHeight(lvBluetoothPareados);

        if (!bluetoothAdapter.isEnabled()) {
            ligarBluetooth();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter.isEnabled()) {
                    iniciarBusca();
                } else {
                    Toast.makeText(getBaseContext(),getResources().getString(R.string.precisa_ativar_bluetooth), Toast.LENGTH_LONG).show();
                }
            }
        });

        lvBluetoothEncontrados.setOnItemClickListener(new CustomBtClickListener());
        lvBluetoothPareados.setOnItemClickListener(new CustomBtClickListener());
    }

    public class CustomBtClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            BluetoothDevice device = (BluetoothDevice) view.getTag();

            if(device != null){
                enviarDeviceEscolhido(device);
            } else {
                Toast.makeText(getBaseContext(), "Nullo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void setDynamicHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        //check adapter if null
        if (adapter == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = height + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();
    }

    public void enviarDeviceEscolhido(BluetoothDevice device){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("btDevName", device.getName());
        returnIntent.putExtra("btDevAddress", device.getAddress());
        SharedPreferences configDevice = getSharedPreferences(PREFS_NAME_BlUETOOTH, MODE_PRIVATE);
        SharedPreferences.Editor editorDevice = configDevice.edit();
        editorDevice.putString("btDevAddress", device.getAddress());
        editorDevice.putString("btDevName", device.getName());

        editorDevice.apply();


        setResult(RESULT_OK, returnIntent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        buscarDispositivosPareados();
        ordenarLista(bluetoothDispositivosPareados);
        ligarBluetooth();
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(receberInfo);
    }

    private void ordenarLista(ArrayList<BluetoothDevice> devices){
        //Comparador usado para ordenar uma lista em ordem alfabetica
        Comparator<BluetoothDevice> ALPHABETIC_ORDER_DEVICES = new Comparator<BluetoothDevice>() {
            @Override
            public int compare(@NonNull BluetoothDevice device1, @NonNull BluetoothDevice device2) {
                return device1.getName().compareTo(device2.getName());
            }
        };
        //Ordena a lista em ordem Alfabetica
        Collections.sort(devices, ALPHABETIC_ORDER_DEVICES);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.mn_list_bluetooth, menu);

        return true;
    }

    private void iniciarBusca() {
        Toast.makeText(getBaseContext(),getResources().getString(R.string.msg_searching_devices), Toast.LENGTH_SHORT).show();
        bluetoothDispositivosEncontrados.clear();
        lvBluetoothEncontrados.setAdapter(adapterEncontrados);
//        lvBluetoothEncontrados.
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(receberInfo, intentFilter);
        bluetoothAdapter.startDiscovery();
        progressBar.setVisibility(View.VISIBLE);
    }

    private void ligarBluetooth() {
        if(!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }
    }

    private BroadcastReceiver receberInfo = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

//            Message msg = Message.obtain();
            String action = intent.getAction();

            if(BluetoothDevice.ACTION_FOUND.equals(action)){

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //Log.d("TAG","entrou no action found");
                if(bluetoothDispositivosPareados.size() < 1) {
                    //Log.d("TAG","entrou no if size < 1");
                    bluetoothDispositivosEncontrados.add(device);
                    adapterEncontrados.notifyDataSetChanged();
                } else {
                    //Log.d("TAG","entrou no else flag");
                    boolean flag = true;
                    for(int i = 0; i< bluetoothDispositivosEncontrados.size(); i++) {
                        if(device.getAddress().equals(bluetoothDispositivosEncontrados.get(i).getAddress())) {
                            flag = false;
                        }
                    }
                    if(flag) {
                        //Log.d("TAG","entrou no if flag == true");
                        bluetoothDispositivosEncontrados.add(device);
                        adapterEncontrados.notifyDataSetChanged();
                    }
                }
                ordenarLista(bluetoothDispositivosEncontrados);
                setDynamicHeight(lvBluetoothEncontrados);

            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    };

    private void buscarDispositivosPareados() {
        Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();
        if(pairedDevice.size() > 0) {
            bluetoothDispositivosPareados.addAll(pairedDevice);
        }
        adapterPareados.notifyDataSetChanged();
        setDynamicHeight(lvBluetoothPareados);

    }
}
