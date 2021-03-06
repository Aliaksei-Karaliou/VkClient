package com.github.aliaksei_karaliou.vkclient.data.url.method;

public enum Messages implements Method {
    GET_DIALOGS("messages.getDialogs"),
    GET_CHAT("messages.getChat");

    private final String mValue;

    Messages(final String pValue) {
        mValue = pValue;
    }

    @Override
    public String getValue() {
        return mValue;
    }

}
