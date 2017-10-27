package com.github.aliaksei_karaliou.vkclient.data.url.method;

public enum Groups implements Method {
    GET_BY_ID("groups.getById");

    private final String mValue;

    Groups(final String pValue) {
        mValue = pValue;
    }

    @Override
    public String getValue() {
        return mValue;
    }
}
