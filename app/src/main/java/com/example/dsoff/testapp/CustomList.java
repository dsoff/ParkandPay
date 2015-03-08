package com.example.dsoff.testapp;

/**
 * Created by Dsoff on 2/8/15.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CustomList extends ArrayAdapter<String> {
    private final Context context;
    private final Bundle info;
    private final List<String> values;

    public  CustomList(Context context, Bundle info, List<String> values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.info = info;
        this.values = values;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        Integer pos = position;
        Bundle temp_spot = (Bundle) info.get(pos.toString());

        //SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        String str = dateFormat.format(((Calendar) temp_spot.get("time")).getTime());


        if (temp_spot.getBoolean("gets_ticket")) {
            imageView.setImageResource(R.drawable.red_x);
            textView.setText(temp_spot.getString("name") + " is unpaid for!" );
            textView.setTextColor(Color.RED);
        }
        else {
            imageView.setImageResource(R.drawable.green_check);
            textView.setText(temp_spot.getString("name") + " is paid for until " + str);
            textView.setTextColor(Color.BLUE);
        }

        return rowView;
    }
}
