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

import com.example.canteenautomation.Activities.ViewFoodDetailsActivity;
import com.example.canteenautomation.Models.FoodItem;
import com.example.canteenautomation.Models.Order;
import com.example.canteenautomation.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewStatusCustomAdapter extends RecyclerView.Adapter<ViewStatusCustomAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Order> orderArrayList;

    public ViewStatusCustomAdapter(Context context, ArrayList<Order> orderArrayList) {
        this.context = context;
        this.orderArrayList = orderArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_order_status_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.OrderId.setText(orderArrayList.get(i).getOrderId());
        holder.OrderStatus.setText(orderArrayList.get(i).getStatus());
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        //Different Widgets
        TextView OrderId;
        TextView OrderStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //casting
            OrderId=itemView.findViewById(R.id.view_order_id);
            OrderStatus=itemView.findViewById(R.id.order_status);
        }
    }
}
