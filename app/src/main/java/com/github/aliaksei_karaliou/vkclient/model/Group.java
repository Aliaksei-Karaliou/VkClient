package com.github.aliaksei_karaliou.vkclient.model;

public interface Group extends Receiver {

    final class Impl implements Group {

        private final String mFullName;
        private final String mPhotoUrl;
        private final long mId;

        private Impl(final Builder pBuilder) {
            mFullName = pBuilder.mFullName;
            mPhotoUrl = pBuilder.mPhotoUrl;
            mId = pBuilder.mId;
        }

        @Override
        public String getFullName() {
            return mFullName;
        }

        @Override
        public String getPhotoUrl() {
            return mPhotoUrl;
        }

        @Override
        public long getPeerId() {
            return -mId;
        }

        @Override
        public long getId() {
            return mId;
        }
    }

    class Builder {

        private String mFullName;
        private String mPhotoUrl;
        private long mId;

        public Builder setFullName(final String pFullName) {
            mFullName = pFullName;
            return this;
        }

        public Builder setPhotoUrl(final String pPhotoUrl) {
            mPhotoUrl = pPhotoUrl;
            return this;
        }

        public Builder setId(final long pId) {
            if (pId > 0) {
                mId = pId;
                return this;
            }
            throw new IllegalArgumentException("Group id is bigger than 0");
        }

        public Group build() {
            return new Impl(this);
        }
    }

}
