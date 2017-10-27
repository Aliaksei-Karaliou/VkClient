package com.github.aliaksei_karaliou.vkclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater pInflater, @Nullable final ViewGroup pContainer, @Nullable final Bundle pSavedInstanceState) {
        final Integer fragmentLayout = getFragmentLayout();

        if (fragmentLayout != null) {
            return pInflater.inflate(fragmentLayout, pContainer, false);
        } else {
            return super.onCreateView(pInflater, pContainer, pSavedInstanceState);
        }
    }

    @Nullable
    @LayoutRes
    public abstract Integer getFragmentLayout();
}
