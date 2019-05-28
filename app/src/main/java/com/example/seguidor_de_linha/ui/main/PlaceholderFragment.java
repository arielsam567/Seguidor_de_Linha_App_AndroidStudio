package com.example.seguidor_de_linha.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.seguidor_de_linha.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends  Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    //private View root;

    public static PlaceholderFragmentTwo newInstance(int index) {
        PlaceholderFragmentTwo fragment = new PlaceholderFragmentTwo();
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

    Button btnConnect, btnReconnect, btnUp,btnRight, btnDonw, btnLeft, btnStart, btnStop,btnReadSensors;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View telaJunior = inflater.inflate(R.layout.tela_junior, container, false);
        btnConnect = telaJunior.findViewById(R.id.buttonconnect);
        btnReconnect =telaJunior. findViewById(R.id.buttonreconnect);
        btnUp = telaJunior.findViewById(R.id.up);
        btnRight = telaJunior.findViewById(R.id.right);
        btnDonw = telaJunior.findViewById(R.id.donw);
        btnLeft = telaJunior.findViewById(R.id.left);
        btnStart = telaJunior.findViewById(R.id.start);
        btnStop = telaJunior.findViewById(R.id.stop);
        btnReadSensors = telaJunior.findViewById(R.id.read);
        btnConnect.setText("Conectar");
        btnReconnect.setText("⟳");
        btnUp.setText("↑");
        btnRight.setText("→");
        btnDonw.setText("↓");
        btnLeft.setText("←");
        btnStart.setText("►        Iniciar");
        btnStop.setText("\uD83D\uDEAB         Parar");
        btnReconnect.setText("Ler sensores");

//        reference_elements();
//        setText_elements();
        return telaJunior;
    }


    public void reference_elements(){
//        btnConnect = findViewById(R.id.buttonconnect);
//        btnReconnect =root. findViewById(R.id.buttonreconnect);
//        btnUp = root.findViewById(R.id.up);
//        btnRight = root.findViewById(R.id.right);
//        btnDonw = root.findViewById(R.id.donw);
//        btnLeft = root.findViewById(R.id.left);
//        btnStart = root.findViewById(R.id.start);
//        btnStop = root.findViewById(R.id.stop);
//        btnReadSensors = root.findViewById(R.id.read);
    }

    public void setText_elements(){
//        btnConnect.setText("Conectar");
//        btnReconnect.setText("⟳");
//        btnUp.setText("↑");
//        btnRight.setText("→");
//        btnDonw.setText("↓");
//        btnLeft.setText("←");
//        btnStart.setText("►        Iniciar");
//        btnStop.setText("\uD83D\uDEAB         Parar");
//        btnReconnect.setText("Ler sensores");
    }
}

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.tela_junior, container, false);
//        Button iniciar = root.findViewById(R.id.iniciar);
//        Button parar = root.findViewById(R.id.parar);
//        Button ler = root.findViewById(R.id.ler);
//        iniciar.setText("►        Iniciar");
//        parar.setText("\uD83D\uDEAB         Parar");
//        ler.setText("Ler sensores");
//        return root;
//    }
//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });