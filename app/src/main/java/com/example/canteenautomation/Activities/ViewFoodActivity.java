package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.canteenautomation.Adapters.CanteenListCustomAdapter;
import com.example.canteenautomation.Adapters.FoodListCustomAdapter;
import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.Models.FoodItem;
import com.example.canteenautomation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewFoodActivity extends AppCompatActivity {
    private RecyclerView mainRecyclerView;
    //Data Structures
    private ArrayList<FoodItem> foodItemsArray;

    private DatabaseReference rootRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food);

        initAll();
        initRecyclerView();
    }

    private void initRecyclerView() {
        //declare and initialize adapter
        final FoodListCustomAdapter foodListCustomAdapter = new FoodListCustomAdapter(ViewFoodActivity.this, foodItemsArray);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewFoodActivity.this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerView.setAdapter(foodListCustomAdapter);

        //access child and add listner
        rootRef.child("FoodItem").addValueEventListener(new ValueEventListener() {
            FoodItem foodItem = new FoodItem();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        foodItem = dataSnapshot1.getValue(FoodItem.class);
                        foodItemsArray.add(foodItem);
                        foodListCustomAdapter.notifyDataSetChanged();
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
        mainRecyclerView=(RecyclerView)findViewById(R.id.food_list_recycler_view);
        //initialize variables
        foodItemsArray=new ArrayList<FoodItem>();
        mAuth=FirebaseAuth.getInstance();
        rootRef= FirebaseDatabase.getInstance().getReference();
    }
}