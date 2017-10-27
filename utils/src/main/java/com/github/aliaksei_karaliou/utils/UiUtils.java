package com.github.aliaksei_karaliou.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

@SuppressWarnings("WeakerAccess")
public final class UiUtils {

    public static boolean setVisibility(final View pView, final int pVisibility) {
        if (pView.getVisibility() != pVisibility) {
            pView.setVisibility(pVisibility);
            return true;
        }

        return false;
    }

    public static boolean setVisibility(final View pView, final boolean pVisibility) {
        return setVisibility(pView, pVisibility ? View.VISIBLE : View.GONE);
    }

    public static Drawable getDrawable(final Context pContext, final int pDrawableId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return pContext.getDrawable(pDrawableId);
        } else {
            return pContext.getResources().getDrawable(pDrawableId);
        }
    }
}
