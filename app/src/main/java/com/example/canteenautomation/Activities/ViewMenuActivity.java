package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.canteenautomation.Adapters.CanteenMenuListCustomAdapter;
import com.example.canteenautomation.Adapters.FoodListCustomAdapter;
import com.example.canteenautomation.Models.FoodItem;
import com.example.canteenautomation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewMenuActivity extends AppCompatActivity {
    private RecyclerView mainRecyclerView;
    //Data Structures
    private ArrayList<FoodItem> foodItemsArray;

    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);

        initAll();
        initRecyclerView();
    }

    private void initRecyclerView() {
        //declare and initialize adapter
        final CanteenMenuListCustomAdapter menuListCustomAdapter = new CanteenMenuListCustomAdapter(ViewMenuActivity.this, foodItemsArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewMenuActivity.this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerView.setAdapter(menuListCustomAdapter);

        //access child and add listner
        rootRef.child("FoodItem").addValueEventListener(new ValueEventListener() {
            FoodItem foodItem = new FoodItem();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        foodItem = dataSnapshot1.getValue(FoodItem.class);
                        foodItemsArray.add(foodItem);
                        menuListCustomAdapter.notifyDataSetChanged();
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
        mainRecyclerView=(RecyclerView)findViewById(R.id.canteen_menu_list);
        //initialize variables
        foodItemsArray=new ArrayList<FoodItem>();
        rootRef= FirebaseDatabase.getInstance().getReference();
    }
}