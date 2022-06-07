package com.example.canteenautomation.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteenautomation.Adapters.StudentListCustomAdapter;
import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewStudentActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;

    //Data Structures
    private ArrayList<Student> studentList;
//    private ArrayList<AppointmentModel> appointmentModelsList;

    private Student currentStudent;


    //Firebase
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference rootRef;
    private FirebaseAuth mAuth;
    private String currentUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        initAll();
        initRecyclerView();
    }
    private void initRecyclerView() {

            //declare and initialize adapter
            final StudentListCustomAdapter studentListCustomAdapter = new StudentListCustomAdapter(ViewStudentActivity.this, studentList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewStudentActivity.this);
            mainRecyclerView.setLayoutManager(layoutManager);
            mainRecyclerView.setAdapter(studentListCustomAdapter);

            //access child and add listner
            rootRef.child("Student").addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Student student = new Student();
                            student = dataSnapshot1.getValue(Student.class);
                            studentList.add(student);
                            studentListCustomAdapter.notifyDataSetChanged();
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }

    private void initAll() {
        //casting variables
        mainRecyclerView=(RecyclerView)findViewById(R.id.main_screen_recyclerview);
        //initialize variables
        studentList=new ArrayList<Student>();
        mAuth=FirebaseAuth.getInstance();
        rootRef= FirebaseDatabase.getInstance().getReference();
        //get user id
        currentUserId=mAuth.getCurrentUser().getUid();
    }
}