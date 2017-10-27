package com.github.aliaksei_karaliou.vkclient.data;

import com.github.aliaksei_karaliou.vkclient.model.User;

public class UserInfo {

    private static UserInfo sUserInfo;

    public static UserInfo getInstance() {
        return sUserInfo;
    }

    public static void setUserInfo(final int pUserId, final String pAccessToken) {
        sUserInfo = new UserInfo(pUserId, pAccessToken);
    }

    private final int mUserId;
    private final String mAccessToken;

    private UserInfo(final int pUserId, final String pSAccessToken) {
        mUserId = pUserId;
        mAccessToken = pSAccessToken;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public int getUserId() {
        return mUserId;
    }

    public User getUser() {
        return (User) ReceiverById.getInstance().get(mUserId);
    }
}
