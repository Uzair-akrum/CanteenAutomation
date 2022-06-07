package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RatingBar;

import com.example.canteenautomation.Adapters.CustomeAdapterForCanteenViewOrders;
import com.example.canteenautomation.Adapters.ViewStatusCustomAdapter;
import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.Models.Order;
import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CanteenCustomerOrdersActivity extends AppCompatActivity {
    private RecyclerView mainRecyclerView;
    //Data Structures
    private ArrayList<Order> orderArrayList;

    private DatabaseReference rootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_customer_orders);

        initAll();
        initRecyclerView();
    }

    private void initRecyclerView() {
        //declare and initialize adapter
        final CustomeAdapterForCanteenViewOrders adapter = new CustomeAdapterForCanteenViewOrders(CanteenCustomerOrdersActivity.this, orderArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CanteenCustomerOrdersActivity.this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerView.setAdapter(adapter);

        //access child and add listner
        rootRef.child("Orders").addValueEventListener(new ValueEventListener() {
            Order order = new Order();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {


                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        order = dataSnapshot1.getValue(Order.class);
                        //for doctor login
                        orderArrayList.add(order);
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initAll() {
        //casting variables
        mainRecyclerView=(RecyclerView)findViewById(R.id.canteen_customer_orders);
        //initialize variables
        orderArrayList=new ArrayList<Order>();
        rootRef= FirebaseDatabase.getInstance().getReference();
    }
}