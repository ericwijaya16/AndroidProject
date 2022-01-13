package com.example.ezcommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.text.TextUtils.replace;

public class HomeActivity extends AppCompatActivity implements RecyclerAdapter.OnNoteListener {

    private static final int VERTICAL_ITEM_SPACE = 48;
    TextView txt;
    RecyclerView recyclerView;
    List<ModelProduct> productList;
//    HardcoverFragment HardcoverFrag = new HardcoverFragment();
//    KindleFragment KindleFrag = new KindleFragment();


//    @NonNull
//    @Override
//    public FragmentManager getSupportFragmentManager() {
//        return super.getSupportFragmentManager();
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        setSupportActionBar(findViewById(R.id.toolbarTemplate));

        recyclerView = findViewById(R.id.recyclerView);
        productList = new ArrayList<>();
        txt = findViewById(R.id.txtNama);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APICall api = retrofit.create(APICall.class);
        Call<ModelUser> call = api.getData();
        Call<JSRes> callProduct = api.getProduct();

        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {

                if(response.code() != 200) {
                    txt.setText("Check  the connection");
                    return;
                }

                String json = "";
                json = response.body().getNama();

                txt.append(json);

            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {

            }
        });
        callProduct.enqueue(new Callback<JSRes>() {
            @Override
            public void onResponse(Call<JSRes> call, Response<JSRes> response) {

                JSRes js = response.body();
                productList = new ArrayList<>(Arrays.asList(js.getProducts()));

                PutDataInRecycler(productList);

            }

            @Override
            public void onFailure(Call<JSRes> call, Throwable t) {

            }
        });

    }
    private void PutDataInRecycler(List<ModelProduct> productList){
        RecyclerAdapter adapter = new RecyclerAdapter(this, productList,this );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("name",productList.get(position).getName());
        intent.putExtra("imgProduct",productList.get(position).getImg());
        intent.putExtra("desc",productList.get(position).getDescription());
        intent.putExtra("id",productList.get(position).getId());
        startActivity(intent);

    }
}
