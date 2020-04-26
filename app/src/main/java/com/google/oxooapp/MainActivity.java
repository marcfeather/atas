package com.google.oxooapp;

import android.os.Bundle;

import com.google.player.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public String customView() {
        return "code";
    }

    @Override
    public String packageName() {
        return BuildConfig.APPLICATION_ID;
    }
}
