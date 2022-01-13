package com.example.ezyfoodbinus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DrinkActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    private String drinkName[], description[], price[];
    private int images [] = {R.drawable.melons, R.drawable.mango, R.drawable.fanta, R.drawable.cocacola, R.drawable.tea};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recyclerView = findViewById(R.id.recyclerViewFood);

        drinkName = getResources().getStringArray(R.array.drink_list);
        description = getResources().getStringArray(R.array.desc_drink);
        price = getResources().getStringArray(R.array.price_drink);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, drinkName, description, price, images);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
