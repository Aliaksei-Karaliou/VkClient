package com.github.aliaksei_karaliou.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JsonIterable implements Iterable<String>, Iterator<String> {

    private final JSONArray mArray;
    private int mPosition;

    public JsonIterable(final JSONArray pArray) {
        mArray = pArray;
    }

    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return mPosition < mArray.length();
    }

    @Override
    public String next() {
        try {
            final Object o = mArray.get(mPosition);
            mPosition++;
            return o.toString();
        } catch (final JSONException pE) {
            throw new ClassCastException("Json Iterable failed");
        }
    }
}
