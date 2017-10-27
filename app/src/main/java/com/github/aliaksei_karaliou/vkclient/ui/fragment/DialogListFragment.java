package com.github.aliaksei_karaliou.vkclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.aliaksei_karaliou.vkclient.R;
import com.github.aliaksei_karaliou.vkclient.data.manager.MessageManager;
import com.github.aliaksei_karaliou.vkclient.ui.adapter.DialogListAdapter;

public class DialogListFragment extends BaseFragment {

    private MessageManager mMessageManager;

    @Override
    public Integer getFragmentLayout() {
        return R.layout.fragment_dialog_list;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RecyclerView recycler = findViewById(R.id.fragment_dialog_list_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);

                final int lastVisible = ((LinearLayoutManager) recycler.getLayoutManager()).findLastCompletelyVisibleItemPosition();

                final DialogListAdapter adapter = (DialogListAdapter) recycler.getAdapter();

                if (lastVisible > 0 && lastVisible + 1 == adapter.getMessages().size()) {
                    mMessageManager.getDialogs(getContext(), lastVisible + 1, adapter::add, null);
                }
            }
        });

        mMessageManager = new MessageManager();
        mMessageManager.getDialogs(getContext(), pData -> {
            final DialogListAdapter adapter = new DialogListAdapter(getContext(), pData);
            recycler.setAdapter(adapter);
        }, null);
    }

    public <T extends View> T findViewById(final int id) {
        return getView() != null ? getView().findViewById(id) : null;
    }
}
