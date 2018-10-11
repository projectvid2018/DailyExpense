package com.example.gmmha.dailyexpense.listview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gmmha.dailyexpense.R;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Item>{

    private Activity context;
    private  int id;
    ArrayList<Item> arrayList;

    public Adapter(@NonNull Activity context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);

        this.context = context;
        this.id = resource;
        this.arrayList = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if( convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(id,null);
        }
        Item item = arrayList.get(position);
        TextView dateView = convertView.findViewById(R.id.listViewDateId);
        TextView amountView = convertView.findViewById(R.id.listViewAmountId);
        TextView descriptionView = convertView.findViewById(R.id.listDescriptionDateId);


        dateView.setText(item.getDate());
        amountView.setText(item.getAmount());
        descriptionView.setText(item.getDescription());

        return convertView;
    }
}
