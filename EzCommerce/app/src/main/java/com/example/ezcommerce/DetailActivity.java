package com.example.ezcommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    TextView txtTitle;
    TextView txtDesc;
    TextView txt;
    String title, desc, id, index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setSupportActionBar(findViewById(R.id.toolbarTemplate));
        txtTitle = findViewById(R.id.txtNamaDetail);
        txtDesc = findViewById(R.id.txtDescDetail);
        ImageView imgProd = (ImageView) findViewById(R.id.imgDetail);
        index = gettingID();

        if(index.equals("2")){
            imgProd.setImageResource(R.drawable.image1);
        }
        else if(index.equals("4")){
            imgProd.setImageResource(R.drawable.image2);
        }
        else if(index.equals("6")){
            imgProd.setImageResource(R.drawable.image3);
        }
        else if(index.equals("8")){
            imgProd.setImageResource(R.drawable.image4);
        }
        else if(index.equals("10")){
            imgProd.setImageResource(R.drawable.image5);
        }
        else if(index.equals("12")){
            imgProd.setImageResource(R.drawable.image6);
        }
        getData();
        setData();

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

    }
    private void getData(){
        if(getIntent().hasExtra("name") && getIntent().hasExtra("imgProduct") && getIntent().hasExtra("desc") && getIntent().hasExtra("id")){
            title = getIntent().getStringExtra("name");
            desc = getIntent().getStringExtra("desc");
            id = getIntent().getStringExtra("id");

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }

    }

    private String gettingID(){
        id = getIntent().getStringExtra("id");
        return id;
    }

    private void setData() {
        txtTitle.setText(title);
        txtDesc.setText(desc);

//        ImageView imgProd = (ImageView) findViewById(R.id.imgDetail);
//        if(id == "2"){
//            imgProd.setImageResource(R.drawable.image1);
//        }
//        else if(id == "4"){
//            imgProd.setImageResource(R.drawable.image2);
//        }
//        else if(id == "6"){
//            imgProd.setImageResource(R.drawable.image3);
//        }
//        else if(id == "8"){
//            imgProd.setImageResource(R.drawable.image4);
//        }
//        else if(id == "10"){
//            imgProd.setImageResource(R.drawable.image5);
//        }
//        else if(id == "12"){
//            imgProd.setImageResource(R.drawable.image6);
//        }
    }
}
