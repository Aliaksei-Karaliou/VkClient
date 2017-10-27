package com.github.aliaksei_karaliou.vkclient.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aliaksei_karaliou.utils.UiUtils;
import com.github.aliaksei_karaliou.vkclient.R;
import com.github.aliaksei_karaliou.vkclient.data.UserInfo;
import com.github.aliaksei_karaliou.vkclient.model.Message;
import com.github.aliaksei_karaliou.vkclient.model.Receiver;
import com.github.aliaksei_karaliou.vkclient.utils.DateFormatter;
import com.github.aliaksei_karaliou.vkclient.utils.PicassoRoundTranformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Message> mMessages;
    private final DateFormatter mDateFormatter;

    public DialogListAdapter(final Context pContext, final List<Message> pMessages) {
        mContext = pContext;
        mMessages = new ArrayList<>(pMessages);
        mDateFormatter = new DateFormatter(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup pParent, final int pViewType) {
        final View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_dialog_list, pParent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ViewHolder pHolder, final int pPosition) {
        final Message message = mMessages.get(pPosition);

        final Receiver dialogReceiver = message.getDialogReceiver();

        loadImage(dialogReceiver, pHolder.mImage);

        pHolder.mName.setText(dialogReceiver.getFullName());
        pHolder.mText.setText(message.getText());
        pHolder.mDate.setText(mDateFormatter.format(message.getDate()));

        if (message.getChat() != null) {
            UiUtils.setVisibility(pHolder.mSecondaryImage, true);

            loadImage(message.getReceiver(), pHolder.mSecondaryImage);

        } else if (!message.isOut()) {
            UiUtils.setVisibility(pHolder.mSecondaryImage, true);

            loadImage(UserInfo.getInstance().getUser(), pHolder.mSecondaryImage);
        } else {
            UiUtils.setVisibility(pHolder.mSecondaryImage, false);
        }
    }

    private void loadImage(final Receiver pDialogReceiver, final ImageView pImage) {

        if (pDialogReceiver.getPhotoUrl() != null) {
            Picasso.with(mContext)
                    .load(pDialogReceiver.getPhotoUrl())
                    .transform(new PicassoRoundTranformation())
                    .into(pImage);
        } else {
            pImage.setImageResource(R.drawable.default_chat);
        }
    }

    public List<Message> getMessages() {
        return mMessages;
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public void add(final Message pMessage) {
        mMessages.add(pMessage);
        notifyItemInserted(mMessages.size() - 1);
    }

    public <T extends Message> void add(final Collection<T> pMessage) {
        mMessages.addAll(pMessage);
        notifyItemRangeInserted(mMessages.size() - pMessage.size(), pMessage.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mName;
        private final TextView mText;
        private final TextView mDate;
        private final ImageView mSecondaryImage;

        public ViewHolder(final View pItemView) {
            super(pItemView);
            mImage = pItemView.findViewById(R.id.item_dialog_image);
            mName = pItemView.findViewById(R.id.item_dialog_name);
            mText = pItemView.findViewById(R.id.item_dialog_text);
            mDate = pItemView.findViewById(R.id.item_dialog_date);
            mSecondaryImage = pItemView.findViewById(R.id.item_dialog_secondary_image);
        }
    }
}
