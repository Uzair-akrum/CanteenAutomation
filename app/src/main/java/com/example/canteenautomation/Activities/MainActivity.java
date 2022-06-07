package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.canteenautomation.R;
import com.example.canteenautomation.Adapters.TabsAccessorAapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabsAccessorAapter myTabsAccessorAapter;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAll();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Admin Portal");
    }

    private void initAll() {
        mToolbar=findViewById(R.id.main_page_toolbar);
        myViewPager=findViewById(R.id.main_tabs_pager);

        myTabsAccessorAapter=new TabsAccessorAapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsAccessorAapter);

        myTabLayout=findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewPager);

        mAuth=FirebaseAuth.getInstance();
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
            sendUserToLoginActivity();
        }

        return true;
    }

    private void sendUserToLoginActivity() {
        Intent intent=new Intent(MainActivity.this,StartActivity.class);
        startActivity(intent);
        finish();
    }
}