package com.example.consumerapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consumerapp.API.APIUser;
import com.example.consumerapp.API.RetrofitClient;
import com.example.consumerapp.Adapter.FollowerAdapter;
import com.example.consumerapp.Model.FollowerModel;
import com.example.consumerapp.Model.UserFavModel;
import com.example.consumerapp.Model.UserSearchModel;
import com.example.consumerapp.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FollowerFragment extends Fragment {
    private static final String EXTRA_NOTE = "extra_note";
    private RecyclerView recyclerFollower;
    private ProgressBar progressBar;

    public FollowerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_follower, container, false);
    }

    @Override
    public void onViewCreated(@NotNull final View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        UserSearchModel userSearchModel = intent.getParcelableExtra("data_user");

        progressBar = view.findViewById(R.id.progressBarFollower);
        showProgress(true);
        recyclerFollower = view.findViewById(R.id.recyclerFollower);
        recyclerFollower.setLayoutManager(new LinearLayoutManager(view.getContext()));

        UserFavModel userFavModel = intent.getParcelableExtra(EXTRA_NOTE);
        if(userFavModel==null){
            getFollower(userSearchModel.getLogin());
        }else{
            getFollower(userFavModel.getUsername());
        }
    }

    void getFollower(String login){
        RetrofitClient retrofitClient = new RetrofitClient();
        APIUser api = retrofitClient.getRetrofit().create(APIUser.class);

        Call<List<FollowerModel>> call = api.getFollowerUser(login);
        call.enqueue(new Callback<List<FollowerModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<FollowerModel>> call, @NotNull Response<List<FollowerModel>> response) {
                ArrayList<FollowerModel> list = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        showProgress(false);
                        list.addAll(response.body());
                        Log.d("TAG RESULT", "onResponse: " +list.size());
                        recyclerFollower.setAdapter(new FollowerAdapter(getContext(), list));
                    }
                } else {
                    Toast.makeText(getContext(), R.string.failed, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<FollowerModel>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.failed+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgress(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}