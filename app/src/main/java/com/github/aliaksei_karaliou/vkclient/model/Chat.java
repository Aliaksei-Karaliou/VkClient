package com.github.aliaksei_karaliou.vkclient.model;

public interface Chat extends Receiver {

    int PEER_OFFSET = 2000000000;

    final class Impl implements Chat {

        private final String mFullName;
        private final String mPhotoUrl;
        private final long mId;

        private Impl(final Builder pBuilder) {
            mId = pBuilder.mId;
            mFullName = pBuilder.mFullName;
            mPhotoUrl = pBuilder.mPhotoUrl;
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
            return PEER_OFFSET + mId;
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
            mId = pId;
            return this;
        }

        public Chat build() {
            return new Impl(this);
        }
    }
}
