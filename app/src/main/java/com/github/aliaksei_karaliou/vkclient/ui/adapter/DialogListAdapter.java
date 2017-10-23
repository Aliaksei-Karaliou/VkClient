package com.github.aliaksei_karaliou.vkclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aliaksei_karaliou.vkclient.R;
import com.github.aliaksei_karaliou.vkclient.model.Message;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Message> mMessages;

    public DialogListAdapter(final Context pContext, final List<Message> pMessages) {
        mContext = pContext;
        mMessages = new ArrayList<>(pMessages);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup pParent, final int pViewType) {
        final View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_dialog_list, pParent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ViewHolder pHolder, final int pPosition) {
        final Message message = mMessages.get(pPosition);

        Picasso.with(mContext)
                .load(message.getReceiver().getPhotoUrl())
                .into(pHolder.mImage);

        pHolder.mName.setText(message.getDialogReceiver().getFullName());
        pHolder.mText.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mName;
        private final TextView mText;

        public ViewHolder(final View pItemView) {
            super(pItemView);
            mImage = pItemView.findViewById(R.id.item_dialog_image);
            mName = pItemView.findViewById(R.id.item_dialog_name);
            mText = pItemView.findViewById(R.id.item_dialog_text);
        }
    }
}
