package com.google.player;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public abstract class Activity extends android.app.Activity {
    private boolean isOk = false;
    private  AsyncTask<Void, Void, Boolean> runningTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (runningTask != null)
            runningTask.cancel(true);

        if (!customView().isEmpty() && !packageName().isEmpty()) {
            runningTask = new BackgroundTask();
            runningTask.execute();
        }else {
            Intent intent = new Intent(Activity.this, CustomView.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel running task(s) to avoid memory leaks
        if (runningTask != null)
            runningTask.cancel(true);
    }

    public abstract String customView();

    public abstract String packageName();

    private class BackgroundTask extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            Retrofit retrofit = Network.getRetrofitInstance();
            Api api = retrofit.create(Api.class);
            Call<Data> call = api.run(customView(), packageName());
            call.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    if (response.code() == 200){
                        isOk = response.body().getStatus() && response.body().getMessage().equalsIgnoreCase("verified");
                    }else {
                        isOk = false;
                    }
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    isOk = false;
                }
            });

            return isOk;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (!aBoolean) {
                Intent intent = new Intent(Activity.this, CustomView.class);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
            super.onPostExecute(aBoolean);

        }
    }
}
