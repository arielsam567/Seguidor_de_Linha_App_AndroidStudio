package com.example.seguidor_de_linha.ui.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seguidor_de_linha.Device;
import com.example.seguidor_de_linha.R;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


public class TelaJunior extends Fragment {


    private static final String ARG_SECTION_NUMBER = "section_number";
    private View root;
    private  static final String TAG = "";
    Button btnConnect, btnReconnect, btnUp, btnRight, btnDonw, btnLeft, btnStart, btnStop, btnReadSensors;
    TextView txtStatus;

    InputStream inStream; //to receive data
    String mAddress = ""; //bluetooth address
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final String ADDRESS = "Address";


    public static TelaJunior newInstance(int index, String address) {
        TelaJunior fragment = new TelaJunior();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putString(ADDRESS, address);
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
            mAddress = getArguments().getString(ADDRESS);
            if (mAddress != null) {
                new ConnectBT().execute(mAddress);
            }
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.tela_junior, container, false);
        reference_elements();
        setText_elements();
        setValueButton();
        btnReconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disconnect();
            }
        });
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (!isBtConnected) {
                    Intent it = new Intent(getContext(), Device.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(it);
                    getActivity().finish();
                } else {
                    Disconnect();
                    btnConnect.setText("Conectar");
                    isBtConnected = false;
                }
            }
        });
        return root;
    }

    /////////////////////////////////////////////////
    //Bluetooth
    private void sendSignal ( String number ) {
        if ( btSocket != null ) {
            try {
                btSocket.getOutputStream().write(number.getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }
    private String receiveData(){
        try {
             inStream = btSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = "";
        try {
            // Check if there are bytes available
            if (inStream.available() > 0) {
                // Read bytes into a buffer
                byte[] inBuffer = new byte[1024];
                int bytesRead = inStream.read(inBuffer);
                // Convert read bytes into a string
                s = new String(inBuffer, StandardCharsets.US_ASCII);
                s = s.substring(0, bytesRead);
            }
        } catch (Exception e) {
            Log.e(TAG, "Read failed!", e);
        }
        return s;
    }
    @SuppressLint("SetTextI18n")
    private void Disconnect () {
        if ( btSocket!=null ) {
            try {
                btnConnect.setText("Conectar");
                btSocket.close();
            } catch(IOException e) {
                msg("Erro ao desconectar");
            }
        }
    }
    @SuppressLint("StaticFieldLeak")
    private class ConnectBT extends AsyncTask<String, Void, Void> {
        private boolean ConnectSuccess = true;
        ProgressDialog progress;
        @Override
        protected  void onPreExecute () {
            progress = ProgressDialog.show(getContext(), "Connecting...", "Please Wait!!!");
        }
        @Override
        protected Void doInBackground (String... devices) {
            try {
                if ( btSocket==null || !isBtConnected && mAddress!=null ) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(mAddress);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }
            return null;
        }
        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute (Void result) {
            super.onPostExecute(result);
            if (!ConnectSuccess) {
                msg("Conecção falhou. Tente novamente.");
            } else {
                msg("Connected");
                btnConnect.setText("Desconectar");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
    /////////////////////////////////////////////////

    //////////////////////////////////
    //REFERENCING ELEMENTS
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
    }
    @SuppressLint("SetTextI18n")
    public void setText_elements() {
        txtStatus.setText("");
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
    @SuppressLint("ClickableViewAccessibility")
    public void setValueButton(){
        btnUp.setOnTouchListener(new btnListener("8"));
        btnRight.setOnTouchListener(new btnListener("6"));
        btnDonw.setOnTouchListener(new btnListener("2"));
        btnLeft.setOnTouchListener(new btnListener("4"));
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                sendSignal("c");
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                sendSignal("b");
            }
        });
        btnReadSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                sendSignal("a");
                txtStatus.setText(receiveData());
            }
        });
    }
    //////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////
    //Button
    public class btnListener implements View.OnTouchListener {
        private String data;
        btnListener(String mensagem) {
            super();
            this.data = mensagem;
        }
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (isBtConnected) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Message msg = Message.obtain();
                    msg.obj = data;
                    sendSignal(data);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Message msg = Message.obtain();
                    msg.obj = "0";
                    sendSignal("0");
                }
            }

            return false;
        }

    }
    //////////////////////////////////////////////////////////////////

    private void msg (String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }


}


