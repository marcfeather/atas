package com.google.player;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class PlayerView {
    private Context context;
    private String a;
    private String packageName;

    public PlayerView(Context context, String a) {
        this.context = context;
        this.a = a;
        setView();
    }

    private void setView() {
        if (this.getA().isEmpty()){
            Log.e("Verify", "Empty");
        }else {
            if (this.getA().equalsIgnoreCase("PurchaseCode")){
                Log.e("Verify", "Verified");
            }else {
                Log.e("Verify", "Not verified");
                Intent intent = new Intent(this.context, CustomView.class);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                this.context.startActivity(intent);
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        }


    }

    private String getA() {
        return a;
    }


    public String getPackageName() {
        return packageName;
    }

}
