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
import android.widget.Toast;

import com.example.canteenautomation.Models.Canteen;
import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewCanteenDetailsActivity extends AppCompatActivity {
    private EditText CanEmail,CanManagmentName,CanHandler,CanMobile,CanNoOfWorkers,CanAddress;
    private ProgressBar progressBar;
    private Button updateBtn,deleteBtn;
    private DatabaseReference UsersRef;

    private AlertDialog.Builder builder;

    Canteen canteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_canteen_details);

        initAll();
        settingDatainUI();
        settingUPListners();
    }

    private void settingUPListners() {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String managmentName,handler,mobile,noOfWorkers,address;

                managmentName = CanManagmentName.getText().toString().trim();
                handler = CanHandler.getText().toString().trim();
                mobile = CanMobile.getText().toString().trim();
                noOfWorkers = CanNoOfWorkers.getText().toString().trim();
                address = CanAddress.getText().toString().trim();

                if(TextUtils.isEmpty(managmentName))
                {
                    CanManagmentName.setError("Enter name");
                }
                else if(TextUtils.isEmpty(handler)){
                    CanHandler.setError("Enter Department");
                }
                else if(TextUtils.isEmpty(mobile)){
                    CanMobile.setError("Enter program");
                }
                else if(TextUtils.isEmpty(noOfWorkers)){
                    CanNoOfWorkers.setError("Enter Phone");
                }
                else if(TextUtils.isEmpty(address)){
                    CanAddress.setError("Entered Address");
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);

                    UsersRef.child("managmentName").setValue(managmentName);
                    UsersRef.child("handler").setValue(handler);
                    UsersRef.child("mobile").setValue(mobile);
                    UsersRef.child("noOfWorkers").setValue(noOfWorkers);
                    UsersRef.child("address").setValue(address);

                    Toast.makeText(ViewCanteenDetailsActivity.this, "Updated..!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Intent intent=new Intent(ViewCanteenDetailsActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        UsersRef.removeValue();
                        dialog.dismiss();
                        Intent intent=new Intent(ViewCanteenDetailsActivity.this,MainActivity.class);
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

    private void settingDatainUI() {
        CanEmail.setText(canteen.getEmail());
        CanManagmentName.setText(canteen.getManagmentName());
        CanHandler.setText(canteen.getHandler());
        CanMobile.setText(canteen.getMobile());
        CanNoOfWorkers.setText(canteen.getNoOfWorkers());
        CanAddress.setText(canteen.getAddress());
    }

    private void initAll() {
        canteen=new Canteen();
        if(getIntent().getSerializableExtra("Canteen")!=null){
            canteen=(Canteen) getIntent().getSerializableExtra("Canteen");
        }
        UsersRef= FirebaseDatabase.getInstance().getReference().child("Canteen").child(canteen.getCanteenId());

        CanEmail=findViewById(R.id.view_canteen_email);
        CanManagmentName=findViewById(R.id.view_canteen_management);
        CanHandler=findViewById(R.id.view_canteen_handler);
        CanNoOfWorkers=findViewById(R.id.view_canteen_workers);
        CanAddress=findViewById(R.id.view_canteen_address);
        CanMobile=findViewById(R.id.view_canteen_phone);

        updateBtn=findViewById(R.id.can_editUpdate_btn);
        deleteBtn=findViewById(R.id.delete_can_btn);

        progressBar=findViewById(R.id.view_can_progress);
        progressBar.setVisibility(View.GONE);

        //alert dailog
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
    }
}