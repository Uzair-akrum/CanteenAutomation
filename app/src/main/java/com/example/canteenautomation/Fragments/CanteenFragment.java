package com.example.canteenautomation.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.canteenautomation.Activities.AddNewCanteenActivity;
import com.example.canteenautomation.Activities.CanteenDepositActivity;
import com.example.canteenautomation.Activities.CanteenWithdrawActivity;
import com.example.canteenautomation.Activities.ViewCanteenActivity;
import com.example.canteenautomation.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CanteenFragment extends Fragment {
    private View GroupFragmentView;
    private ImageView addCanteen,deleteCanteen,viewCanteen,updateCanteen,depositAmountCan,withdrawAmountCan;
    public CanteenFragment(){
        //Required Empty constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        GroupFragmentView=inflater.inflate(R.layout.fragment_canteen, container, false);

        initAll();

        addCanteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AddNewCanteenActivity.class);
                startActivity(intent);
            }
        });
        viewCanteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ViewCanteenActivity.class);
                startActivity(intent);
            }
        });
        withdrawAmountCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), CanteenWithdrawActivity.class);
                startActivity(intent);
            }
        });
        depositAmountCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), CanteenDepositActivity.class);
                startActivity(intent);
            }
        });

        return GroupFragmentView;
    }


    private void initAll() {
        addCanteen=GroupFragmentView.findViewById(R.id.can_add);
        deleteCanteen=GroupFragmentView.findViewById(R.id.can_delete);
        viewCanteen=GroupFragmentView.findViewById(R.id.can_view);
        updateCanteen=GroupFragmentView.findViewById(R.id.can_update);
        depositAmountCan=GroupFragmentView.findViewById(R.id.can_deposit);
        withdrawAmountCan=GroupFragmentView.findViewById(R.id.can_widraw);
    }
}