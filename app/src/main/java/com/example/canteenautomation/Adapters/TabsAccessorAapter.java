package com.example.canteenautomation.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.canteenautomation.Fragments.CanteenFragment;
import com.example.canteenautomation.Fragments.StudentFragment;

public class TabsAccessorAapter extends FragmentPagerAdapter {

    public TabsAccessorAapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                StudentFragment studentFragment=new StudentFragment();
                return studentFragment;
            case 1:
                CanteenFragment canteenFragment=new CanteenFragment();
                return canteenFragment;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Students";
            case 1:
                return "Canteen";

            default:
                return null;
        }
    }
}
