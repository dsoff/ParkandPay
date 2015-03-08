package com.example.dsoff.testapp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dsoff.testapp.CustomList;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Dsoff on 2/24/15.
 */
public class CustomSpinner extends ArrayAdapter<ParseObject> {

    private Context context;

    private List<ParseObject> data;

    public CustomSpinner(Context context, int textViewResourceID, List<ParseObject> data) {
        super(context, textViewResourceID, data);
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public ParseObject getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setTextColor(Color.BLACK);
        textView.setText(data.get(position).getString("Lot_Name"));
        return textView;
    }

    @Override
    public  View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setTextColor(Color.BLACK);
        textView.setText(data.get(position).getString("Lot_Name"));
        return textView;
    }

}
