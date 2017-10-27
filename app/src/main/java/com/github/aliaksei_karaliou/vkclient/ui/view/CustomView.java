package com.github.aliaksei_karaliou.vkclient.ui.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class CustomView extends RelativeLayout {

    public CustomView(@NonNull final Context pContext) {
        super(pContext);
        inflate(pContext, getViewLayout(), this);
    }

    public CustomView(final Context pContext, final AttributeSet pAttrs) {
        super(pContext, pAttrs);
        inflate(pContext,getViewLayout());
    }

    public CustomView(final Context pContext, final AttributeSet pAttrs, final int pDefStyleAttr) {
        super(pContext, pAttrs, pDefStyleAttr);
        inflate(pContext,getViewLayout());
    }

    private View inflate(final Context pContext, @LayoutRes final int pViewLayout) {
        return inflate(pContext, pViewLayout, this);
    }

    @LayoutRes
    public abstract int getViewLayout();
}
