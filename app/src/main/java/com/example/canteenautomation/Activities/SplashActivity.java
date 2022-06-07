package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.canteenautomation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference myRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initAll();
    }

    private void initAll() {
        mAuth=FirebaseAuth.getInstance();
        myRootRef= FirebaseDatabase.getInstance().getReference();

        String currentUserId=FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRootRef.child("Admin").child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //place data in datasnapshot that we can show
                if(dataSnapshot.exists())
                {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    Toast.makeText(SplashActivity.this, "Welcom Admin", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRootRef.child("Canteen").child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //place data in datasnapshot that we can show
                if(dataSnapshot.exists())
                {
                    Intent intent = new Intent(SplashActivity.this, CanteenHomeActivity.class);
                    Toast.makeText(SplashActivity.this, "Welcom Canteen", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRootRef.child("Student").child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //place data in datasnapshot that we can show
                if(dataSnapshot.exists())
                {
                    Intent intent = new Intent(SplashActivity.this, StudentHomeActivity.class);
                    Toast.makeText(SplashActivity.this, "Welcom Student", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}