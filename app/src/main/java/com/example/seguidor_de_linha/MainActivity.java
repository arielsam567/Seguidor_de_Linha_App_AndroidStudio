package com.example.seguidor_de_linha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.seguidor_de_linha.ui.main.SectionsPagerAdapter;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editKd;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editKp;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT1;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT10;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT2;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT3;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT4;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT5;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT6;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT7;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT8;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editT9;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editThre;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editTimer;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editVmax;
import static com.example.seguidor_de_linha.ui.main.TelaDados.editVmin;


public class MainActivity extends AppCompatActivity {
    private static final String THRE =    "thre";
    private static final String VMAX =    "vmax";
    private static final String MIN =     "vmin";
    private static final String KP =      "kp";
    private static final String KD =      "kd";
    private static final String TIMER =   "timer";
    private static final String T1 =      "t1";
    private static final String T2 =      "t2";
    private static final String T3 =      "t3";
    private static final String T4 =      "t4";
    private static final String T5 =      "t5";
    private static final String T6 =      "t6";
    private static final String T7 =      "t7";
    private static final String T8 =      "t8";
    private static final String T9 =      "t9";
    private static final String T10 =     "t10";



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(VMAX,"5");
        outState.putString(THRE,editThre.getText().toString());
        outState.putString(MIN,editVmin.getText().toString());
        outState.putString(KP,editKp.getText().toString());
        outState.putString(KD,editKd.getText().toString());
        outState.putString(TIMER,editTimer.getText().toString());
        outState.putString(T1,editT1.getText().toString());
        outState.putString(T2,editT2.getText().toString());
        outState.putString(T3,editT3.getText().toString());
        outState.putString(T4,editT4.getText().toString());
        outState.putString(T5,editT5.getText().toString());
        outState.putString(T6,editT6.getText().toString());
        outState.putString(T7,editT7.getText().toString());
        outState.putString(T8,editT8.getText().toString());
        outState.putString(T9,editT9.getText().toString());
        outState.putString(T10,editT10.getText().toString());
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        editVmax.setText(savedInstanceState.getString(VMAX));
        editThre.setText(savedInstanceState.getString(THRE));
        editVmin.setText(savedInstanceState.getString(MIN));
        editKp.setText(savedInstanceState.getString(KP));
        editKd.setText(savedInstanceState.getString(KD));
        editTimer.setText(savedInstanceState.getString(TIMER));
        editT1.setText(savedInstanceState.getString(T1));
        editT2.setText(savedInstanceState.getString(T2));
        editT3.setText(savedInstanceState.getString(T3));
        editT4.setText(savedInstanceState.getString(T4));
        editT5.setText(savedInstanceState.getString(T5));
        editT6.setText(savedInstanceState.getString(T6));
        editT7.setText(savedInstanceState.getString(T7));
        editT8.setText(savedInstanceState.getString(T8));
        editT9.setText(savedInstanceState.getString(T9));
        editT10.setText(savedInstanceState.getString(T10));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent newint = getIntent();
        String address = newint.getStringExtra(Device.EXTRA_ADDRESS);
//http://xahlee.info/comp/cars_trains_airplanes_boats.html
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), address);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }



}