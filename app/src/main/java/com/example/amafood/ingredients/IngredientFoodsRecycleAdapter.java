package com.example.amafood.ingredients;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amafood.R;

import java.util.List;

public class IngredientFoodsRecycleAdapter extends RecyclerView.Adapter<IngredientFoodsRecycleAdapter.MyViewHolder> {

    List<IngredientFoodsListDataClass> list;
    Context context;

    public IngredientFoodsRecycleAdapter(List<IngredientFoodsListDataClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_foods_list_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvMealName.setText(list.get(position).getMealName());
        Glide.with(context).load(list.get(position).getMealImg()).into(holder.imageView);
        holder.btnGoToFoodInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = list.get(position).getMealName();
                Bundle bundle = new Bundle();
                bundle.putString("mealName",foodName);
                Navigation.findNavController(v).navigate(R.id.action_ingredientFoodsFragment_to_foodsInformation,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMealName;
        ImageView imageView;
        LinearLayout btnGoToFoodInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnGoToFoodInfo = itemView.findViewById(R.id.btn_ingredient_foods_to_food_search);
            imageView = itemView.findViewById(R.id.ingredient_foods_img);
            tvMealName = itemView.findViewById(R.id.tv_ingredient_food_name);
        }
    }
}
