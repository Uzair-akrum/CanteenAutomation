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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewStudentActivity extends AppCompatActivity {
    private EditText email,password,name,department,program,mobile,address;
    private Button signUpStudent;
    private String stuEmail,stuPassword,stuName,stuGender,stuDepartment,stuProgram,stuMobile,stuAddress;

    private RadioGroup radioGroup;
    private RadioButton genderRadioButton;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference myRootRef;

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        initAll();
        settingUpListners();
    }

    private void settingUpListners() {
        signUpStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stuEmail=email.getText().toString().trim();
                stuPassword=password.getText().toString().trim();
                stuName=name.getText().toString().trim();
                stuDepartment=department.getText().toString().trim();
                stuProgram=program.getText().toString().trim();
                stuMobile=mobile.getText().toString().trim();
                stuAddress=address.getText().toString().trim();

                if(TextUtils.isEmpty(stuEmail))
                {
                    email.setError("Entered Email");
                }
                else if(TextUtils.isEmpty(stuPassword)){
                    password.setError("Entered password");
                }
                else if(TextUtils.isEmpty(stuName)){
                    name.setError("Entered full name");
                }
                else if(TextUtils.isEmpty(stuDepartment)){
                    department.setError("Entered department");
                }
                else if(TextUtils.isEmpty(stuProgram)){
                    program.setError("Entered progarm");
                }
                else if(TextUtils.isEmpty(stuMobile)){
                    mobile.setError("Entered mobile");
                }
                else if(TextUtils.isEmpty(stuAddress)){
                    address.setError("Entered address");
                }
                else if(radioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(AddNewStudentActivity.this,"Select Gender",Toast.LENGTH_SHORT).show();
                }
                else{
                    CreateStudentAccount();
                }

            }
        });
    }

    private void CreateStudentAccount() {
        progressBar.setVisibility(View.VISIBLE);
        signUpStudent.setVisibility(View.INVISIBLE);
        student.setEmail(stuEmail);
        student.setPaasword(stuPassword);
        student.setName(stuName);
        student.setBalance("0");
        student.setDepartment(stuDepartment);
        student.setProgram(stuProgram);
        student.setMobile(stuMobile);
        student.setAddress(stuAddress);

        int selectedId = radioGroup.getCheckedRadioButtonId();
        genderRadioButton = (RadioButton) findViewById(selectedId);
        student.setGender(genderRadioButton.getText().toString());

        mAuth.createUserWithEmailAndPassword(stuEmail,stuPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("student", "createUserWithEmail:success");
                            String currentUserId=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            student.setStuentId(currentUserId);

                            myRootRef.child("Student").child(currentUserId).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //show message
                                    Toast.makeText(AddNewStudentActivity.this,"New Student Added successfully",Toast.LENGTH_SHORT).show();
                                    //change screen
                                    Intent intent=new Intent(AddNewStudentActivity.this,MainActivity.class);
                                    //sending data
//                                    intent.putExtra("user",user);
                                    startActivity(intent);
                                    mAuth.signOut();
                                    finish();
                                }
                            })
                                    //handle failuer result
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("student",e.toString());
                                        }
                                    });
                        }
                        else{
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.INVISIBLE);
                            signUpStudent.setVisibility(View.VISIBLE);
                            Log.w("canteen", "createUserWithEmail:failure", task.getException());
                            //show message
                            Toast.makeText(AddNewStudentActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initAll() {
        email=findViewById(R.id.student_email);
        password=findViewById(R.id.student_pass);
        name=findViewById(R.id.student_name);
        department=findViewById(R.id.student_department);
        program=findViewById(R.id.student_program);
        mobile=findViewById(R.id.student_mobile);
        address=findViewById(R.id.student_address);
        email=findViewById(R.id.student_email);

        radioGroup=(RadioGroup)findViewById(R.id.gender_radio_group);

        signUpStudent =findViewById(R.id.student_signup_btn);
        progressBar=findViewById(R.id.student_signup_progress_bar);
        progressBar.setVisibility(View.GONE);

        mAuth=FirebaseAuth.getInstance();
        myRootRef= FirebaseDatabase.getInstance().getReference();
        student=new Student();
    }
}