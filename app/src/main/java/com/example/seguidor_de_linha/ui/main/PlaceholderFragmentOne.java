package com.example.seguidor_de_linha.ui.main;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seguidor_de_linha.Device;
import com.example.seguidor_de_linha.R;

import java.io.IOException;
import java.util.UUID;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentOne extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private View root;
    Button btnConnect, btnReconnect, btnUp, btnRight, btnDonw, btnLeft, btnStart, btnStop, btnReadSensors, trocaTela;
    TextView txtStatus;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    public void reference_elements() {
        btnConnect = root.findViewById(R.id.buttonconnect);
        btnReconnect = root.findViewById(R.id.buttonreconnect);
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
    public void setText_elements() {
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
        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
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

        View.OnClickListener parear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Device.class);
                startActivity(intent);
            }
        };
        btnConnect.setOnClickListener(parear);


       // new ConnectBT().execute();

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                sendSignal("8");
            }
        });
        return root;
    }






    /////////////////////////////////////////////////


    private void sendSignal ( String number ) {
        if ( btSocket != null ) {
            try {
                btSocket.getOutputStream().write(number.toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void Disconnect () {
        if ( btSocket!=null ) {
            try {
                btSocket.close();
            } catch(IOException e) {
                msg("Error");
            }
        }

    }

    private void msg (String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;
        @Override
        protected  void onPreExecute () {
            progress = ProgressDialog.show(getContext(), "Connecting...", "Please Wait!!!");
        }
        @Override
        protected Void doInBackground (Void... devices) {
            try {
                if ( btSocket==null || !isBtConnected ) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }
            return null;
        }
        @Override
        protected void onPostExecute (Void result) {
            super.onPostExecute(result);
            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");

            } else {
                msg("Connected");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }



    /////////////////////////////////////////////////
}


