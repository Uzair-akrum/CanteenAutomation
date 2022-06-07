package com.example.canteenautomation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class DepositeAmountActivity extends AppCompatActivity {
    private EditText email,depositeAmount;
    private Button depositBtn;
    private ProgressBar progressBar;
    private DatabaseReference UsersRef;

    private DatabaseReference databaseReference;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposite_amount);
        initAll();

        depositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail=email.getText().toString().trim();
                final String textAmount=depositeAmount.getText().toString().trim();

                if(TextUtils.isEmpty(textEmail)){
                    email.setError("Please Enter valid email");
                }
                else if(TextUtils.isEmpty(textAmount) && Integer.parseInt(textAmount) > 0){
                    depositeAmount.setError("Plase Enter Amount");
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");
                    Query query = databaseReference.orderByChild("email").equalTo(textEmail);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    student = dataSnapshot1.getValue(Student.class);
                                    String stuId=student.getStuentId();
                                    int oldbalance= Integer.parseInt(student.getBalance());
                                    int newBalance= oldbalance + (Integer.parseInt(textAmount));
                                    UsersRef= FirebaseDatabase.getInstance().getReference().child("Student").child(stuId);
                                    UsersRef.child("balance").setValue(String.valueOf(newBalance));
                                }
                                Toast.makeText(DepositeAmountActivity.this, "Amount Added..!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                Toast.makeText(DepositeAmountActivity.this, "Email not found..!", Toast.LENGTH_SHORT).show();
                            }
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            throw databaseError.toException(); // don't ignore errors
                        }
                    });
                }

            }
        });
    }

    private void initAll() {
        email=findViewById(R.id.deposit_email);
        depositeAmount=findViewById(R.id.deposit_amount);
        depositBtn=findViewById(R.id.deposit_btn);
        progressBar=findViewById(R.id.deposit_progressBar);
        progressBar.setVisibility(View.GONE);

        student=new Student();
    }
}