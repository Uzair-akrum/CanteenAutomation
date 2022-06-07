package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.Models.FoodItem;
import com.example.canteenautomation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddNewFoodActivity extends AppCompatActivity {
    private EditText name,cost,time,description;
    private String foodName,foodCost,foodTime,foodDescription;

    private Button AddFoodBtn;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference myRootRef;

    FoodItem foodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_food);

        initAll();
        settingUpListners();
    }

    private void settingUpListners() {
//        time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Calendar c = Calendar.getInstance();
//                int hour = c.get(Calendar.HOUR_OF_DAY);
//                int minute = c.get(Calendar.MINUTE);
//
//                TimePickerDialog timePickerDialog = new TimePickerDialog(AddNewFoodActivity.this,
//                        new TimePickerDialog.OnTimeSetListener() {
//
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//
//                                time.setText(hourOfDay + ":" + minute);
//                            }
//                        }, hour, minute, false);
//                timePickerDialog.show();
//            }
//        });
        AddFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodName=name.getText().toString().trim();
                foodCost=cost.getText().toString().trim();
                foodTime=time.getText().toString().trim();
                foodDescription=description.getText().toString().trim();

                if(TextUtils.isEmpty(foodName))
                {
                    name.setError("Enter food name");
                }
                else if(TextUtils.isEmpty(foodCost))
                {
                    cost.setError("Enter food cost");
                }
                else if(TextUtils.isEmpty(foodTime))
                {
                    time.setError("Enter time in mints");
                }

                else if(TextUtils.isEmpty(foodDescription))
                {
                    description.setError("Enter food description");
                }
                else{
                    AddNewFoodItem();
                }


            }
        });

    }

    private void AddNewFoodItem() {
        progressBar.setVisibility(View.VISIBLE);
        AddFoodBtn.setVisibility(View.INVISIBLE);
        foodItem.setName(foodName);
        foodItem.setCost(foodCost);
        foodItem.setTime(foodTime);
        foodItem.setDescription(foodDescription);

        String key = myRootRef.push().getKey();
        foodItem.setFoodId(key);

        myRootRef.child("FoodItem").child(key).setValue(foodItem).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(AddNewFoodActivity.this, "Food Item Added", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddNewFoodActivity.this,CanteenHomeActivity.class);
                startActivity(intent);
                finish();
            }
        })
                //handle failuer result
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("food",e.toString());
                    }
                });
    }

    private void initAll() {
        name=findViewById(R.id.food_name);
        cost=findViewById(R.id.food_cost);
        time=findViewById(R.id.food_time);
        description=findViewById(R.id.food_description);


        AddFoodBtn=findViewById(R.id.add_food_btn);
        progressBar=findViewById(R.id.add_food_progress_bar);
        progressBar.setVisibility(View.GONE);

        mAuth=FirebaseAuth.getInstance();
        myRootRef= FirebaseDatabase.getInstance().getReference();
        foodItem=new FoodItem();
    }
}