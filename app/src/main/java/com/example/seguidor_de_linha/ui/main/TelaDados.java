package com.example.seguidor_de_linha.ui.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.seguidor_de_linha.Db.CreateDatabase;
import com.example.seguidor_de_linha.Db.DAL;
import com.example.seguidor_de_linha.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class TelaDados extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private View root;
    //private TextView txtThre, txtVmax, txtVmin, txtKp, txtKd, txtTimer, txtT1, txtT2, txtT3, txtT4, txtT5, txtT6, txtT7, txtT8, txtT9, txtT10;
    @SuppressLint("StaticFieldLeak")
    public static EditText editThre, editVmax, editVmin, editKp, editKd, editTimer, editT1, editT2, editT3, editT4, editT5, editT6, editT7, editT8, editT9, editT10;
    private Button btnDelete, btnSave;
    private ListView listData;


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
        root = inflater.inflate(R.layout.tela_database, container, false);
        reference_elements();
        setText_elements();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialogForDelete();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialogForDelete();
            }
        });
        db();
        return root;
    }

    public void db() {
        DAL dal = new DAL(getContext());
        Cursor cursor = dal.loadAll();
        String[] fields = new String[]{CreateDatabase.INFORMACAO, CreateDatabase.ID, CreateDatabase.THRESHOLD, CreateDatabase.VMAX,
                CreateDatabase.VMIN, CreateDatabase.KP, CreateDatabase.KD
                , CreateDatabase.TIMER, CreateDatabase.T1, CreateDatabase.T2, CreateDatabase.T3, CreateDatabase.T4, CreateDatabase.T5
                , CreateDatabase.T6, CreateDatabase.T7, CreateDatabase.T8, CreateDatabase.T9, CreateDatabase.T10, CreateDatabase.D1
                , CreateDatabase.D2, CreateDatabase.D3, CreateDatabase.D4};

        int[] ids = {R.id.tvInfo, R.id.tvId, R.id.tvThre, R.id.tvVmax, R.id.tvVmin, R.id.tvKp, R.id.tvKd, R.id.tvTimer, R.id.tvT1, R.id.tvT2, R.id.tvT3
                , R.id.tvT4, R.id.tvT5, R.id.tvT6, R.id.tvT7, R.id.tvT8, R.id.tvT9, R.id.tvT10, R.id.tvD1, R.id.tvD2, R.id.tvD3, R.id.tvD4};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),
                R.layout.lista_dados, cursor, fields, ids, 0);

        listData.setAdapter(adapter);

        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editThre.setText(((TextView) view.findViewById(R.id.tvThre)).getText().toString());
                editVmax.setText(((TextView) view.findViewById(R.id.tvVmax)).getText().toString());
                editVmin.setText(((TextView) view.findViewById(R.id.tvVmin)).getText().toString());
                editKp.setText(((TextView) view.findViewById(R.id.tvKp)).getText().toString());
                editKd.setText(((TextView) view.findViewById(R.id.tvKd)).getText().toString());
                editTimer.setText(((TextView) view.findViewById(R.id.tvTimer)).getText().toString());
                editT1.setText(((TextView) view.findViewById(R.id.tvT1)).getText().toString());
                editT2.setText(((TextView) view.findViewById(R.id.tvT2)).getText().toString());
                editT3.setText(((TextView) view.findViewById(R.id.tvT3)).getText().toString());
                editT4.setText(((TextView) view.findViewById(R.id.tvT4)).getText().toString());
                editT5.setText(((TextView) view.findViewById(R.id.tvT5)).getText().toString());
                editT6.setText(((TextView) view.findViewById(R.id.tvT6)).getText().toString());
                editT7.setText(((TextView) view.findViewById(R.id.tvT7)).getText().toString());
                editT8.setText(((TextView) view.findViewById(R.id.tvT8)).getText().toString());
                editT9.setText(((TextView) view.findViewById(R.id.tvT9)).getText().toString());
                editT10.setText(((TextView) view.findViewById(R.id.tvT10)).getText().toString());
            }
        });


    }

    public void saveInDb(String info) {
        DAL dal = new DAL(getContext());
        int thre = Integer.valueOf(editThre.getText().toString());
        int vmax = Integer.valueOf(editVmax.getText().toString());
        int vmin = Integer.valueOf(editVmin.getText().toString());
        int kp = Integer.valueOf(editKp.getText().toString());
        int kd = Integer.valueOf(editKd.getText().toString());
        int timer = Integer.valueOf(editTimer.getText().toString());
        int t1 = Integer.valueOf(editT1.getText().toString());
        int t2 = Integer.valueOf(editT2.getText().toString());
        int t3 = Integer.valueOf(editT3.getText().toString());
        int t4 = Integer.valueOf(editT4.getText().toString());
        int t5 = Integer.valueOf(editT5.getText().toString());
        int t6 = Integer.valueOf(editT6.getText().toString());
        int t7 = Integer.valueOf(editT7.getText().toString());
        int t8 = Integer.valueOf(editT8.getText().toString());
        int t9 = Integer.valueOf(editT9.getText().toString());
        int t10 = Integer.valueOf(editT10.getText().toString());
        int d1 = 1;
        int d2 = 2;
        int d3 = 3;
        int d4 = 4;


        if (dal.insert(info, thre, vmax, vmin, kp, kd, timer, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, d1, d2, d3, d4)) {
            Toast.makeText(getContext(), "Registro Inserido com sucesso!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Erro ao inserir registro!", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("CutPasteId")
    public void reference_elements() {
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
        editT6 = root.findViewById(R.id.txtT6);
        editT7 = root.findViewById(R.id.txtT7);
        editT8 = root.findViewById(R.id.txtT8);
        editT9 = root.findViewById(R.id.txtT9);
        editT10 = root.findViewById(R.id.txtT10);
        btnDelete = root.findViewById(R.id.buttonDelete);
        btnSave = root.findViewById(R.id.buttonSave);
        listData = root.findViewById(R.id.listViewDados);
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
        btnSave.setText("Salvar");
        btnDelete.setText("Deletar");
    }

    public void onCreateDialogForInsert() {
        final EditText edt = new EditText(getContext());
        edt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        AlertDialog.Builder altBx = new AlertDialog.Builder(getContext());
        altBx.setTitle("Banco de dados");
        altBx.setMessage("Deseja adicionar alguma informação?");
        altBx.setView(edt);

        altBx.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (edt.getText().toString().length() != 0) {
                    String teste = edt.getText().toString();
                    saveInDb(teste);
                } else {
                    saveInDb("");
                }
            }
        });
        altBx.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        altBx.show();
//                AlertDialog.Builder mensagem = new AlertDialog.Builder(getContext());
//                mensagem.setTitle("Adicionando ao Banco de Dados");
//                mensagem.setMessage("Deseja adicionar alguma informação?");
//                // add editview
//                final EditText input = new EditText(getContext());
//                mensagem.setView(input);
//                mensagem.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        try {
//                            String info = String.valueOf(input.getText());
//                            saveInDb(info);
//                        } catch (Exception e) {
//                            saveInDb(" ");
//                        }
//                    }
//                });
//                mensagem.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                mensagem.show();
//                // FORÇA O TECLADO APARECER AO ABRIR O ALERT
//                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void onCreateDialogForDelete() {
        final EditText edt = new EditText(getContext());
        edt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        AlertDialog.Builder altBx = new AlertDialog.Builder(getContext());
        altBx.setTitle("Banco de dados");
        altBx.setMessage("Indique o ID dos dados que deseja excluir");
        altBx.setView(edt);

        altBx.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (edt.getText().toString().length() != 0) {
                    int id = Integer.parseInt(edt.getText().toString());
                    DAL dal = new DAL(getContext());
                    dal.delete(id);
                } else {
                    Toast.makeText(getContext(), "Você esqueceu de inserir o ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
        altBx.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        altBx.show();
//                AlertDialog.Builder mensagem = new AlertDialog.Builder(getContext());
//                mensagem.setTitle("Adicionando ao Banco de Dados");
//                mensagem.setMessage("Deseja adicionar alguma informação?");
//                // add editview
//                final EditText input = new EditText(getContext());
//                mensagem.setView(input);
//                mensagem.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        try {
//                            String info = String.valueOf(input.getText());
//                            saveInDb(info);
//                        } catch (Exception e) {
//                            saveInDb(" ");
//                        }
//                    }
//                });
//                mensagem.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                mensagem.show();
//                // FORÇA O TECLADO APARECER AO ABRIR O ALERT
//                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}