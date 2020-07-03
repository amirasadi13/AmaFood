package com.example.amafood.categoryfoods;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amafood.R;

import java.util.List;

public class CategoryFoodsListRecycleAdapter extends RecyclerView.Adapter<CategoryFoodsListRecycleAdapter.MyViewHolder> {

    List<CategoryFoodsItemsListDataClass> foodsList;
    Context context;

    public CategoryFoodsListRecycleAdapter(List<CategoryFoodsItemsListDataClass> foodsList, Context context) {
        this.foodsList = foodsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.foods_list_items, parent, false);
        MyViewHolder recycle = new MyViewHolder(view);
        return recycle;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tvFoodName.setText(foodsList.get(position).getMealName());
        Glide.with(context).load(foodsList.get(position).getImg()).into(holder.imgFood);
        holder.btnGoFoodInfoPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = foodsList.get(position).getMealName();
                Bundle bundle = new Bundle();
                bundle.putString("mealName",name);
                Navigation.findNavController(v).navigate(R.id.action_categoryFoodsListItems_to_foodsInformation,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView btnGoFoodInfoPage;
        TextView tvFoodName;
        ImageView imgFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnGoFoodInfoPage = itemView.findViewById(R.id.btn_go_to_food_info);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            imgFood = itemView.findViewById(R.id.img_food);
        }
    }

}
