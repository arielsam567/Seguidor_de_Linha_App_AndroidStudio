package com.example.seguidor_de_linha;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TesteTela extends AppCompatActivity {


    public static TextView ttt;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alert_dialog);
        ttt = findViewById(R.id.informacao);
    }


//    ///////////////////////////////////////////////////////////////////////////////////////
//    //Listner Botoao
//    public class BotaoListener implements View.OnTouchListener {
//        private String mensagem;
//        BotaoListener(String mensagem) {
//            super();
//            this.mensagem = mensagem;
//        }
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (isBtConnected) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    Message msg = Message.obtain();
//                    msg.obj = mensagem;
//                    sendSignal(mensagem);
//                }
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    Message msg = Message.obtain();
//                    msg.obj = "0";
//                    sendSignal("0");
//                }
//            }
//
//            return false;
//        }
//
//    }
//    ///////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////////////////////////////////////////////////////
//    //Bluetooth
//    private void sendSignal(String number) {
//        if (btSocket != null) {
//            try {
//                btSocket.getOutputStream().write(number.toString().getBytes());
//            } catch (IOException e) {
//                msg("Error");
//            }
//        }
//    }
//    private void Disconnect() {
//        if (btSocket != null) {
//            try {
//                btSocket.close();
//            } catch (IOException e) {
//                msg("Error");
//            }
//        }
//    }
//    private void msg(String s) {
//        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//    }
//    @SuppressLint("StaticFieldLeak")
//    private class ConnectBT extends AsyncTask<Void, Void, Void> {
//        private boolean ConnectSuccess = true;
//        @Override
//        protected void onPreExecute() {
//            progress = ProgressDialog.show(TesteTela.this, "Connecting...", "Please Wait!!!");
//        }
//        @Override
//        protected Void doInBackground(Void... devices) {
//            try {
//                if (btSocket == null || !isBtConnected) {
//                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
//                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
//                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
//                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
//                    btSocket.connect();
//                }
//            } catch (IOException e) {
//                ConnectSuccess = false;
//            }
//
//            return null;
//        }
//
//        @SuppressLint("SetTextI18n")
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            if (!ConnectSuccess) {
//                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
//                finish();
//            } else {
//                msg("Connected");
//                btnConnect.setText("Desconectar");
//                isBtConnected = true;
//            }
//
//            progress.dismiss();
//        }
//    }
//////////////////////////////////////////////////////////////////////////////////////
//    ////////////////////////////
//    //Recebe dados do bluetooth
//    private InputStream inStream;
//    private String read() {
//        String s = "";
//        try {
//            // Check if there are bytes available
//            if (inStream.available() > 0) {
//                // Read bytes into a buffer
//                byte[] inBuffer = new byte[1024];
//                int bytesRead = inStream.read(inBuffer);
//
//                // Convert read bytes into a string
//                s = new String(inBuffer, "ASCII");
//                s = s.substring(0, bytesRead);
//            }
//        } catch (Exception e) {
//            //Log.e(TAG, "Read failed!", e);
//        }
//        return s;
//    }
//    ///////////////////////////////////
//
//    String address = null;
//    private ProgressDialog progress;
//    BluetoothAdapter myBluetooth = null;
//    BluetoothSocket btSocket = null;
//    private boolean isBtConnected = false;
//    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //Elementos da activity
//    Button btnConnect, btnReconnect, btnUp, btnRight, btnDonw, btnLeft, btnStart, btnStop, btnReadSensors;
//    TextView txtStatus;
//
//    public void reference_elements() {
//        btnConnect = findViewById(R.id.buttonconnect);
//        btnReconnect = findViewById(R.id.buttonreconnect);
//        btnUp = findViewById(R.id.up);
//        btnRight = findViewById(R.id.right);
//        btnDonw = findViewById(R.id.donw);
//        btnLeft = findViewById(R.id.left);
//        btnStart = findViewById(R.id.sett);
//        btnStop = findViewById(R.id.readdata);
//        btnReadSensors = findViewById(R.id.read);
//        txtStatus = findViewById(R.id.txtStatus);
//    }
//    @SuppressLint("SetTextI18n")
//    public void setText_elements() {
//        btnConnect.setText("Conectar");
//        btnReconnect.setText("⟳");
//        btnUp.setText("↑");
//        btnRight.setText("→");
//        btnDonw.setText("↓");
//        btnLeft.setText("←");
//        btnStart.setText("►        Iniciar");
//        btnStop.setText("\uD83D\uDEAB         Parar");
//        btnReadSensors.setText("Ler sensores");
//    }
//    @SuppressLint("ClickableViewAccessibility")
//    public void setValueButton(){
//        btnUp.setOnTouchListener(new BotaoListener("8"));
//        btnRight.setOnTouchListener(new BotaoListener("6"));
//        btnDonw.setOnTouchListener(new BotaoListener("2"));
//        btnLeft.setOnTouchListener(new BotaoListener("4"));
//        btnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v) {
//                sendSignal("c");
//            }
//        });
//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v) {
//                sendSignal("b");
//            }
//        });
//        btnReadSensors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v) {
//                sendSignal("a");
//            }
//        });
//    }
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
