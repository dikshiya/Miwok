package com.example.miwokself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabTiles);
        tabLayout.setupWithViewPager(viewPager);
    }
}