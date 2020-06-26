package com.example.amafood.foodinfo;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amafood.R;

import java.util.List;

public class FoodInfoRecycleAdapter extends RecyclerView.Adapter<FoodInfoRecycleAdapter.MyViewHolder> {


    List<FoodMaterialDataClass> foodInfo;
    Context context;


    public FoodInfoRecycleAdapter(List<FoodMaterialDataClass> foodInfo, Context context) {
        this.foodInfo = foodInfo;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_material_list, parent, false);
        MyViewHolder recycle = new MyViewHolder(view);
        return recycle;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        setIngStatus(position,holder);
        setMeasStatus(position,holder);

    }


    @Override
    public int getItemCount() {
        return foodInfo.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        VideoView vdFood;
        TextView tvMealName;
        TextView tvMealInstruction;
        ImageView imgFood;
        TextView tvCategory;
        TextView tvArea;
        TextView tvIng1;
        TextView tvIng2;
        TextView tvIng3;
        TextView tvIng4;
        TextView tvIng5;
        TextView tvIng6;
        TextView tvIng7;
        TextView tvIng8;
        TextView tvIng9;
        TextView tvIng10;
        TextView tvIng11;
        TextView tvIng12;
        TextView tvIng13;
        TextView tvIng14;
        TextView tvIng15;
        TextView tvIng16;
        TextView tvIng17;
        TextView tvIng18;
        TextView tvIng19;
        TextView tvIng20;
        TextView tvMeas1;
        TextView tvMeas2;
        TextView tvMeas3;
        TextView tvMeas4;
        TextView tvMeas5;
        TextView tvMeas6;
        TextView tvMeas7;
        TextView tvMeas8;
        TextView tvMeas9;
        TextView tvMeas10;
        TextView tvMeas11;
        TextView tvMeas12;
        TextView tvMeas13;
        TextView tvMeas14;
        TextView tvMeas15;
        TextView tvMeas16;
        TextView tvMeas17;
        TextView tvMeas18;
        TextView tvMeas19;
        TextView tvMeas20;
        LinearLayout l1;
        LinearLayout l2;
        LinearLayout l3;
        LinearLayout l4;
        LinearLayout l5;
        LinearLayout l6;
        LinearLayout l7;
        LinearLayout l8;
        LinearLayout l9;
        LinearLayout l10;
        LinearLayout l11;
        LinearLayout l12;
        LinearLayout l13;
        LinearLayout l14;
        LinearLayout l15;
        LinearLayout l16;
        LinearLayout l17;
        LinearLayout l18;
        LinearLayout l19;
        LinearLayout l20;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vdFood = itemView.findViewById(R.id.vd_food);
            tvMealInstruction = itemView.findViewById(R.id.food_instruction);
            imgFood = itemView.findViewById(R.id.img_food_material);
            tvMealName = itemView.findViewById(R.id.tv_meal_name);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvArea = itemView.findViewById(R.id.tv_area);
            tvIng1 = itemView.findViewById(R.id.ing1);
            tvIng2 = itemView.findViewById(R.id.ing2);
            tvIng3 = itemView.findViewById(R.id.ing3);
            tvIng4 = itemView.findViewById(R.id.ing4);
            tvIng5 = itemView.findViewById(R.id.ing5);
            tvIng6 = itemView.findViewById(R.id.ing6);
            tvIng7 = itemView.findViewById(R.id.ing7);
            tvIng8 = itemView.findViewById(R.id.ing8);
            tvIng9 = itemView.findViewById(R.id.ing9);
            tvIng10 = itemView.findViewById(R.id.ing10);
            tvIng11 = itemView.findViewById(R.id.ing11);
            tvIng12 = itemView.findViewById(R.id.ing12);
            tvIng13 = itemView.findViewById(R.id.ing13);
            tvIng14 = itemView.findViewById(R.id.ing14);
            tvIng15 = itemView.findViewById(R.id.ing15);
            tvIng16 = itemView.findViewById(R.id.ing16);
            tvIng17 = itemView.findViewById(R.id.ing17);
            tvIng18 = itemView.findViewById(R.id.ing18);
            tvIng19 = itemView.findViewById(R.id.ing19);
            tvIng20 = itemView.findViewById(R.id.ing20);
            tvMeas1 = itemView.findViewById(R.id.meas1);
            tvMeas2 = itemView.findViewById(R.id.meas2);
            tvMeas3 = itemView.findViewById(R.id.meas3);
            tvMeas4 = itemView.findViewById(R.id.meas4);
            tvMeas5 = itemView.findViewById(R.id.meas5);
            tvMeas6 = itemView.findViewById(R.id.meas6);
            tvMeas7 = itemView.findViewById(R.id.meas7);
            tvMeas8 = itemView.findViewById(R.id.meas8);
            tvMeas9 = itemView.findViewById(R.id.meas9);
            tvMeas10 = itemView.findViewById(R.id.meas10);
            tvMeas11 = itemView.findViewById(R.id.meas11);
            tvMeas12 = itemView.findViewById(R.id.meas12);
            tvMeas13 = itemView.findViewById(R.id.meas13);
            tvMeas14 = itemView.findViewById(R.id.meas14);
            tvMeas15 = itemView.findViewById(R.id.meas15);
            tvMeas16 = itemView.findViewById(R.id.meas16);
            tvMeas17 = itemView.findViewById(R.id.meas17);
            tvMeas18 = itemView.findViewById(R.id.meas18);
            tvMeas19 = itemView.findViewById(R.id.meas19);
            tvMeas20 = itemView.findViewById(R.id.meas20);

            l1 = itemView.findViewById(R.id.l_1);
            l2 = itemView.findViewById(R.id.l_2);
            l3 = itemView.findViewById(R.id.l_3);
            l4 = itemView.findViewById(R.id.l_4);
            l5 = itemView.findViewById(R.id.l_5);
            l6 = itemView.findViewById(R.id.l_6);
            l7 = itemView.findViewById(R.id.l_7);
            l8 = itemView.findViewById(R.id.l_8);
            l9 = itemView.findViewById(R.id.l_9);
            l10 = itemView.findViewById(R.id.l_10);
            l11 = itemView.findViewById(R.id.l_11);
            l12 = itemView.findViewById(R.id.l_12);
            l13 = itemView.findViewById(R.id.l_13);
            l14 = itemView.findViewById(R.id.l_14);
            l15 = itemView.findViewById(R.id.l_15);
            l16 = itemView.findViewById(R.id.l_16);
            l17 = itemView.findViewById(R.id.l_17);
            l18 = itemView.findViewById(R.id.l_18);
            l19 = itemView.findViewById(R.id.l_19);
            l20 = itemView.findViewById(R.id.l_20);
        }
    }

    private void setMeasStatus(int position, MyViewHolder holder) {

        if (foodInfo.get(position).getMeas1().contentEquals("") || foodInfo.get(position).getMeas1() == null){
            holder.tvMeas1.setVisibility(View.GONE);
        }else {
            holder.tvMeas1.setText(foodInfo.get(position).getMeas1());
        }
        if (foodInfo.get(position).getMeas2().contentEquals("") || foodInfo.get(position).getMeas2() == null){
            holder.tvMeas2.setVisibility(View.GONE);
        }else {
            holder.tvMeas2.setText(foodInfo.get(position).getMeas2());
        }
        if (foodInfo.get(position).getMeas3().contentEquals("") || foodInfo.get(position).getMeas3() == null){
            holder.tvMeas3.setVisibility(View.GONE);
        }else {
            holder.tvMeas3.setText(foodInfo.get(position).getMeas3());
        }
        if (foodInfo.get(position).getMeas4().contentEquals("") || foodInfo.get(position).getMeas4() == null){
            holder.tvMeas4.setVisibility(View.GONE);
        }else {
            holder.tvMeas4.setText(foodInfo.get(position).getMeas4());
        }
        if (foodInfo.get(position).getMeas5().contentEquals("") || foodInfo.get(position).getMeas5() == null){
            holder.tvMeas5.setVisibility(View.GONE);
        }else {
            holder.tvMeas5.setText(foodInfo.get(position).getMeas5());
        }
        if (foodInfo.get(position).getMeas6().contentEquals("") || foodInfo.get(position).getMeas6() == null){
            holder.tvMeas6.setVisibility(View.GONE);
        }else {
            holder.tvMeas6.setText(foodInfo.get(position).getMeas6());
        }
        if (foodInfo.get(position).getMeas7().contentEquals("") || foodInfo.get(position).getMeas7() == null){
            holder.tvMeas7.setVisibility(View.GONE);
        }else {
            holder.tvMeas7.setText(foodInfo.get(position).getMeas7());
        }
        if (foodInfo.get(position).getMeas8().contentEquals("") || foodInfo.get(position).getMeas8() == null){
            holder.tvMeas8.setVisibility(View.GONE);
        }else {
            holder.tvMeas8.setText(foodInfo.get(position).getMeas8());
        }
        if (foodInfo.get(position).getMeas9().contentEquals("") || foodInfo.get(position).getMeas9() == null){
            holder.tvMeas9.setVisibility(View.GONE);
        }else {
            holder.tvMeas9.setText(foodInfo.get(position).getMeas9());
        }
        if (foodInfo.get(position).getMeas10().contentEquals("") || foodInfo.get(position).getMeas10() == null){
            holder.tvMeas10.setVisibility(View.GONE);
        }else {
            holder.tvMeas10.setText(foodInfo.get(position).getMeas10());
        }
        if (foodInfo.get(position).getMeas11().contentEquals("") || foodInfo.get(position).getMeas11() == null){
            holder.tvMeas11.setVisibility(View.GONE);
        }else {
            holder.tvMeas11.setText(foodInfo.get(position).getMeas11());
        }
        if (foodInfo.get(position).getMeas12().contentEquals("") || foodInfo.get(position).getMeas12() == null){
            holder.tvMeas12.setVisibility(View.GONE);
        }else {
            holder.tvMeas12.setText(foodInfo.get(position).getMeas12());
        }
        if (foodInfo.get(position).getMeas13().contentEquals("") || foodInfo.get(position).getMeas13() == null){
            holder.tvMeas13.setVisibility(View.GONE);
        }else {
            holder.tvMeas13.setText(foodInfo.get(position).getMeas13());
        }
        if (foodInfo.get(position).getMeas14().contentEquals("") || foodInfo.get(position).getMeas14() == null){
            holder.tvMeas14.setVisibility(View.GONE);
        }else {
            holder.tvMeas14.setText(foodInfo.get(position).getMeas14());
        }
        if (foodInfo.get(position).getMeas15().contentEquals("") || foodInfo.get(position).getMeas15() == null){
            holder.tvMeas15.setVisibility(View.GONE);
        }else {
            holder.tvMeas15.setText(foodInfo.get(position).getMeas15());
        }
        if (foodInfo.get(position).getMeas16().contentEquals("") || foodInfo.get(position).getMeas16() == null){
            holder.tvMeas16.setVisibility(View.GONE);
        }else {
            holder.tvMeas16.setText(foodInfo.get(position).getMeas16());
        }
        if (foodInfo.get(position).getMeas17().contentEquals("") || foodInfo.get(position).getMeas17() == null){
            holder.tvMeas17.setVisibility(View.GONE);
        }else {
            holder.tvMeas17.setText(foodInfo.get(position).getMeas17());
        }
        if (foodInfo.get(position).getMeas18().contentEquals("") || foodInfo.get(position).getMeas18() == null){
            holder.tvMeas18.setVisibility(View.GONE);
        }else {
            holder.tvMeas18.setText(foodInfo.get(position).getMeas18());
        }
        if (foodInfo.get(position).getMeas19().contentEquals("") || foodInfo.get(position).getMeas19() == null){
            holder.tvMeas19.setVisibility(View.GONE);
        }else {
            holder.tvMeas19.setText(foodInfo.get(position).getMeas19());
        }
        if (foodInfo.get(position).getMeas20().contentEquals("") || foodInfo.get(position).getMeas20() == null){
            holder.tvMeas20.setVisibility(View.GONE);
        }else {
            holder.tvMeas20.setText(foodInfo.get(position).getMeas20());
        }

    }

    private void setIngStatus(int position , MyViewHolder holder){
        if (foodInfo.get(position).getIng1().contentEquals("") || foodInfo.get(position).getIng1() == null){
            holder.tvIng1.setVisibility(View.GONE);
            holder.l1.setVisibility(View.GONE);
        }else {
            holder.tvIng1.setText(foodInfo.get(position).getIng1());
        }
        if (foodInfo.get(position).getIng2().contentEquals("") || foodInfo.get(position).getIng2() == null){
            holder.tvIng2.setVisibility(View.GONE);
            holder.l2.setVisibility(View.GONE);
        }else {
            holder.tvIng2.setText(foodInfo.get(position).getIng2());
        }
        if (foodInfo.get(position).getIng3().contentEquals("") || foodInfo.get(position).getIng3() == null){
            holder.tvIng3.setVisibility(View.GONE);
            holder.l3.setVisibility(View.GONE);
        }else {
            holder.tvIng3.setText(foodInfo.get(position).getIng3());
        }
        if (foodInfo.get(position).getIng4().contentEquals("") || foodInfo.get(position).getIng4() == null){
            holder.tvIng4.setVisibility(View.GONE);
            holder.l4.setVisibility(View.GONE);
        }else {
            holder.tvIng4.setText(foodInfo.get(position).getIng4());
        }
        if (foodInfo.get(position).getIng5().contentEquals("") || foodInfo.get(position).getIng5() == null){
            holder.tvIng5.setVisibility(View.GONE);
            holder.l5.setVisibility(View.GONE);
        }else {
            holder.tvIng5.setText(foodInfo.get(position).getIng5());
        }
        if (foodInfo.get(position).getIng6().contentEquals("") || foodInfo.get(position).getIng6() == null){
            holder.tvIng6.setVisibility(View.GONE);
            holder.l6.setVisibility(View.GONE);
        }else {
            holder.tvIng6.setText(foodInfo.get(position).getIng6());
        }
        if (foodInfo.get(position).getIng7().contentEquals("") || foodInfo.get(position).getIng7() == null){
            holder.tvIng7.setVisibility(View.GONE);
            holder.l7.setVisibility(View.GONE);
        }else {
            holder.tvIng7.setText(foodInfo.get(position).getIng7());
        }
        if (foodInfo.get(position).getIng8().contentEquals("") || foodInfo.get(position).getIng8() == null){
            holder.tvIng8.setVisibility(View.GONE);
            holder.l8.setVisibility(View.GONE);
        }else {
            holder.tvIng8.setText(foodInfo.get(position).getIng8());
        }
        if (foodInfo.get(position).getIng9().contentEquals("") || foodInfo.get(position).getIng9() == null){
            holder.tvIng9.setVisibility(View.GONE);
            holder.l9.setVisibility(View.GONE);
        }else {
            holder.tvIng9.setText(foodInfo.get(position).getIng9());
        }
        if (foodInfo.get(position).getIng10().contentEquals("") || foodInfo.get(position).getIng10() == null){
            holder.tvIng10.setVisibility(View.GONE);
            holder.l10.setVisibility(View.GONE);
        }else {
            holder.tvIng10.setText(foodInfo.get(position).getIng10());
        }
        if (foodInfo.get(position).getIng11().contentEquals("") || foodInfo.get(position).getIng11() == null){
            holder.tvIng11.setVisibility(View.GONE);
            holder.l11.setVisibility(View.GONE);
        }else {
            holder.tvIng11.setText(foodInfo.get(position).getIng11());
        }
        if (foodInfo.get(position).getIng12().contentEquals("") || foodInfo.get(position).getIng12() == null){
            holder.tvIng12.setVisibility(View.GONE);
            holder.l12.setVisibility(View.GONE);
        }else {
            holder.tvIng12.setText(foodInfo.get(position).getIng12());
        }
        if (foodInfo.get(position).getIng13().contentEquals("") || foodInfo.get(position).getIng13() == null){
            holder.tvIng13.setVisibility(View.GONE);
            holder.l13.setVisibility(View.GONE);
        }else {
            holder.tvIng13.setText(foodInfo.get(position).getIng13());
        }
        if (foodInfo.get(position).getIng14().contentEquals("") || foodInfo.get(position).getIng14() == null){
            holder.tvIng14.setVisibility(View.GONE);
            holder.l14.setVisibility(View.GONE);
        }else {
            holder.tvIng14.setText(foodInfo.get(position).getIng14());
        }
        if (foodInfo.get(position).getIng15().contentEquals("") || foodInfo.get(position).getIng15() == null){
            holder.tvIng15.setVisibility(View.GONE);
            holder.l15.setVisibility(View.GONE);
        }else {
            holder.tvIng15.setText(foodInfo.get(position).getIng15());
        }
        if (foodInfo.get(position).getIng16().contentEquals("") || foodInfo.get(position).getIng16() == null){
            holder.tvIng16.setVisibility(View.GONE);
            holder.l16.setVisibility(View.GONE);
        }else {
            holder.tvIng16.setText(foodInfo.get(position).getIng16());
        }
        if (foodInfo.get(position).getIng17().contentEquals("") || foodInfo.get(position).getIng17() == null){
            holder.tvIng17.setVisibility(View.GONE);
            holder.l17.setVisibility(View.GONE);
        }else {
            holder.tvIng17.setText(foodInfo.get(position).getIng17());
        }
        if (foodInfo.get(position).getIng18().contentEquals("") || foodInfo.get(position).getIng18() == null){
            holder.tvIng18.setVisibility(View.GONE);
            holder.l18.setVisibility(View.GONE);
        }else {
            holder.tvIng18.setText(foodInfo.get(position).getIng18());
        }
        if (foodInfo.get(position).getIng19().contentEquals("") || foodInfo.get(position).getIng19() == null){
            holder.tvIng19.setVisibility(View.GONE);
            holder.l19.setVisibility(View.GONE);
        }else {
            holder.tvIng19.setText(foodInfo.get(position).getIng19());
        }
        if (foodInfo.get(position).getIng20().contentEquals("") || foodInfo.get(position).getIng20() == null){
            holder.tvIng20.setVisibility(View.GONE);
            holder.l20.setVisibility(View.GONE);
        }else {
            holder.tvIng20.setText(foodInfo.get(position).getIng20());
        }
    }
}
