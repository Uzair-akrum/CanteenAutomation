package com.example.canteenautomation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canteenautomation.Models.Order;
import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;

import java.util.ArrayList;

public class CustomeAdapterForCanteenViewOrders extends RecyclerView.Adapter<CustomeAdapterForCanteenViewOrders.ViewHolder>{
    private Context context;
    private ArrayList<Order> orderArrayList;

    public CustomeAdapterForCanteenViewOrders(Context context, ArrayList<Order> orderArrayList) {
        this.context = context;
        this.orderArrayList = orderArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.canteen_single_customer_order_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.OrderId.setText(orderArrayList.get(i).getOrderId());
        holder.OrderStatus.setText(orderArrayList.get(i).getStatus());
        holder.StudentName.setText(orderArrayList.get(i).getStudentID());
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        //Different Widgets
        TextView OrderId;
        TextView OrderStatus;
        TextView StudentName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //casting
            OrderId=itemView.findViewById(R.id.c_view_order_id);
            OrderStatus=itemView.findViewById(R.id.c_order_status);
            StudentName=itemView.findViewById(R.id.c_view_order_name);
        }
    }
}
