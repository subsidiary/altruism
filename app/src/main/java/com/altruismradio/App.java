package com.altruismradio;

import android.app.Application;

import com.altruismradio.api.Api;

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Api.init();
    }
    
}
