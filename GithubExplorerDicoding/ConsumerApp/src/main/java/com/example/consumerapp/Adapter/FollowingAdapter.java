package com.example.consumerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.consumerapp.Model.FollowingModel;
import com.example.consumerapp.R;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder> {

    private Context context;
    private ArrayList<FollowingModel> list;

    public FollowingAdapter(Context context, ArrayList<FollowingModel> listFollowing) {
        this.context = context;
        this.list = listFollowing;
    }
    @NonNull
    @Override
    public FollowingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_following, parent, false);
        return new FollowingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingViewHolder holder, int position) {
        holder.txtUsernameFollowing.setText(list.get(position).getLogin());
        Glide.with(context)
                .load(list.get(position).getAvatar_url())
                .into(holder.imgFollowing);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class FollowingViewHolder extends RecyclerView.ViewHolder {
        TextView txtUsernameFollowing;
        ImageView imgFollowing;

        public FollowingViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsernameFollowing = itemView.findViewById(R.id.txtUsernameFollowing);
            imgFollowing = itemView.findViewById(R.id.imgFollowing);

        }
    }
}
