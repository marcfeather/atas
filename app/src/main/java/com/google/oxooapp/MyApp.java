package com.google.oxooapp;



import com.google.player.Application;


public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public String customView() {
        return "purchaseCod";
    }

    @Override
    public String packageName() {
        return null;
    }


}
