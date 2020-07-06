package com.example.amafood.foodinfo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavAction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amafood.R;
import com.example.amafood.categoryfoods.CategoryFoodsItemsListDataClass;

import java.util.List;

public class MoreFoodsRecycleAdapter extends RecyclerView.Adapter<MoreFoodsRecycleAdapter.MyViewHolder> {

    List<CategoryFoodsItemsListDataClass> list;
    Context context;

    public MoreFoodsRecycleAdapter(List<CategoryFoodsItemsListDataClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.more_foods_items_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tvName.setText(list.get(position).getMealName());
        Glide.with(context).load(list.get(position).getImg()).into(holder.imgFood);
        holder.btnCheckFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = list.get(position).getMealName();
                Boolean goneRecycle = true;
                Bundle bundle = new Bundle();
                bundle.putString("mealName",name);
                bundle.putBoolean("goneRecycle",goneRecycle);
                Navigation.findNavController(v).navigate(R.id.action_foodsInformation_to_foodsInformation2,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView tvName;
        LinearLayout btnCheckFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCheckFood = itemView.findViewById(R.id.btn_more_foods_item);
            imgFood = itemView.findViewById(R.id.img_more_foods);
            tvName = itemView.findViewById(R.id.tv_more_foods_name);
        }
    }
}
