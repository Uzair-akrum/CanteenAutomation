package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import
        androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewCanteenActivity extends AppCompatActivity {
    private EditText email,pass,managment,handler,mobile,workers,address;
    private String CanteenEmail,CanteenPass,CanteenManagment,CanteenHandler,CanteenMobile,CanteenWorkers,CanteenAddress;
    private Button signUpCanteen;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference myRootRef;

    Canteen canteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_canteen);
        
        initAll();
        settingUPlistners();
    }

    private void settingUPlistners() {
        signUpCanteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CanteenEmail=email.getText().toString().trim();
                CanteenPass=pass.getText().toString().trim();
                CanteenManagment=managment.getText().toString().trim();
                CanteenHandler=handler.getText().toString().trim();
                CanteenMobile=mobile.getText().toString().trim();
                CanteenWorkers=workers.getText().toString().trim();
                CanteenAddress=address.getText().toString().trim();

                if(TextUtils.isEmpty(CanteenEmail))
                {
                    email.setError("Enter Your Email");
                }
                else if(TextUtils.isEmpty(CanteenPass))
                {
                    pass.setError("Enter Your password");
                }
                else if(TextUtils.isEmpty(CanteenManagment))
                {
                    managment.setError("Enter Managment name");
                }
                else if(TextUtils.isEmpty(CanteenHandler))
                {
                    handler.setError("Enter handler name");     
                }
                else if(TextUtils.isEmpty(CanteenMobile))
                {
                    mobile.setError("Enter canteen mobile");
                }
                else if(TextUtils.isEmpty(CanteenWorkers))
                {
                    workers.setError("Enter no of workers");
                }
                else if(TextUtils.isEmpty(CanteenAddress))
                {
                    address.setError("Enter Address of canteen");
                }
                else{
                    AddNewCanteen();
                }
            }
        });
    }

    private void AddNewCanteen() {
        progressBar.setVisibility(View.VISIBLE);
        signUpCanteen.setVisibility(View.INVISIBLE);
        canteen.setEmail(CanteenEmail);
        canteen.setPass(CanteenPass);
        canteen.setBalance("0");
        canteen.setManagmentName(CanteenManagment);
        canteen.setHandler(CanteenHandler);
        canteen.setMobile(CanteenMobile);
        canteen.setNoOfWorkers(CanteenWorkers);
        canteen.setAddress(CanteenAddress);

        mAuth.createUserWithEmailAndPassword(CanteenEmail,CanteenPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("canteen", "createUserWithEmail:success");
                            String currentUserId=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            canteen.setCanteenId(currentUserId);

                            myRootRef.child("Canteen").child(currentUserId).setValue(canteen).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //show message
                                    Toast.makeText(AddNewCanteenActivity.this,"New Canteen Added successfully",Toast.LENGTH_SHORT).show();
                                    //change screen
                                    Intent intent=new Intent(AddNewCanteenActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    mAuth.signOut();
                                    finish();
                                }
                            })
                                    //handle failuer result
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("canteen",e.toString());
                                        }
                                    });
                        }
                        else{
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.INVISIBLE);
                            signUpCanteen.setVisibility(View.VISIBLE);
                            Log.w("canteen", "createUserWithEmail:failure", task.getException());
                            //show message
                            Toast.makeText(AddNewCanteenActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initAll() {
        email=findViewById(R.id.canteen_email);
        pass=findViewById(R.id.canteen_pass);
        managment=findViewById(R.id.canteen_management_name);
        handler=findViewById(R.id.canteen_handler);
        mobile=findViewById(R.id.canteen_mobile);
        workers=findViewById(R.id.canteen_no_of_workers);
        address=findViewById(R.id.canteen_address);

        signUpCanteen=findViewById(R.id.canteen_signup_btn);
        progressBar=findViewById(R.id.canteen_signup_progress_bar);
        progressBar.setVisibility(View.GONE);

        mAuth=FirebaseAuth.getInstance();
        myRootRef= FirebaseDatabase.getInstance().getReference();
        canteen=new Canteen();

    }
}