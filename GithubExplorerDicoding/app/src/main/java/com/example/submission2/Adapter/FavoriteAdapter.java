package com.example.submission2.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission2.DetailActivity;
import com.example.submission2.Model.UserFavModel;
import com.example.submission2.R;
import com.example.submission2.Tools.CustomOnItemClickListener;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private final Activity activity;
    private final ArrayList<UserFavModel> userFavModel = new ArrayList<>();

    public FavoriteAdapter(Activity activity) {
        this.activity = activity;
    }


    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView UsernameFavorite;
        ImageView ImageFavorite;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            UsernameFavorite = itemView.findViewById(R.id.txtUsernameUserFavorite);
            ImageFavorite = itemView.findViewById(R.id.imgUserFavorite);
        }
    }


    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        holder.UsernameFavorite.setText(getList().get(position).getUsername());
        Glide.with(holder.itemView).load(getList().get(position).getImage()).into(holder.ImageFavorite);
        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, (view, position1) -> {
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_NOTE, userFavModel.get(position1));
            activity.startActivity(intent);
        }));

    }

    @Override
    public int getItemCount() {
        return userFavModel.size();
    }

    public void setList(ArrayList<UserFavModel> list){
        if(userFavModel.size() > 0){
            this.userFavModel.clear();
        }
        this.userFavModel.addAll(list);
        notifyDataSetChanged();
    }


    public ArrayList<UserFavModel> getList(){
        return userFavModel;
    }

}
