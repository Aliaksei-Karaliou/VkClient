package com.github.aliaksei_karaliou.vkclient.data.url.method;

public enum Users implements Method {
    GET("users.get");

    private final String mValue;

    Users(final String pValue) {
        mValue = pValue;
    }

    @Override
    public String getValue() {
        return mValue;
    }

}
