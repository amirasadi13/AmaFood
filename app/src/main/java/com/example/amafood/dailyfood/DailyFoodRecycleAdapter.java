package com.example.amafood.dailyfood;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amafood.R;

import java.util.List;

public class DailyFoodRecycleAdapter extends RecyclerView.Adapter<DailyFoodRecycleAdapter.MyViewHolder> {

    List<DailyFoodInfoDataClass> list;
    Context context;

    public DailyFoodRecycleAdapter(List<DailyFoodInfoDataClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.daily_food_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tvFoodName.setText(list.get(position).getMealName());
        holder.tvFoodCategory.setText("Category :" + " " + list.get(position).getCategory());
        holder.tvFoodArea.setText("Area :" + " " + list.get(position).getArea());
        holder.tvFoodTags.setText("#" + list.get(position).getTag());
        Glide.with(context).load(list.get(position).getMealImg()).into(holder.imgFood);
        holder.btnFoodInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = list.get(position).getMealName();
                Bundle bundle = new Bundle();
                bundle.putString("mealName",foodName);
                Navigation.findNavController(v).navigate(R.id.action_dailyFoodFragment_to_foodsInformation,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView tvFoodName;
        TextView tvFoodCategory;
        TextView tvFoodArea;
        TextView tvFoodTags;
        TextView btnFoodInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnFoodInfo = itemView.findViewById(R.id.btn_daily_food_go_to_food_info);
            imgFood = itemView.findViewById(R.id.img_daily_food);
            tvFoodName = itemView.findViewById(R.id.tv_daily_food_name);
            tvFoodCategory = itemView.findViewById(R.id.tv_daily_food_category);
            tvFoodArea = itemView.findViewById(R.id.tv_daily_food_area);
            tvFoodTags = itemView.findViewById(R.id.tv_daily_food_tag);
        }
    }
}
