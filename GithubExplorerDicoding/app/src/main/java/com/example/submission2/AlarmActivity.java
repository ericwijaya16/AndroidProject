package com.example.submission2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.submission2.Alarm.AlarmReceiver;

public class AlarmActivity extends AppCompatActivity {

    private AlarmReceiver alarm;
    Switch alarmSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        getSupportActionBar().setTitle(R.string.title_alarm);

        alarm = new AlarmReceiver();
        alarmSwitch = findViewById(R.id.alarmSwitch);

        SharedPreferences sharedPreferences = getSharedPreferences("checked", MODE_PRIVATE);
        alarmSwitch.setChecked(sharedPreferences.getBoolean("value",false));
        alarmSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = getSharedPreferences("checked", MODE_PRIVATE).edit();
            if(isChecked){
                editor.putBoolean("value",true);
                editor.apply();
                alarm.setDailyAlarm(AlarmActivity.this, AlarmReceiver.Daily, AlarmReceiver.time_daily, getString(R.string.daily));
            }else {
                editor.putBoolean("value", false);
                editor.apply();
                alarm.turnOffAlarm(AlarmActivity.this);
            }
        });
    }
}