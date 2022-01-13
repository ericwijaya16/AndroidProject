package com.example.consumerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.consumerapp.API.APIUser;
import com.example.consumerapp.API.RetrofitClient;
import com.example.consumerapp.Adapter.SectionPagerAdapter;
import com.example.consumerapp.Model.UserDetailModel;
import com.example.consumerapp.Model.UserFavModel;
import com.example.consumerapp.Model.UserSearchModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    private UserDetailModel detailModel;
    private UserSearchModel searchModel;
    private UserFavModel userFavModel;
    private TextView txtName, txtUsername, txtLocation, txtUrl;
    private ImageView imgDetail;
    boolean statusFavorite = false;

    public static final String EXTRA_NOTE = "extra_note";

    @StringRes
    private final int[] titles= new int[]{
            R.string.tab1,
            R.string.tab2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imgDetail = findViewById(R.id.imgUserDetail);
        txtUsername = findViewById(R.id.txtDetailUser);
        txtName = findViewById(R.id.txtDetailName);
        txtLocation = findViewById(R.id.txtDetailLocation);
        txtUrl = findViewById(R.id.tempUrl);

        getSupportActionBar().setTitle("Detail User");

        searchModel = getIntent().getParcelableExtra("data_user");
        userFavModel = getIntent().getParcelableExtra(EXTRA_NOTE);

        checkData();

    }

    private void displayFragment(){
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(this);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(sectionPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(getResources().getString(titles[position]))).attach();
    }

    private void checkData(){
        if(userFavModel!=null){
            setFav();
            statusFavorite = true;

        } else{
            setDataDetail();
        }

    }

    private void setDataDetail(){
        final ProgressDialog progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setMessage(getString(R.string.progressMessage));
        progressDialog.show();

        RetrofitClient retrofitClient = new RetrofitClient();
        APIUser api = retrofitClient.getRetrofit().create(APIUser.class);

        Call<UserDetailModel> call = api.getDetailUser(searchModel.getLogin());
        call.enqueue(new Callback<UserDetailModel>() {
            @Override
            public void onResponse(Call<UserDetailModel> call, Response<UserDetailModel> response) {

                detailModel = response.body();
                txtName.setText(detailModel.getName());
                txtLocation.setText(detailModel.getLocation());
                txtUrl.setText(searchModel.getAvatar_url());
                Glide.with(DetailActivity.this)
                        .load(searchModel.getAvatar_url())
                        .into(imgDetail);
                txtUsername.setText(searchModel.getLogin());
                displayFragment();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<UserDetailModel> call, Throwable t) {

            }
        });

    }
    private void setFav(){
        Intent intent = getIntent();
        UserFavModel userFavModel = intent.getParcelableExtra(EXTRA_NOTE);
        Glide.with(DetailActivity.this).load(userFavModel.getImage()).into(imgDetail);
        txtUrl.setText(userFavModel.getImage());
        txtUsername.setText(userFavModel.getUsername());
        txtName.setText((userFavModel.getName()));
        txtLocation.setText((userFavModel.getLocation()));
        displayFragment();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.language_setting){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        } else if(item.getItemId() == R.id.favorite){
            Intent intent = new Intent(DetailActivity.this, FavoriteActivity.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.reminder){
            Intent intent = new Intent(DetailActivity.this, AlarmActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}