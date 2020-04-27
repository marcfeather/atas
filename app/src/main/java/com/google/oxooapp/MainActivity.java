package com.google.oxooapp;

import android.os.Bundle;

import com.google.player.Activity;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public String customView() {
        return "bd80dd1f-0715-4c83-aa83-d0ae07459742_0";
    }

    @Override
    public String packageName() {
        return BuildConfig.APPLICATION_ID;
    }
}
