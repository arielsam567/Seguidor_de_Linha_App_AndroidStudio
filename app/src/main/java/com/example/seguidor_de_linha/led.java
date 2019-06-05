package com.example.seguidor_de_linha;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class led extends AppCompatActivity {


    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Elementos da activity
    Button btnConnect, btnReconnect, btnUp, btnRight, btnDonw, btnLeft, btnStart, btnStop, btnReadSensors;
    TextView txtStatus;

    public void reference_elements() {
        btnConnect = findViewById(R.id.buttonconnect);
        btnReconnect = findViewById(R.id.buttonreconnect);
        btnUp = findViewById(R.id.up);
        btnRight = findViewById(R.id.right);
        btnDonw = findViewById(R.id.donw);
        btnLeft = findViewById(R.id.left);
        btnStart = findViewById(R.id.start);
        btnStop = findViewById(R.id.stop);
        btnReadSensors = findViewById(R.id.read);
        txtStatus = findViewById(R.id.txtStatus);
    }

    @SuppressLint("SetTextI18n")
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
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);
        reference_elements();
        setText_elements();

        Intent newint = getIntent();
        address = newint.getStringExtra(device.EXTRA_ADDRESS);
        new ConnectBT().execute();

        btnUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isBtConnected) {
                    sendSignal("8");
                }
                return false;
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSignal("6");
            }
        });
        btnDonw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSignal("2");
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSignal("4");
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSignal("1");
            }
        });
        btnReconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disconnect();
            }
        });
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBtConnected) {
                    Intent intent = new Intent(led.this, device.class);
                    startActivity(intent);
                } else if (isBtConnected) {
                    Disconnect();
                    btnConnect.setText("Conectar");
                    isBtConnected = false;
                }
            }
        });

    }


    ///////////////////////////////////////////////////////////////////////////////////////
    //Bluetooth
    private void sendSignal(String number) {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write(number.toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void Disconnect() {
        if (btSocket != null) {
            try {
                btSocket.close();
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("StaticFieldLeak")
    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(led.this, "Connecting...", "Please Wait!!!");
        }

        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (btSocket == null || !isBtConnected) {
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

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected");
                btnConnect.setText("Desconectar");
                isBtConnected = true;
            }

            progress.dismiss();
        }
    }
////////////////////////////////////////////////////////////////////////////////////

}
