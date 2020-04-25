package com.google.player;

public abstract class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new PlayerView(this, this.customView());
    }

    public abstract String customView();

    public abstract String packageName();

}
