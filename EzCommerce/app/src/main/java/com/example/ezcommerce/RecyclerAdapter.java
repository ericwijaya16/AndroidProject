package com.example.ezcommerce;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context MyContext;
    private List<ModelProduct> productList;
    private OnNoteListener MyOnNoteListener;

    public RecyclerAdapter(Context myContext, List<ModelProduct> productList, OnNoteListener onNoteListener) {
        this.MyContext = myContext;
        this.productList = productList;
        this.MyOnNoteListener = onNoteListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(MyContext);
        view = layoutInflater.inflate(R.layout.product_item, parent, false );
        return new MyViewHolder(view, MyOnNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nama.setText(productList.get(position).getName());
        holder.type.setText(productList.get(position).getType());

        Glide.with(MyContext).load(productList.get(position).getImg()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nama;
        ImageView img;
        TextView type;
        OnNoteListener onNoteListener;


        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            nama = itemView.findViewById(R.id.txtNamaProduct);
            type = itemView.findViewById(R.id.txtCategory);
            img = itemView.findViewById(R.id.imgProduct);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
