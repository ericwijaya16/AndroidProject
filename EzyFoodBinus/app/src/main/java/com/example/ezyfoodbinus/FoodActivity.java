package com.example.ezyfoodbinus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class FoodActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private String foodName[], description[], price[];
    private int images [] = {R.drawable.chicken, R.drawable.rice, R.drawable.duck, R.drawable.noodle, R.drawable.steak};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recyclerView = findViewById(R.id.recyclerViewFood);

        foodName = getResources().getStringArray(R.array.food_list);
        description = getResources().getStringArray(R.array.desc);
        price = getResources().getStringArray(R.array.price);

        RecyclerAdapter  recyclerAdapter = new RecyclerAdapter(this, foodName, description, price, images);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
