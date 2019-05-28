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
        return root;
    }

    Button btnConnect, btnReconnect, btnUp,btnRight, btnDonw, btnLeft, btnStart, btnStop,btnReadSensors;


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
}