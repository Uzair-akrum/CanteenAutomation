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

import com.example.canteenautomation.R;
import com.google.firebase.auth.FirebaseAuth;

public class CanteenHomeActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView AddFood,ViewUpdateFood;
    private ImageView viewOrders;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_home);
        initAll();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Canteen Portal");

        settingUpListnes();
    }

    private void settingUpListnes() {
        AddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CanteenHomeActivity.this,AddNewFoodActivity.class);
                startActivity(intent);
            }
        });
        ViewUpdateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CanteenHomeActivity.this,ViewFoodActivity.class);
                startActivity(intent);
            }
        });
        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CanteenHomeActivity.this,CanteenCustomerOrdersActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initAll() {
        mToolbar=findViewById(R.id.main_page_toolbar);
        mAuth=FirebaseAuth.getInstance();

        AddFood=findViewById(R.id.home_can_add);
        ViewUpdateFood=findViewById(R.id.home_can_view);
        viewOrders=findViewById(R.id.canteen_view_order_request);

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
        Intent intent=new Intent(CanteenHomeActivity.this,StartActivity.class);
        startActivity(intent);
    }
}