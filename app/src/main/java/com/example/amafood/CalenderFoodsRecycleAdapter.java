package com.example.amafood;

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
import com.example.amafood.calender.food.datebase.CalenderFoodDataBase;
import com.example.amafood.calender.food.datebase.CalenderFoodDeo;
import com.example.amafood.calender.food.datebase.CalenderFoods;

import java.util.List;
import java.util.Set;

public class CalenderFoodsRecycleAdapter extends RecyclerView.Adapter<CalenderFoodsRecycleAdapter.MyViewHolder> {

    List<CalenderFoods> list;
    Context context;

    public CalenderFoodsRecycleAdapter(List<CalenderFoods> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.calender_foods_items_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tvFood.setText(list.get(position).getCalenderFoodName());
        Glide.with(context).load(list.get(position).getCalenderFoodImage()).into(holder.imgFood);

        holder.imgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("date", null);
                bundle.putBoolean("chooseFood", false);
                bundle.putString("mealName",list.get(position).getCalenderFoodName());
                Navigation.findNavController(v).navigate(R.id.action_homePageFragment_to_foodsInformation,bundle);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CalenderFoodDeo calenderFoodDeo = CalenderFoodDataBase.getInstance(v.getContext()).calenderFoodDeo();
                calenderFoodDeo.deleteFood(list.get(position));
                list.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView tvFood;
        ImageView btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.btn_delete_calender_food);
            imgFood = itemView.findViewById(R.id.img_calender_food);
            tvFood = itemView.findViewById(R.id.tv_calender_food_name);
        }
    }
}
