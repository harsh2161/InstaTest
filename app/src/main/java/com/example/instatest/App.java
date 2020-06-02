package com.example.instatest;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("DPbt8UfatPcKsGm2DtnB2NULdQab12Ve8ihinpCd")
        .clientKey("ShsE97Uni8q7416JpnvTbvvwvCQWD3ISOw6anR7Q")
        .server("https://parseapi.back4app.com/")
        .build());
    }
}
