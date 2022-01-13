package com.example.ezyfoodbinus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SnackActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    private String snackName[], description[], price[];
    private int images [] = {R.drawable.fries, R.drawable.sausage, R.drawable.popcorn, R.drawable.fishball, R.drawable.nachos};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recyclerView = findViewById(R.id.recyclerViewFood);

        snackName = getResources().getStringArray(R.array.snack_list);
        description = getResources().getStringArray(R.array.desc_snack);
        price = getResources().getStringArray(R.array.price_snack);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, snackName, description, price, images);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
