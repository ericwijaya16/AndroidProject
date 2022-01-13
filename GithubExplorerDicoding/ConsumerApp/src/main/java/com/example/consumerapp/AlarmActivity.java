package com.example.consumerapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.consumerapp.Alarm.AlarmReceiver;

public class AlarmActivity extends AppCompatActivity {

    private AlarmReceiver alarmReceiver;
    Switch alarmSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        getSupportActionBar().setTitle(R.string.title_alarm);

        alarmReceiver = new AlarmReceiver();
        alarmSwitch = findViewById(R.id.alarmSwitch);

        SharedPreferences sharedPreferences = getSharedPreferences("checked", MODE_PRIVATE);
        alarmSwitch.setChecked(sharedPreferences.getBoolean("value",false));
        alarmSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences("checked", MODE_PRIVATE).edit();
            if(isChecked){
                editor.putBoolean("value",true);
                editor.apply();
                alarmReceiver.setDailyAlarm(AlarmActivity.this, AlarmReceiver.Daily, AlarmReceiver.time_daily, getString(R.string.daily));
            }else {
                editor.putBoolean("value", false);
                editor.apply();
                alarmReceiver.cancelAlarm(AlarmActivity.this);
            }
        });
    }
}