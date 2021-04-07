package com.example.miwokself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_category);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerr, new NumbersFragment())
                .commit();

    }
}