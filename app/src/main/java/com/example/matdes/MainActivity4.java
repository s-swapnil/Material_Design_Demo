package com.example.matdes;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;

import com.appyvet.materialrangebar.RangeBar;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class MainActivity4 extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    AppCompatSeekBar seekBar;
    TextView seekbarvalue,rangebarvalue, rangebarRight;
    RangeBar rangeBar1;
    TextView timeText,dateText;
    AppCompatButton timebtn,datebtn;
    Calendar calendar;
    Boolean time24hrMode=false;
    com.wdullaer.materialdatetimepicker.time.TimePickerDialog timePickerDialog;
    TimePickerDialog finalPicker;
    DatePickerDialog datePickerDialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        seekBar=findViewById(R.id.seekbar_id);
        seekbarvalue=findViewById(R.id.seekbar_txt);
        rangeBar1=findViewById(R.id.rangebar1_id);
        rangebarvalue=findViewById(R.id.rangebar_txt);
        rangebarRight =findViewById(R.id.rangebar_txt2);
        seekBar.setProgress(0);
        //seekBar.setKeyProgressIncrement(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarvalue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rangeBar1.setSeekPinByIndex((int)rangeBar1.getTickEnd());

        rangeBar1.setPinColor(getResources().getColor(R.color.colorAccent));
        rangeBar1.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
rangebarvalue.setText(String.valueOf(leftPinValue));
rangebarRight.setText(String.valueOf(rightPinValue));
            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }
        });

        timebtn=findViewById(R.id.timeBtnid);
        timeText=findViewById(R.id.timeTxtView);

        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
calendar=Calendar.getInstance();
int currentHour=calendar.get(Calendar.HOUR_OF_DAY);
int currentMinute=calendar.get(Calendar.MINUTE);
int currentSecond=calendar.get(Calendar.SECOND);
                timePickerDialog= com.wdullaer.materialdatetimepicker.time.TimePickerDialog.newInstance(MainActivity4.this,currentHour,currentMinute
                        ,currentSecond,time24hrMode);
                timePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimary));
                timePickerDialog.setTitle("Time Picker");
                //timePickerDialog.setThemeDark(true);
                timePickerDialog.dismissOnPause(false);
                timePickerDialog.setOkText("SET");
                timePickerDialog.enableSeconds(true);
                timePickerDialog.show(getSupportFragmentManager(),"timePicker");
            }
        });

//        finalPicker=(TimePickerDialog) getSupportFragmentManager().findFragmentByTag("timePicker");
//
//        if (finalPicker!=null)
//        {
//            finalPicker.setOnTimeSetListener(this);
//        }


        datebtn=findViewById(R.id.dateBtnid);
        dateText=findViewById(R.id.dateTxtView);
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar=Calendar.getInstance();
                int currentDay=calendar.get(Calendar.DAY_OF_MONTH);
                int currentMonth=calendar.get(Calendar.MONTH);
                int currentYear=calendar.get(Calendar.YEAR);
                datePickerDialog= com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(MainActivity4.this,currentYear,currentMonth,currentDay);
                datePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimary));
                datePickerDialog.setTitle("Date Picker");
                datePickerDialog.setOkText("SET");
                datePickerDialog.dismissOnPause(false);
                datePickerDialog.show(getSupportFragmentManager(),"datePicker");

            }
        });
    }


    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        String hour= hourOfDay<10? "0"+hourOfDay: ""+hourOfDay;
        String min= minute<10? "0"+minute: ""+minute;
        String sec= second<10? "0"+second: ""+second;

        String time="You picked the following time : "+hour+" h "+min+" m "+sec+" s";
        timeText.setText(time);


    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        String datestring="You picked the following date : "+dayOfMonth+" / "+monthOfYear+" / "+year;
        dateText.setText(datestring);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case (android.R.id.home): {
                Toast.makeText(getApplicationContext(), "HOMIE", Toast.LENGTH_LONG).show();
                finish();
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}