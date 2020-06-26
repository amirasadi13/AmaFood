package com.example.amafood.countriesfoods;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.amafood.R;

import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {

    List<CountryListItemsDataClass> countryListItem;
    Context context;

    LayoutInflater inflater;
    public CustomSpinnerAdapter(List<CountryListItemsDataClass> countryListItem, Context context) {
        this.countryListItem = countryListItem;
        this.context = context;
        inflater = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return countryListItem.size();
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
        convertView = inflater.inflate(R.layout.countries_list_items_layout,null);
        TextView tvCountriesName = convertView.findViewById(R.id.tv_countries_name);
        tvCountriesName.setText(countryListItem.get(position).getArea());
        return convertView;
    }
}
