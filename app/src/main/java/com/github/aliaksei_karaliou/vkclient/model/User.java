package com.github.aliaksei_karaliou.vkclient.model;

public interface User extends Receiver {

    String getFirstName();

    String getLastName();

    boolean isOnline();

    final class Impl implements User {

        private final String mFirstName;
        private final String mLastName;
        private final String mPhotoUrl;
        private final long mId;
        private final boolean mOnline;

        private Impl(final Builder pBuilder) {
            mId = pBuilder.mId;
            mFirstName = pBuilder.mFirstName;
            mLastName = pBuilder.mLastName;
            mPhotoUrl = pBuilder.mPhotoUrl;
            mOnline=pBuilder.mOnline;
        }

        @Override
        public String getFirstName() {
            return mFirstName;
        }

        @Override
        public String getLastName() {
            return mLastName;
        }

        @Override
        public boolean isOnline() {
            return mOnline;
        }

        @Override
        public String getPhotoUrl() {
            return mPhotoUrl;
        }

        @Override
        public long getPeerId() {
            return mId;
        }

        @Override
        public String getFullName() {
            return mFirstName + " " + mLastName;
        }

        @Override
        public long getId() {
            return mId;
        }
    }

    class Builder {

        private String mFirstName;
        private String mLastName;
        private String mPhotoUrl;
        private long mId;
        private boolean mOnline;

        public Builder setFirstName(final String pFirstName) {
            mFirstName = pFirstName;
            return this;
        }

        public Builder setLastName(final String pLastName) {
            mLastName = pLastName;
            return this;
        }

        public Builder setPhotoUrl(final String pPhotoUrl) {
            mPhotoUrl = pPhotoUrl;
            return this;
        }

        public Builder setId(final long pId) {
            mId = pId;
            return this;
        }

        public Builder setOnline(final boolean pOnline) {
            mOnline = pOnline;
            return this;
        }

        public User build() {
            return new Impl(this);
        }
    }
}
