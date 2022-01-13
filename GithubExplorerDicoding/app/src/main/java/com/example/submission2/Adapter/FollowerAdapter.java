package com.example.submission2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission2.Model.FollowerModel;
import com.example.submission2.R;

import java.util.ArrayList;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder> {

    private Context context;
    private ArrayList<FollowerModel> list;

    public FollowerAdapter(Context context, ArrayList<FollowerModel> listFollower) {
        this.context = context;
        this.list = listFollower;
    }
    @NonNull
    @Override
    public FollowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_follower, parent, false);
        return new FollowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerViewHolder holder, int position) {
        holder.txtUsernameFollower.setText(list.get(position).getLogin());
        Glide.with(context)
                .load(list.get(position).getAvatar_url())
                .into(holder.imgFollower);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class FollowerViewHolder extends RecyclerView.ViewHolder {
        TextView txtUsernameFollower;
        ImageView imgFollower;

        public FollowerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsernameFollower = itemView.findViewById(R.id.txtUsernameFollower);
            imgFollower = itemView.findViewById(R.id.imgFollower);

        }
    }
}
