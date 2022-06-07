package com.example.canteenautomation.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.Models.FoodItem;
import com.example.canteenautomation.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewFoodDetailsActivity extends AppCompatActivity {
    private EditText name,cost,time,description;
    private ProgressBar progressBar;
    private Button updateBtn,deleteBtn;
    private DatabaseReference UsersRef;

    private AlertDialog.Builder builder;

    FoodItem foodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food_details);

        initAll();
        settingDatainUI();
        settingUPListners();
    }
    private void settingUPListners() {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname,fcost,ftime,fdescription;

                fname = name.getText().toString().trim();
                fcost = cost.getText().toString().trim();
                ftime = time.getText().toString().trim();
                fdescription = description.getText().toString().trim();

                if(TextUtils.isEmpty(fname))
                {
                    name.setError("Enter name");
                }
                else if(TextUtils.isEmpty(fcost)){
                    cost.setError("Enter cost");
                }
                else if(TextUtils.isEmpty(ftime)){
                    time.setError("Enter time to cook");
                }
                else if(TextUtils.isEmpty(fdescription)){
                    description.setError("Enter Description");
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    UsersRef.child("name").setValue(fname);
                    UsersRef.child("cost").setValue(fcost);
                    UsersRef.child("time").setValue(ftime);
                    UsersRef.child("description").setValue(fdescription);

                    Toast.makeText(ViewFoodDetailsActivity.this, "Updated..!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Intent intent=new Intent(ViewFoodDetailsActivity.this,CanteenHomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        UsersRef.removeValue();
                        dialog.dismiss();
                        Intent intent=new Intent(ViewFoodDetailsActivity.this,CanteenHomeActivity.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void settingDatainUI() {
        name.setText(foodItem.getName());
        cost.setText(foodItem.getCost());
        time.setText(foodItem.getTime());
        description.setText(foodItem.getDescription());
    }

    private void initAll() {
        foodItem=new FoodItem();
        if(getIntent().getSerializableExtra("FoodItem")!=null){
            foodItem=(FoodItem) getIntent().getSerializableExtra("FoodItem");
        }
        UsersRef= FirebaseDatabase.getInstance().getReference().child("FoodItem").child(foodItem.getFoodId());

        name=findViewById(R.id.details_food_name);
        cost=findViewById(R.id.details_food_cost);
        time=findViewById(R.id.details_food_time);
        description=findViewById(R.id.detail_description);

        updateBtn=findViewById(R.id.details_update_btn);
        deleteBtn=findViewById(R.id.detail_delete_btn);

        progressBar=findViewById(R.id.details_progress);
        progressBar.setVisibility(View.GONE);

        //alert dailog
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
    }
}