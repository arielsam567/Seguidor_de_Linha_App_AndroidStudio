package com.example.seguidor_de_linha.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seguidor_de_linha.BluetoothThread;
import com.example.seguidor_de_linha.ListBluetooth;
import com.example.seguidor_de_linha.R;
import com.example.seguidor_de_linha.activity_test_bluetooth;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentOne extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private View root;

    public static PlaceholderFragmentOne newInstance(int index) {
        PlaceholderFragmentOne fragment = new PlaceholderFragmentOne();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.tela_junior, container, false);
        reference_elements();
        setText_elements();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothPadrao == null) {
                    showTextWithColorRed(getResources().getString(R.string.dispostivoNaoPossuiBluetooth));
                } else {
                    if(btt != null){
                        interromperBluetooth();
                    }else {
                        if (!bluetoothPadrao.isEnabled()) {
                            Intent novoIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivityForResult(novoIntent, REQUEST_ENABLE_BT);
                        } else {
                            listaDeDispositivos();
                        }
                    }
                }

            }
        };
        btnConnect.setOnClickListener(onClickListener);
        bluetoothPadrao = BluetoothAdapter.getDefaultAdapter();
        return root;
    }

    public void voltar(View view) {
        Intent intent = new Intent(getContext(), activity_test_bluetooth.class);
        startActivity(intent);
    }

    Button btnConnect, btnReconnect, btnUp,btnRight, btnDonw, btnLeft, btnStart, btnStop,btnReadSensors,trocaTela;
    TextView txtStatus;

    public void reference_elements(){
        btnConnect = root.findViewById(R.id.buttonconnect);
        btnReconnect =root. findViewById(R.id.buttonreconnect);
        btnUp = root.findViewById(R.id.up);
        btnRight = root.findViewById(R.id.right);
        btnDonw = root.findViewById(R.id.donw);
        btnLeft = root.findViewById(R.id.left);
        btnStart = root.findViewById(R.id.start);
        btnStop = root.findViewById(R.id.stop);
        btnReadSensors = root.findViewById(R.id.read);
        txtStatus = root.findViewById(R.id.txtStatus);
        trocaTela = root.findViewById(R.id.button3);
    }

    public void setText_elements(){
        btnConnect.setText("Conectar");
        btnReconnect.setText("⟳");
        btnUp.setText("↑");
        btnRight.setText("→");
        btnDonw.setText("↓");
        btnLeft.setText("←");
        btnStart.setText("►        Iniciar");
        btnStop.setText("\uD83D\uDEAB         Parar");
        btnReadSensors.setText("Ler sensores");
    }

    ///////////////////////////////////////////////
    //bluetooth
    BluetoothThread btt;
    private BluetoothAdapter bluetoothPadrao = null;
    public static final int SELECT_PAIRED_DEVICE = 2;
    public String deviceName;
    private static final int REQUEST_ENABLE_BT = 1;
    public static final String PREFS_NAME_BlUETOOTH = "device";
    Handler writeHandler;
    boolean imprimir = true;
    public static final int RESULT_OK = -1;
    public static final int MODE_PRIVATE = 0;



    public void listaDeDispositivos() {
        if (bluetoothPadrao.isEnabled()) {
            if (btt == null) {
                Intent searchPairedDevicesIntent = new Intent(getContext(), ListBluetooth.class);
                //Intent searchPairedDevicesIntent = new Intent(this, ListBluetooth.class);
                startActivityForResult(searchPairedDevicesIntent, SELECT_PAIRED_DEVICE);
            } else {
                interromperBluetooth();
            }
        }
    }

    public void interromperBluetooth(){
        if(btt != null){
            btnConnect.setText(getResources().getString(R.string.conectar));
            btt.interrupt();
            btt.disconnect();
            btt = null;
            txtStatus.setText(getResources().getString(R.string.state)
                    + " "
                    + getResources().getString(R.string.desconectado)
                    +	" de " +
                    deviceName);
        }
    }

