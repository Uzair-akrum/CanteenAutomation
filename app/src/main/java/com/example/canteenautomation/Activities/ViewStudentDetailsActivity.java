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
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;

public class ViewStudentDetailsActivity extends AppCompatActivity {
    private EditText stuName,stuEmail,stuDepart,stuProg,stuGender,stuPhone,stuAddress;
    private ProgressBar progressBar;
    private Button updateBtn,deleteBtn;
    private DatabaseReference UsersRef;

    private AlertDialog.Builder builder;

    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_details);

        initAll();
        settingDatainUI();
        settingUPListners();
    }

    private void settingDatainUI() {
        stuName.setText(student.getName());
        stuEmail.setText(student.getEmail());
        stuDepart.setText(student.getDepartment());
        stuProg.setText(student.getProgram());
        stuGender.setText(student.getGender());
        stuPhone.setText(student.getMobile());
        stuAddress.setText(student.getAddress());
    }

    private void settingUPListners() {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name,Depart,Prog,Phone,Address;

                Name = stuName.getText().toString().trim();
                Depart = stuDepart.getText().toString().trim();
                Prog = stuProg.getText().toString().trim();
                Phone = stuPhone.getText().toString().trim();
                Address = stuAddress.getText().toString().trim();

                if(TextUtils.isEmpty(Name))
                {
                    stuName.setError("Enter name");
                }
                else if(TextUtils.isEmpty(Depart)){
                    stuDepart.setError("Enter Department");
                }
                else if(TextUtils.isEmpty(Prog)){
                    stuProg.setError("Enter program");
                }
                else if(TextUtils.isEmpty(Phone)){
                    stuPhone.setError("Enter Phone");
                }
                else if(TextUtils.isEmpty(Address)){
                    stuAddress.setError("Entered Address");
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);

                    UsersRef.child("name").setValue(Name);
                    UsersRef.child("department").setValue(Depart);
                    UsersRef.child("program").setValue(Prog);
                    UsersRef.child("mobile").setValue(Phone);
                    UsersRef.child("address").setValue(Address);

                    Toast.makeText(ViewStudentDetailsActivity.this, "Updated..!", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.GONE);

                    Intent intent=new Intent(ViewStudentDetailsActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ViewStudentDetailsActivity.this, "Delete Button", Toast.LENGTH_SHORT).show();
                        // Do nothing but close the dialog
                        UsersRef.removeValue();
                        dialog.dismiss();
                        Intent intent=new Intent(ViewStudentDetailsActivity.this,MainActivity.class);
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

    private void initAll() {

        student=new Student();
        if(getIntent().getSerializableExtra("Student")!=null){
            student=(Student) getIntent().getSerializableExtra("Student");
        }
        UsersRef= FirebaseDatabase.getInstance().getReference().child("Student").child(student.getStuentId());

        stuName=findViewById(R.id.view_stu_name);
        stuEmail=findViewById(R.id.view_stu_email);
        stuDepart=findViewById(R.id.view_stu_depart);
        stuProg=findViewById(R.id.view_stu_program);
        stuGender=findViewById(R.id.view_stu_gender);
        stuPhone=findViewById(R.id.view_stu_phone);
        stuAddress=findViewById(R.id.view_stu_address);
        updateBtn=findViewById(R.id.editUpdate_btn);
        deleteBtn=findViewById(R.id.delete_stu_btn);

        progressBar=findViewById(R.id.view_stu_progress);
        progressBar.setVisibility(View.GONE);

        //alert dailog
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
    }
}