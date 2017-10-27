package com.github.aliaksei_karaliou.vkclient.data.parser;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.vkclient.data.ReceiverById;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters;
import com.github.aliaksei_karaliou.vkclient.model.Chat;
import com.github.aliaksei_karaliou.vkclient.model.Message;
import com.github.aliaksei_karaliou.vkclient.model.Receiver;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageParser implements JsonParser<Message> {

    @Override
    public Message parse(final String pJson) throws JSONException {

        final JSONObject messageObject = new JSONObject(pJson).getJSONObject(UrlParameters.MESSAGE);
        final ReceiverById receiverById = ReceiverById.getInstance();

        final long id = messageObject.getLong(UrlParameters.ID);
        final String text = messageObject.getString(UrlParameters.BODY);
        final long timestamp = messageObject.getLong(UrlParameters.DATE);
        final boolean isOut = messageObject.getInt(UrlParameters.OUT) == 0;

        final long userId = messageObject.getLong(UrlParameters.USER_ID);
        final Receiver receiver = receiverById.get(userId);

        final Message.Builder messageBuilder = new Message.Builder()
                .setId(id)
                .setReceiver(receiver)
                .setText(text)
                .setOut(isOut)
                .setTimestamp(timestamp);

        if (messageObject.has(UrlParameters.CHAT_ID)) {
            final long chatPeerId = messageObject.getLong(UrlParameters.CHAT_ID) + Chat.PEER_OFFSET;
            final Receiver chat = receiverById.get(chatPeerId);
            
            if (chat instanceof Chat) {
                messageBuilder.setChat((Chat) chat);
            }
        }

        return messageBuilder.build();
    }
}
