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
import com.example.consumerapp.Adapter.FollowingAdapter;
import com.example.consumerapp.Model.FollowingModel;
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


public class FollowingFragment extends Fragment {
    private static final String EXTRA_NOTE = "extra_note";
    private RecyclerView recyclerFollowing;
    private ProgressBar progressBar;

    public FollowingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(@NotNull final View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        UserSearchModel userSearchModel = intent.getParcelableExtra("data_user");

        progressBar = view.findViewById(R.id.progressBarFollowing);
        showProgress(true);

        recyclerFollowing = view.findViewById(R.id.recyclerFollowing);
        recyclerFollowing.setLayoutManager(new LinearLayoutManager(view.getContext()));

        UserFavModel userFavModel = intent.getParcelableExtra(EXTRA_NOTE);
        if(userFavModel==null){
            getFollowing(userSearchModel.getLogin());
        }else{
            getFollowing(userFavModel.getUsername());
        }
    }

    void getFollowing(String login){
        RetrofitClient retrofitClient = new RetrofitClient();
        APIUser api = retrofitClient.getRetrofit().create(APIUser.class);

        Call<List<FollowingModel>> call = api.getFollowingUser(login);
        call.enqueue(new Callback<List<FollowingModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<FollowingModel>> call, @NotNull Response<List<FollowingModel>> response) {
                ArrayList<FollowingModel> list = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        showProgress(false);
                        list.addAll(response.body());
                        Log.d("TAG RESULT", "onResponse: " +list.size());
                        recyclerFollowing.setAdapter(new FollowingAdapter(getContext(), list));
                    }
                } else {
                    Toast.makeText(getContext(), R.string.failed, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<FollowingModel>> call, Throwable t) {
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