package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.Models.FoodItem;
import com.example.canteenautomation.Models.Order;
import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class ViewFoodsMenueDetailsActivity extends AppCompatActivity {
    private EditText name,cost,time,description;
    private ProgressBar progressBar;
    private Button orderBtn;

    private AlertDialog.Builder builder;

    FoodItem foodItem;
    private Order order;
    private Student student;
    String stuBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_foods_menue_details_main);

        initAll();
        settingDatainUI();
        settingUPListners();

    }

    private void settingUPListners() {
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {

                        settingDataOnServer();

                        dialog.dismiss();
                        Intent intent=new Intent(ViewFoodsMenueDetailsActivity.this,StudentHomeActivity.class);
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

    private void settingDataOnServer() {
        //place order code goes here
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference root= FirebaseDatabase.getInstance().getReference().child("Orders");
        String key=root.push().getKey();
        //id set
        order.setOrderId(key);
        //date set
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
       //get student ref so that we can check weather the student have enough balance to order food
//        DatabaseReference balReg=FirebaseDatabase.getInstance().getReference().child("Student").child(userid);
//        balReg.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                        student = dataSnapshot1.getValue(Student.class);
//                        String stuBalance=student.getBalance();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                throw databaseError.toException();
//            }
//        });
//        int stuBal=Integer.parseInt(stuBalance);
//        int fodItemCost=Integer.parseInt(foodItem.getCost());

        order.setDateOfOrder(currentDateTimeString);
        order.setStatus("pending");
        order.setStudentID(userid);
        order.setFoodId(foodItem.getFoodId());
        root.child(key).setValue(order).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //show message
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ViewFoodsMenueDetailsActivity.this,"Order Submitted..!",Toast.LENGTH_SHORT).show();
                //change screen
                Intent intent=new Intent(ViewFoodsMenueDetailsActivity.this,StudentHomeActivity.class);
                startActivity(intent);
                finish();
            }
        })
                //handle failuer result
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("order",e.toString());
                    }
                });
        Intent intent=new Intent(ViewFoodsMenueDetailsActivity.this,StudentHomeActivity.class);
        startActivity(intent);




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


        name=findViewById(R.id.menu_details_food_name);
        cost=findViewById(R.id.menu_details_food_cost);
        time=findViewById(R.id.menu_details_food_time);
        description=findViewById(R.id.menu_detail_description);

        order=new Order();
        student=new Student();

        orderBtn =findViewById(R.id.menu_order_btn);

        progressBar=findViewById(R.id.menu_details_progress);
        progressBar.setVisibility(View.GONE);

        //alert dailog
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
    }
}