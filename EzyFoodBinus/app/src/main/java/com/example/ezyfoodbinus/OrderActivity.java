package com.example.ezyfoodbinus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    ImageView foodImageOrder;
    TextView foodOrdertxt, foodPricetxt;
    String array1, array3;
    int imageFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        foodImageOrder = findViewById(R.id.foodImageOrder);
        foodOrdertxt = findViewById(R.id.foodOrdertxt);
        foodPricetxt = findViewById(R.id.foodPricetxt);

        getData();
        setData();

    }

    private void getData(){
        if(getIntent().hasExtra("imageFood") && getIntent().hasExtra("array1") &&
                getIntent().hasExtra("array3")){
            array1 = getIntent().getStringExtra("array1");
            array3 = getIntent().getStringExtra("array3");
            imageFood = getIntent().getIntExtra("imageFood", 1);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        foodOrdertxt.setText(array1);
        foodPricetxt.setText(array3);
        foodImageOrder.setImageResource(imageFood);
    }
}
