package com.example.amafood.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amafood.R;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder> {

    List<CategoryListItemsDataClass> categoryList;
    Context context;

    public CategoryRecyclerViewAdapter(Context context, List<CategoryListItemsDataClass> categoryListItemDataClasses) {
        this.context = context;
        this.categoryList = categoryListItemDataClasses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_items, parent, false);
        MyViewHolder recycle = new MyViewHolder(view);
        return recycle;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.tvTitle.setText(categoryList.get(position).getTitle());
        Glide.with(context).load(categoryList.get(position).getImg()).into(holder.imgCategory);

        holder.btnGoToCategoryFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = categoryList.get(position).getTitle();
                Bundle bundle = new Bundle();
                bundle.putString("title",title);
                Navigation.findNavController(v).navigate(R.id.action_categoryFragment_to_categoryFoodsListItems,bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imgCategory;
        CardView btnGoToCategoryFoods;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            imgCategory = itemView.findViewById(R.id.img_category);
            btnGoToCategoryFoods = itemView.findViewById(R.id.btn_go_to_categories_foods);
        }
    }
}
