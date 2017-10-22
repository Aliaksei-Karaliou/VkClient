package com.github.aliaksei_karaliou.vkclient.data.url;

import com.github.aliaksei_karaliou.utils.StringUtils;
import com.github.aliaksei_karaliou.vkclient.data.UserInfo;
import com.github.aliaksei_karaliou.vkclient.data.url.method.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class UrlBuilder {

    private static final String URL_TEMPLATE = "https://api.vk.com/method/%s?%s";

    private Method mMethod;
    private final Collection<String> mParameters = new ArrayList<>();
    @SuppressWarnings("MagicNumber")
    private double mApiVersion = 5.68;

    public UrlBuilder setMethod(final Method pMethod) {
        mMethod = pMethod;
        return this;
    }

    public UrlBuilder setApiVersion(final double pApiVersion) {
        mApiVersion = pApiVersion;
        return this;
    }

    @SuppressWarnings("WeakerAccess")
    public UrlBuilder addParameter(@UrlParameters final String pName, final Object pValue) {
        mParameters.add(pName + "=" + pValue);
        return this;
    }

    public UrlBuilder addParameter(@UrlParameters final String pName, final Object pValue, final Object... pValues) {
        final List<Object> iterable = new ArrayList<>(Arrays.asList(pValues));
        iterable.add(0, pValue);
        return addParameter(pName, StringUtils.glue(iterable, ","));
    }

    public String build() {
        addParameter(UrlParameters.ACCESS_TOKEN, UserInfo.getAccessToken());
        addParameter(UrlParameters.VERSION, mApiVersion);

        final String parameters = StringUtils.glue(mParameters, "&");
        return String.format(Locale.US, URL_TEMPLATE, mMethod.getValue(), parameters);
    }
}
