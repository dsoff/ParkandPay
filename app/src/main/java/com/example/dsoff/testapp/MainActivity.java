package com.example.dsoff.testapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.bluetooth.BluetoothClass;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;
import android.widget.TimePicker;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    public static Bundle spot_bundle = new Bundle();

    @Override
    protected void onStart() {
        super.onStart();
        //testLoadData();
//        Bundle spot = new Bundle();
//        Calendar spottime = Calendar.getInstance();
//        String name = "spot1!!!";
//        spot.putSerializable("time", spottime);
//        spot.putString("name", name);
//        Integer zero = 0;
//        spot_bundle.putBundle(zero.toString(), spot);
    }


    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Integer zero = 0;
//            ((Calendar) ((Bundle) spot_bundle.get(zero.toString())).getSerializable("time")).set(Calendar.HOUR_OF_DAY, hourOfDay);
//            ((Calendar) ((Bundle) spot_bundle.get(zero.toString())).getSerializable("time")).set(Calendar.MINUTE, minute);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "D3e1FmZef0xReoXZlWFVGgxIosiuUfusQ9jgHT7y", "FFUhtwW99qsT4ExUj2kepUhKVAibc3MsMJYJGf2x");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ChangeText(View view) {
        TextView tv = (TextView) findViewById(R.id.ScreenText);
        if (tv.getText() == getString(R.string.greg_good)) {
            tv.setText(getString(R.string.greg_sucks));
        }
        else {
            tv.setText(getString(R.string.greg_good));
        }

    }

    public void ChangeScreen(View view) {
        Intent intent = new Intent(this, SpotsTakenActivity.class);
        testLoadData();
        intent.putExtra("spot_bundle", spot_bundle);
        startActivity(intent);
    }

    public void ShowTimePicker(View view) {
        DialogFragment df = new TimePickerFragment();
        df.show(getFragmentManager(), "timePicker");
    }

    public void testLoadData() {
        // database call would go here

//        for (Integer i = 0; i < 15; i++) {
//            Bundle spot = new Bundle();
//            Calendar spottime = Calendar.getInstance();

/* --------- Set spot times to random data ---------------
            Random r1 = new Random();
            Random r2 = new Random();
            Random r3 = new Random();
            int hour = r1.nextInt(12 - 1) + 1;
            int min = r2.nextInt(59 - 1) + 1;
            int am = r3.nextInt();
            spottime.set(Calendar.HOUR, hour);
            spottime.set(Calendar.MINUTE, min);
            spottime.set(Calendar.AM_PM, (am%2));
            String name = i.toString();
            spot.putSerializable("time", spottime);
            spot.putString("name", name);
            spot_bundle.putBundle(i.toString(), spot);
*/

// Using Parse to populate spot times

        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParkingSpot");
        query.getInBackground("8LtXrXVxTo", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    Date spot_date = parseObject.getDate("PaidForUntil");
                    Bundle spot = new Bundle();
                    Calendar spottime = Calendar.getInstance();
                    spottime.setTime(spot_date);
                    spot.putSerializable("time", spottime);
                    spot.putString("name", parseObject.getString("SpotName"));
                    Integer zero = 0;
                    spot_bundle.putBundle(zero.toString(), spot);
                }
                else {

                }

            }
        });

        //}
    }


}

