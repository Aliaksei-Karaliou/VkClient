package com.github.aliaksei_karaliou.vkclient.data.parser;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters;
import com.github.aliaksei_karaliou.vkclient.model.Message;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageParser implements JsonParser<Message> {

    @Override
    public Message parse(final String pJson) throws JSONException {

        final JSONObject messageObject = new JSONObject(pJson).getJSONObject(UrlParameters.MESSAGE);
        final long id = messageObject.getLong(UrlParameters.ID);
        final String text = messageObject.getString(UrlParameters.BODY);
        final long timestamp = messageObject.getLong(UrlParameters.DATE);

        return new Message.Builder()
                .setId(id)
                .setText(text)
                .setTimestamp(timestamp)
                .build();
    }
}
