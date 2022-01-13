package com.example.ezyfoodbinus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    private ImageButton button, buttonDrink, buttonSnack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (ImageButton) findViewById(R.id.buttonFood);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFood();
            }
        });

        buttonDrink = (ImageButton) findViewById(R.id.buttonDrink);
        buttonDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrink();
            }
        });

        buttonSnack = (ImageButton) findViewById(R.id.buttonSnack);
        buttonSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSnack();
            }
        });
    }
    public void openFood(){
        Intent intent = new Intent(this, FoodActivity.class);
        startActivity(intent);
    }

    public void openDrink(){
        Intent intent = new Intent(this, DrinkActivity.class);
        startActivity(intent);
    }

    public void openSnack(){
        Intent intent = new Intent(this, SnackActivity.class);
        startActivity(intent);
    }
}
