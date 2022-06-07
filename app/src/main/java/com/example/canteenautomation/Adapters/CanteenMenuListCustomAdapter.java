package com.example.canteenautomation.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canteenautomation.Activities.ViewFoodsMenueDetailsActivity;
import com.example.canteenautomation.Models.FoodItem;
import com.example.canteenautomation.R;

import java.io.Serializable;
import java.util.ArrayList;

public class CanteenMenuListCustomAdapter extends RecyclerView.Adapter<CanteenMenuListCustomAdapter.ViewHolder>{
    private Context context;
    private ArrayList<FoodItem> foodItemsList;

    public CanteenMenuListCustomAdapter(Context context, ArrayList<FoodItem> foodItemsList) {
        this.context = context;
        this.foodItemsList = foodItemsList;
    }

    @NonNull
    @Override
    public CanteenMenuListCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_menu_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.FoodName.setText(foodItemsList.get(i).getName());
        viewHolder.FoodPrice.setText(foodItemsList.get(i).getCost());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change screen
                Intent intent=new Intent(context, ViewFoodsMenueDetailsActivity.class);
                intent.putExtra("FoodItem", (Serializable) foodItemsList.get(viewHolder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //Different Widgets
        TextView FoodName;
        TextView FoodPrice;
        CardView layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //casting
            FoodName=itemView.findViewById(R.id.f_name);
            FoodPrice=itemView.findViewById(R.id.f_price);
            layout=itemView.findViewById(R.id.menu_single_layout);
        }
    }

}
