package com.github.aliaksei_karaliou.vkclient.data.url;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        UrlParameters.ACCESS_TOKEN,
        UrlParameters.FIELDS,
        UrlParameters.OFFSET,
        UrlParameters.USER_IDS,
        UrlParameters.VERSION})
public @interface UrlParameters {

    String ACCESS_TOKEN = "access_token";
    String BODY = "body";
    String DATE = "date";
    String FIRST_NAME = "first_name";
    String FIELDS = "fields";
    String ID = "id";
    String ITEMS = "items";
    String LAST_NAME = "last_name";
    String MESSAGE = "message";
    String OFFSET = "offset";
    String ONLINE = "online";
    String PHOTO_MAX = "photo_max";
    String RESPONSE = "response";
    String USER_ID = "user_id";
    String USER_IDS = "user_ids";
    String VERSION = "v";

}
