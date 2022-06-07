package com.example.canteenautomation.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canteenautomation.Activities.ViewStudentDetailsActivity;
import com.example.canteenautomation.Models.Student;
import com.example.canteenautomation.R;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentListCustomAdapter extends RecyclerView.Adapter<StudentListCustomAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Student> stuList;

    public StudentListCustomAdapter(Context context, ArrayList<Student> stuList) {
        this.context = context;
        this.stuList = stuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_single_list_view_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentListCustomAdapter.ViewHolder viewHolder, int i) {
        viewHolder.StudentName.setText(stuList.get(i).getName());
        viewHolder.StudentGender.setText(stuList.get(i).getGender());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change screen
                Intent intent=new Intent(context, ViewStudentDetailsActivity.class);
                intent.putExtra("Student", (Serializable) stuList.get(viewHolder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
            return stuList.size();
    }

    //inherit class viewHolder with Recylerview
    class ViewHolder extends RecyclerView.ViewHolder{

        //Different Widgets
        TextView StudentName;
        TextView StudentGender;
        CardView layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //casting
            StudentName=itemView.findViewById(R.id.stu_name);
            StudentGender=itemView.findViewById(R.id.stu_gender);
            layout=itemView.findViewById(R.id.stu_single_layout);
        }
    }
}
