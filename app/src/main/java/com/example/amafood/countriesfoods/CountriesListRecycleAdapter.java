package com.example.amafood.countriesfoods;

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

public class CountriesListRecycleAdapter extends RecyclerView.Adapter<CountriesListRecycleAdapter.MyViewHolder> {

    List<CountryFoodsListItemsDataClass> countryFoodsList;
    Context context;

    public CountriesListRecycleAdapter(List<CountryFoodsListItemsDataClass> countryFoodsList, Context context) {
        this.countryFoodsList = countryFoodsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_foods_list_items_layout, parent, false);
        MyViewHolder recycle = new MyViewHolder(view);
        return recycle;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvFoodsName.setText(countryFoodsList.get(position).getMealName());
        Glide.with(context).load(countryFoodsList.get(position).getMealImg()).into(holder.imgFood);

        holder.btnGoToFoodInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mealName = countryFoodsList.get(position).getMealName();
                Bundle bundle = new Bundle();
                bundle.putString("mealName",mealName);
                Navigation.findNavController(v).navigate(R.id.action_countriesListFragment_to_foodsInformation,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryFoodsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout btnGoToFoodInfo;
        TextView tvFoodsName;
        ImageView imgFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnGoToFoodInfo = itemView.findViewById(R.id.btn_country_foods_to_food_search);
            tvFoodsName = itemView.findViewById(R.id.tv_country_foods_name);
            imgFood = itemView.findViewById(R.id.country_foods_img);
        }
    }
}
