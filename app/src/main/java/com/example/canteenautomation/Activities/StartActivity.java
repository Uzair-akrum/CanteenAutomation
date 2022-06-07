package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.canteenautomation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartActivity extends AppCompatActivity {
    private Button admin,canteen,student;
    private String userType;
    private FirebaseAuth mAuth;
    private DatabaseReference myRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //calling function
        initAllUi();
        buttonClickListeners();
    }

    private void buttonClickListeners() {
        //handle admin button
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userType="Admin";
                //calling function
                userTypeClick(userType);
            }
        });
        //handle canteen button
        canteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userType="Canteen";
                //calling function
                userTypeClick(userType);
            }
        });
        //handle student button
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userType="Student";
                //calling function
                userTypeClick(userType);
            }
        });
    }
    //functionality of function that calling upper braces
    private void userTypeClick(String userType) {
//        SharePrefrencesModel.setDefaults("usertype",userType,StartActivity.this);
        //changing screens
        Intent intent=new Intent(StartActivity.this,LoginActivity.class);
        intent.putExtra("userType",userType);
        startActivity(intent);
    }

    private void initAllUi() {
        //casting variables
        admin=(Button)findViewById(R.id.admin_btn);
        canteen=(Button)findViewById(R.id.canteen_btn);
        student=(Button)findViewById(R.id.student_btn);
        //initalize variables
        mAuth=FirebaseAuth.getInstance();
        myRootRef=FirebaseDatabase.getInstance().getReference();
        //there is no user login
        if(mAuth.getCurrentUser()!=null)
        {
            //for testing
            Log.d("authkey",mAuth.getUid());
            //changing screens
            Intent intent=new Intent(StartActivity.this,SplashActivity.class);
            startActivity(intent);
            finish();

        }

//        String currentUserId=FirebaseAuth.getInstance().getCurrentUser().getUid();
//        myRootRef.child("Admin").child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //place data in datasnapshot that we can show
//                if(dataSnapshot.exists())
//                {
//                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
//                    Toast.makeText(StartActivity.this, "Welcom Admin", Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//                    finish();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        myRootRef.child("Canteen").child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //place data in datasnapshot that we can show
//                if(dataSnapshot.exists())
//                {
//                    Intent intent = new Intent(StartActivity.this, CanteenHomeActivity.class);
//                    Toast.makeText(StartActivity.this, "Welcom Canteen", Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//                    finish();
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        myRootRef.child("Student").child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //place data in datasnapshot that we can show
//                if(dataSnapshot.exists())
//                {
//                    Intent intent = new Intent(StartActivity.this, StudentHomeActivity.class);
//                    Toast.makeText(StartActivity.this, "Welcom Student", Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}