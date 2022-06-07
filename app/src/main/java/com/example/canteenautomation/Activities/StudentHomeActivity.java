package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.canteenautomation.R;
import com.google.firebase.auth.FirebaseAuth;

public class StudentHomeActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView foodMenu,deposit,withdrawl,myOrders;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        initAll();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Student Portal");

        settingUpListners();
    }

    private void settingUpListners() {
        foodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentHomeActivity.this,ViewMenuActivity.class);
                startActivity(intent);
            }
        });
        withdrawl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentHomeActivity.this, "withdraw", Toast.LENGTH_SHORT).show();

            }
        });
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentHomeActivity.this, "Deposit", Toast.LENGTH_SHORT).show();
            }
        });
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentHomeActivity.this,ViewOrdersActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initAll() {
        mToolbar=findViewById(R.id.main_page_toolbar);
        mAuth=FirebaseAuth.getInstance();
        foodMenu=findViewById(R.id.student_view_food_menu);
        deposit=findViewById(R.id.home_stu_deposit);
        withdrawl=findViewById(R.id.home_stu_withdraw);
        myOrders=findViewById(R.id.student_view_my_orders);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.main_logout_option){
            mAuth.signOut();
            sendUserToStartActivity();
        }

        return true;
    }
    private void sendUserToStartActivity() {
        Intent intent=new Intent(StudentHomeActivity.this,StartActivity.class);
        startActivity(intent);
    }
}