package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteenautomation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText email,password;
    private Button login_btn;
    private TextView userTypeTextView;
    private String mail,pass;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference myRootRef;

    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Declare Function
        initAllUi();
        buttonClickListeners();
    }

    private void buttonClickListeners() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //function
                UserLogin();
            }
        });

    }

    private void UserLogin() {
        //getting data
        mail=email.getText().toString().trim();
        pass=password.getText().toString().trim();
        //check authentication
        if(TextUtils.isEmpty(mail))
        {
            // Toast.makeText(MainActivity.this,"Enter Your Email",Toast.LENGTH_SHORT).show();
            //show progress bar
            progressBar.setVisibility(View.GONE);
            email.setError("Enter Your Email");
        }
        //if the password field is empty
        else if(TextUtils.isEmpty(pass))
        {
            // Toast.makeText(MainActivity.this,"Enter You Password",Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            password.setError("Enter Your Password");
        }else{

            progressBar.setVisibility(View.VISIBLE);
            login_btn.setVisibility(View.INVISIBLE);
            login_btn.setEnabled(false);
            //Check authentication by using email and password
            mAuth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                String currentUserId=FirebaseAuth.getInstance().getCurrentUser().getUid();

                                Log.d("userId",currentUserId);

                                myRootRef.child(userType).child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        //place data in datasnapshot that we can show
                                        if(dataSnapshot.exists())
                                        {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            if(userType.equals("Canteen")) {
                                                Intent intent = new Intent(LoginActivity.this, CanteenHomeActivity.class);
                                                Toast.makeText(LoginActivity.this, "Welcom Canteen", Toast.LENGTH_SHORT).show();
                                                startActivity(intent);
                                                finish();
                                            }
                                            else if(userType.equals("Student")){
                                                Intent intent = new Intent(LoginActivity.this, StudentHomeActivity.class);
                                                Toast.makeText(LoginActivity.this, "Welcom Student", Toast.LENGTH_SHORT).show();
                                                startActivity(intent);
                                                finish();
                                            }
                                            else{
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                Toast.makeText(LoginActivity.this, "Welcom Admin", Toast.LENGTH_SHORT).show();
                                                startActivity(intent);
                                                finish();
                                            }


                                        }else
                                        {
                                            mAuth.signOut();
                                            Toast.makeText(LoginActivity.this,"This is not "+userType+" login details",Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                            login_btn.setVisibility(View.VISIBLE);
                                            login_btn.setEnabled(true);
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            } else {

                                //setting progree bar
                                progressBar.setVisibility(View.GONE);
                                login_btn.setEnabled(true);
                                login_btn.setVisibility(View.VISIBLE);
                                //show message
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();


                            }


                        }
                    });


        }

    }

    private void initAllUi() {
        email=(EditText)findViewById(R.id.login_email);
        password=(EditText)findViewById(R.id.login_pass);
        login_btn=(Button)findViewById(R.id.login_btn);
        progressBar=(ProgressBar)findViewById(R.id.login_progress_bar);
        progressBar.setVisibility(View.GONE);

        mAuth=FirebaseAuth.getInstance();
        userTypeTextView=(TextView)findViewById(R.id.usertype_login);
        myRootRef= FirebaseDatabase.getInstance().getReference();

        if(getIntent()!=null)
        {
            userType=getIntent().getStringExtra("userType");
            Log.d("useType",userType);
            userTypeTextView.setText(userType+" Login");
        }
    }
}