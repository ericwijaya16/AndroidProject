package com.example.ezyfoodbinus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import javax.xml.namespace.QName;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder1> {

    private String array1[], array2[], array3[];
    private int images1[];
    Context context;

    public RecyclerAdapter(Context ct, String foodName[], String desc[], String price[], int images[]){
            context = ct;
            array1 = foodName;
            array2 = desc;
            array3 = price;
            images1 = images;

    }
    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, final int position) {
            holder.text1.setText(array1[position]);
            holder.text2.setText(array2[position]);
            holder.text3.setText(array3[position]);
            holder.image1.setImageResource(images1[position]);

            holder.image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OrderActivity.class);
                    intent.putExtra("array1", array1[position]);
                    intent.putExtra("array3", array3[position]);
                    intent.putExtra("imageFood", images1[position]);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return images1.length;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView text1, text2, text3;
        ImageView image1;
        ConstraintLayout mainLayout;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.foodTitletxt);
            text2 = itemView.findViewById(R.id.foodDesctxt);
            text3 = itemView.findViewById(R.id.pricetxt);
            image1 = itemView.findViewById(R.id.foodImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
