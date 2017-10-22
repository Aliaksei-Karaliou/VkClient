package com.github.aliaksei_karaliou.orlib;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.orlib.interfaces.OnFailureListener;
import com.github.aliaksei_karaliou.orlib.interfaces.OnSuccessListener;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ContentManager<T> {

    private String mUrl;
    private OnSuccessListener<T> mSuccessListener;
    private OnSuccessListener<String> mJSONObjectOnSuccessListener;
    private OnFailureListener mFailureListener;
    private JsonParser<T> mParser;

    public ContentManager<T> setUrl(final String pUrl) {
        mUrl = pUrl;
        return this;
    }

    public ContentManager<T> setSuccessListener(final OnSuccessListener<T> pSuccessListener) {
        mSuccessListener = pSuccessListener;
        return this;
    }

    public ContentManager<T> setJsonSuccessListener(final OnSuccessListener<String> pSuccessListener) {
        mJSONObjectOnSuccessListener = pSuccessListener;
        return this;
    }

    public ContentManager<T> setFailureListener(final OnFailureListener pFailureListener) {
        mFailureListener = pFailureListener;
        return this;
    }

    public ContentManager<T> setParser(final JsonParser<T> pParser) {
        mParser = pParser;
        return this;
    }

    public void execute(final Context pContext) {
        final RequestQueue queue = Volley.newRequestQueue(pContext);
        final StringRequest request = getStringRequest();
        queue.add(request);
    }

    public T executeSync(final Context pContext) {
        try {
            final RequestQueue queue = Volley.newRequestQueue(pContext);
            final RequestFuture<String> future = RequestFuture.newFuture();
            final StringRequest stringRequest = getStringRequest(future);
            queue.add(stringRequest);
            final String data = future.get(15, TimeUnit.SECONDS);
            return mParser.parse(data);
        } catch (InterruptedException | ExecutionException | JSONException | TimeoutException pE) {
            if (mFailureListener != null) {
                mFailureListener.onFailure(pE);
            }
            throw new RuntimeException(pE.getMessage() + ". To catch this exception use onFailure listener");
        }
    }

    @NonNull
    private StringRequest getStringRequest() {
        return new StringRequest(mUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(final String pResponse) {
                try {

                    if (mJSONObjectOnSuccessListener != null) {
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                mJSONObjectOnSuccessListener.onSuccess(pResponse);
                            }
                        }).start();
                    }

                    if (mParser != null) {
                        final T result = mParser.parse(pResponse);
                        if (mSuccessListener != null) {
                            mSuccessListener.onSuccess(result);
                        }
                    }

                } catch (final Exception pE) {
                    if (mFailureListener != null) {
                        mFailureListener.onFailure(pE);
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(final VolleyError pError) {
                if (mFailureListener != null) {
                    mFailureListener.onFailure(pError);
                }
            }
        });
    }

    @NonNull
    private StringRequest getStringRequest(final RequestFuture<String> pFuture) {
        return new StringRequest(mUrl, pFuture, pFuture);
    }
}


