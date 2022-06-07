package com.example.canteenautomation.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canteenautomation.Activities.ViewCanteenDetailsActivity;
import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.R;

import java.io.Serializable;
import java.util.ArrayList;

public class CanteenListCustomAdapter extends RecyclerView.Adapter<CanteenListCustomAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Canteen> canList;

    public CanteenListCustomAdapter(Context context, ArrayList<Canteen> canList) {
        this.context = context;
        this.canList = canList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_canteen_list_view_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CanteenListCustomAdapter.ViewHolder viewHolder, int i) {
        viewHolder.CanteenManagementName.setText(canList.get(i).getManagmentName());
        viewHolder.CanteenHandler.setText(canList.get(i).getHandler());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change screen
                Intent intent=new Intent(context, ViewCanteenDetailsActivity.class);
                intent.putExtra("Canteen", (Serializable) canList.get(viewHolder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return canList.size();
    }

    //inherit class viewHolder with Recylerview
    class ViewHolder extends RecyclerView.ViewHolder{

        //Different Widgets
        TextView CanteenManagementName;
        TextView CanteenHandler;
        CardView layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //casting
            CanteenManagementName=itemView.findViewById(R.id.cant_management_name);
            CanteenHandler=itemView.findViewById(R.id.cant_handler);
            layout=itemView.findViewById(R.id.can_single_layout);
        }
    }
}
