package com.example.miwokself;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private Context mcontext;
    public  FragmentAdapter(Context context, FragmentManager fm){
        super(fm);
        mcontext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new NumbersFragment();
        }
        else if (position==1){
            return new FamilyFragment();
        }
        else if (position==2){
            return new ColorsFragment();
        }
        else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "Numbers";
        }
        else if (position==1){
            return "Family";
        }
        else if (position==2){
            return "Colors";
        }
        else {
            return "Phrases";
        }
    }
}
