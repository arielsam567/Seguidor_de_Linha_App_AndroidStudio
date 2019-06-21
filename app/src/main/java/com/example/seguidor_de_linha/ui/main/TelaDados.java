package com.example.seguidor_de_linha.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.seguidor_de_linha.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TelaDados extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private View root;

    //private TextView txtThre,txtVmax,txtVmin,txtKp,txtKd,txtTimer,txtT1,txtT2,txtT3,txtT4,txtT5,txtT6,txtT7,txtT8,txtT9,txtT10;
    public static EditText editThre, editVmax, editVmin, editKp, editKd, editTimer, editT1, editT2, editT3, editT4, editT5, editT6, editT7, editT8, editT9, editT10;
    private Button access, save;
    private  TextView tt;

    public static TelaDados newInstance(int index) {
        TelaDados fragment = new TelaDados();
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
        root = inflater.inflate(R.layout.tela_dados, container, false);
        reference_elements();
        setText_elements();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt.setText(String.valueOf(editVmax.getText()));
            }
        });



        return root;
    }



    @SuppressLint("CutPasteId")
    public void reference_elements() {
        tt = root.findViewById(R.id.textView5);
        editThre = root.findViewById(R.id.txtThre);
        editVmax = root.findViewById(R.id.txtVmax);
        editVmin = root.findViewById(R.id.txtVmin);
        editKp = root.findViewById(R.id.txtKp);
        editKd = root.findViewById(R.id.txtKd);
        editTimer = root.findViewById(R.id.txtTime);
        editT1 = root.findViewById(R.id.txtT1);
        editT2 = root.findViewById(R.id.txtT2);
        editT3 = root.findViewById(R.id.txtT3);
        editT4 = root.findViewById(R.id.txtT4);
        editT5 = root.findViewById(R.id.txtT5);
        editT6 = root.findViewById(R.id.txtT7);
        editT7 = root.findViewById(R.id.txtT8);
        editT8 = root.findViewById(R.id.txtT8);
        editT9 = root.findViewById(R.id.txtT9);
        editT10 = root.findViewById(R.id.txtT10);
        access = root.findViewById(R.id.buttonAccess);
        save = root.findViewById(R.id.buttonSave);
    }
    @SuppressLint("SetTextI18n")
    public void setText_elements() {
        editThre.setText("100");
        editVmax.setText("100");
        editVmin.setText("100");
        editKp.setText("55");
        editKd.setText("25");
        editTimer.setText("13000");
        editT1.setText("");
        editT2.setText("");
        editT3.setText("");
        editT4.setText("");
        editT5.setText("");
        editT6.setText("");
        editT7.setText("");
        editT8.setText("");
        editT9.setText("");
        editT10.setText("");
        save.setText("Salvar");
        access.setText("Acessar");
    }
}