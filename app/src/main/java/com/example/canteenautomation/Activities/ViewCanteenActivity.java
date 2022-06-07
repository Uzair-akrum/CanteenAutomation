package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.canteenautomation.Adapters.CanteenListCustomAdapter;
import com.example.canteenautomation.Adapters.StudentListCustomAdapter;
import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewCanteenActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;
    //Data Structures
    private ArrayList<Canteen> canteenList;
    private Canteen currentCanteen;

    //Firebase
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference rootRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_canteen);

        initAll();
        initRecyclerView();
    }

    private void initRecyclerView() {
        //declare and initialize adapter
        final CanteenListCustomAdapter canteenListCustomAdapter = new CanteenListCustomAdapter(ViewCanteenActivity.this, canteenList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewCanteenActivity.this);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerView.setAdapter(canteenListCustomAdapter);

        //access child and add listner
        rootRef.child("Canteen").addValueEventListener(new ValueEventListener() {
            Canteen canteen = new Canteen();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        canteen = dataSnapshot1.getValue(Canteen.class);
                        //for doctor login
                        canteenList.add(canteen);
                        canteenListCustomAdapter.notifyDataSetChanged();
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
        mainRecyclerView=(RecyclerView)findViewById(R.id.canteen_screen_recyclerview);
        //initialize variables
        canteenList=new ArrayList<Canteen>();
        mAuth=FirebaseAuth.getInstance();
        rootRef= FirebaseDatabase.getInstance().getReference();
    }
}