//    public void connectButtonPressed(View v ) {
//        if (bluetoothPadrao == null) {
//            showTextWithColorRed(getResources().getString(R.string.dispostivoNaoPossuiBluetooth));
//        } else {
//            if(btt != null){
//                interromperBluetooth();
//            }else {
//                if (!bluetoothPadrao.isEnabled()) {
//                    Intent novoIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                    startActivityForResult(novoIntent, REQUEST_ENABLE_BT);
//                } else {
//                    listaDeDispositivos();
//                }
//            }
//        }
//    }

    public void reconect(View v) {
        if (!bluetoothPadrao.isEnabled()) {
            showTextWithColorRed(getResources().getString(R.string.ativeBluetooth));
        } else {
            btnConnect.setText(getResources().getString(R.string.cancel));
            try {
                interromperBluetooth();
//				Thread.sleep(1000);
                connectWithBluetooth(RESULT_OK);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public SharedPreferences getSharedPreferences(String name, int mode) {
        throw new RuntimeException("Stub!");
    }

    private void connectWithBluetooth(int resultCode) {
        SharedPreferences configDevice = getSharedPreferences(PREFS_NAME_BlUETOOTH, MODE_PRIVATE);
        if (resultCode == RESULT_OK) {
            if (btt == null) {
                deviceName = configDevice.getString("btDevName","");
                txtStatus.setText(getResources().getString(R.string.state)
                        + " "
                        + getResources().getString(R.string.conectando)
                        +	" " +
                        deviceName);

                btt = new BluetoothThread(getContext(),configDevice.getString("btDevAddress", ""), new Handler() {

                    @Override
                    public void handleMessage(Message message) {

                        String s = (String) message.obj;
                        String textoB[] = s.split(";");
                        // Do something with the message
                        switch (s) {
                            case "CONNECTED":
                                btnConnect.setText(getResources().getString(R.string.desconectar));
                                showTextWithColorRed(getResources().getString(R.string.conectado));
                                txtStatus.setText("Conectado em: " + deviceName);
                                break;
                            case "DISCONNECTED":
                                showTextWithColorRed(getResources().getString(R.string.desconectado));
                                interromperBluetooth();
                                break;
                            case "CONNECTION FAILED":
                                showTextWithColorRed(getResources().getString(R.string.falhaNaConexao));
                                interromperBluetooth();
                                break;
                            default:
                                loop:
                                for (int i = 0; i < textoB.length; i++) {
                                    switch (i) {
                                        case 0:
                                            //txtArduino01.setText(textoB[0]);
                                            break;
                                        case 1:
                                            // txtArduino02.setText(textoB[1]);
                                            break;
                                        case 2:
                                            // txtArduino03.setText(textoB[2]);
                                            break;
                                        case 3:
                                            //txtArduino04.setText(textoB[3]);
                                            break;
                                        case 4:
                                            //txtArduino05.setText(textoB[4]);
                                            break;
                                        case 5:
                                            //txtArduino06.setText(textoB[5]);
                                            break;
                                        case 6:
                                            //txtArduino07.setText(textoB[6]);
                                            break;
                                        default:
                                            if (imprimir) {
                                                showTextWithColorRed(getResources().getString(R.string.numerosMaximosDeLinhasUltrapassado));
                                                imprimir = false;
                                            }

                                            break loop;
                                    }
                                }
                                break;
                        }
                    }
                });
            }
            if(btt != null){
                // Get the handler that is used to send messages
                writeHandler = btt.getWriteHandler();
                // Run the thread
                btt.start();
                btnConnect.setText(getResources().getString(R.string.cancel));
            }
            // break;

        } else {
            showTextWithColorRed(getResources().getString(R.string.nenhumDispositivoSelecionado));
        }
    }

///////////////////////////////////////////////////////



    public void showTextWithColorRed(final String mensagem){
        Toast.makeText(getContext(), mensagem, Toast.LENGTH_SHORT).show();
//        Toast toast = Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT);
//        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
//        v.setTextColor(Color.RED);
//        toast.show();
    }

}