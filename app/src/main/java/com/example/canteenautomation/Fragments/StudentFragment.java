package com.example.canteenautomation.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.canteenautomation.Activities.AddNewStudentActivity;
import com.example.canteenautomation.Activities.DepositeAmountActivity;
import com.example.canteenautomation.Activities.LoginActivity;
import com.example.canteenautomation.Activities.StartActivity;
import com.example.canteenautomation.Activities.ViewStudentActivity;
import com.example.canteenautomation.Activities.WithdrawActivity;
import com.example.canteenautomation.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class StudentFragment extends Fragment {
    private View GroupFragmentView;
    private ImageView addStudent,deleteStudent,viewStudent,updateStudent,depositeAmount,withdrawAmount;

   public StudentFragment(){
       //Required empty constructor
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        GroupFragmentView=inflater.inflate(R.layout.fragment_student, container, false);
        initAll();
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AddNewStudentActivity.class);
                startActivity(intent);
            }
        });
        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ViewStudentActivity.class);
                startActivity(intent);
            }
        });
        depositeAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), DepositeAmountActivity.class);
                startActivity(intent);
            }
        });
        withdrawAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), WithdrawActivity.class);
                startActivity(intent);
            }
        });

        return GroupFragmentView;
    }

    private void initAll() {
       addStudent=GroupFragmentView.findViewById(R.id.stu_add);
       deleteStudent=GroupFragmentView.findViewById(R.id.stu_delete);
       viewStudent=GroupFragmentView.findViewById(R.id.stu_view);
       updateStudent=GroupFragmentView.findViewById(R.id.stu_update);
       depositeAmount=GroupFragmentView.findViewById(R.id.stu_deposit);
       withdrawAmount=GroupFragmentView.findViewById(R.id.stu_withdraw);

    }
}