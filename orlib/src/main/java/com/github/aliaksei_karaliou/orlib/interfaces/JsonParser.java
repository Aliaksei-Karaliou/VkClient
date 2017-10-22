package com.github.aliaksei_karaliou.orlib.interfaces;

import org.json.JSONException;

public interface JsonParser<T> {

    T parse(String pJson) throws JSONException;
}
