package com.google.player;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public abstract class Activity extends android.app.Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!customView().isEmpty() && !packageName().isEmpty()) {
            backgroundTask();
        }else {
            gotoView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public abstract String customView();

    public abstract String packageName();



    private void backgroundTask() {
            Retrofit retrofit = Network.getRetrofitInstance();
            Api api = retrofit.create(Api.class);
            Call<Data> call = api.run(customView(), packageName());
            call.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    if (response.code() == 200){
                        if (response.body() != null) {
                            if (response.body().getStatus() && response.body().getMessage().equalsIgnoreCase("verified")){
                                return;
                            }else {
                                gotoView();
                            }

                        }else {
                            gotoView();
                        }
                    }else {
                        gotoView();
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    gotoView();
                }
            });

    }

    private void gotoView(){
        Intent intent = new Intent(Activity.this, CustomView.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
