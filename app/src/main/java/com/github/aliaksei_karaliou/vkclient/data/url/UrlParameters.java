package com.github.aliaksei_karaliou.vkclient.data.url;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        UrlParameters.ACCESS_TOKEN,
        UrlParameters.CHAT_IDS,
        UrlParameters.FIELDS,
        UrlParameters.GROUP_IDS,
        UrlParameters.OFFSET,
        UrlParameters.USER_IDS,
        UrlParameters.VERSION})
public @interface UrlParameters {

    String ACCESS_TOKEN = "access_token";
    String BODY = "body";
    String CHAT_ID = "chat_id";
    String CHAT_IDS = "chat_ids";
    String DATE = "date";
    String FIRST_NAME = "first_name";
    String FIELDS = "fields";
    String GROUP_IDS = "group_ids";
    String ID = "id";
    String ITEMS = "items";
    String LAST_NAME = "last_name";
    String MESSAGE = "message";
    String NAME = "name";
    String OFFSET = "offset";
    String ONLINE = "online";
    String OUT = "out";
    String PHOTO_200 = "photo_200";
    String PHOTO_MAX = "photo_max";
    String TITLE = "title";
    String RESPONSE = "response";
    String USER_ID = "user_id";
    String USER_IDS = "user_ids";
    String VERSION = "v";

}
