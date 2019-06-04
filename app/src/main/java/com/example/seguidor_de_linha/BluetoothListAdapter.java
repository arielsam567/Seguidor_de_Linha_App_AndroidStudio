package com.example.seguidor_de_linha;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BluetoothListAdapter extends ArrayAdapter<BluetoothDevice> {

    public BluetoothListAdapter(Context context, ArrayList<BluetoothDevice> devices) {
        super(context, android.R.layout.simple_list_item_1, devices);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        BluetoothDevice device = getItem(position);

        if (convertView == null) {
            convertView =  LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView text = convertView.findViewById(android.R.id.text1);
        text.setText(device.getName()+"\n"+device.getAddress());
        text.setTag(device);

        // Return the completed view to render on screen
        return convertView;
    }

}
