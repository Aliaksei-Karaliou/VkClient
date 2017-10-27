package com.github.aliaksei_karaliou.vkclient;

import android.app.Application;

import com.github.aliaksei_karaliou.vkclient.data.UserInfo;

public class VkClientApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            UserInfo.setUserInfo(127804881, "4756d16bccc24064888915c4faaebad5b10e27be0f52e084669ee3cee23d49ce0959b54c944cf4ab8cc16");
        }
    }
}
