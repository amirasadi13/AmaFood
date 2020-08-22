package com.example.amafood.ingredients;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amafood.R;

import java.util.List;

public class IngredientSpinnerAdapter extends BaseAdapter {
    List<IngredientListItemsDataClass> list;
    Context context;
    LayoutInflater inflater;

    public IngredientSpinnerAdapter(List<IngredientListItemsDataClass> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String imageUrl = "https://www.themealdb.com/images/ingredients/"+list.get(position).getIngredientName()+".png";
        convertView = inflater.inflate(R.layout.ingredient_spinner_layout,null);
        TextView tvIngredientName = convertView.findViewById(R.id.tv_ingredient_name);
        ImageView imgIngredient = convertView.findViewById(R.id.img_ingredient);
        tvIngredientName.setText(list.get(position).getIngredientName());
        Glide.with(context).load(imageUrl).into(imgIngredient);
        return convertView;
    }
}
