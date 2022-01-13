package com.example.consumerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        getSupportActionBar().hide();

        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(LoadingActivity.this, FavoriteActivity.class);
            startActivity(homeIntent);
            finish();
        },SPLASH_TIME_OUT);


    }
}