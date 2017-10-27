package com.github.aliaksei_karaliou.vkclient.model;

import java.util.Date;

public interface Message {

    String getText();

    Date getDate();

    Receiver getReceiver();

    long getId();

    Chat getChat();

    Receiver getDialogReceiver();

    boolean isOut();

    final class Impl implements Message {

        private final String mText;
        private final Date mDate;
        private final Receiver mReceiver;
        private final long mId;
        private final Chat mChat;
        private final boolean mOut;

        private Impl(final Builder pBuilder) {
            mText = pBuilder.mText;
            mDate = new Date(pBuilder.mTimestamp * 1000);
            mReceiver = pBuilder.mReceiver;
            mId = pBuilder.mId;
            mChat = pBuilder.mChat;
            mOut = pBuilder.mOut;
        }

        @Override
        public String getText() {
            return mText;
        }

        @Override
        public Date getDate() {
            return mDate;
        }

        @Override
        public Receiver getReceiver() {
            return mReceiver;
        }

        @Override
        public long getId() {
            return mId;
        }

        @Override
        public Chat getChat() {
            return mChat;
        }

        @Override
        public Receiver getDialogReceiver() {
            return mChat != null ? mChat : mReceiver;
        }

        @Override
        public boolean isOut() {
            return mOut;
        }
    }

    class Builder {

        private String mText;
        private long mTimestamp;
        private Receiver mReceiver;
        private long mId;
        private Chat mChat;
        private boolean mOut;

        public Builder setText(final String pText) {
            mText = pText;
            return this;
        }

        public Builder setTimestamp(final long pTimestamp) {
            mTimestamp = pTimestamp;
            return this;
        }

        public Builder setReceiver(final Receiver pReceiver) {
            mReceiver = pReceiver;
            return this;
        }

        public Builder setId(final long pId) {
            mId = pId;
            return this;
        }

        public Builder setChat(final Chat pChat) {
            mChat = pChat;
            return this;
        }

        public Builder setOut(final boolean pOut) {
            mOut = pOut;
            return this;
        }

        public Message build() {
            return new Impl(this);
        }
    }

}
