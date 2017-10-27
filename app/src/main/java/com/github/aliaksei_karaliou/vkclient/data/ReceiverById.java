package com.github.aliaksei_karaliou.vkclient.data;

import com.github.aliaksei_karaliou.vkclient.model.Receiver;

import java.util.HashMap;
import java.util.Map;

public final class ReceiverById {

    private static final ReceiverById ourInstance = new ReceiverById();

    public static ReceiverById getInstance() {
        return ourInstance;
    }

    private ReceiverById() {
    }

    private final Map<Long, Receiver> mReceiverMap = new HashMap<>();

    public void add(final Receiver pReceiver) {
        mReceiverMap.put(pReceiver.getPeerId(), pReceiver);
    }

    public <T extends Receiver> void add(final Iterable<T> pReceivers) {
        for (final Receiver receiver : pReceivers) {
            add(receiver);
        }
    }

    public Receiver get(final long peerId) {
        return mReceiverMap.get(peerId);
    }
}
