package com.github.aliaksei_karaliou.vkclient.data.parser;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.utils.JsonIterable;
import com.github.aliaksei_karaliou.vkclient.model.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.ITEMS;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.RESPONSE;

public class MessageListParser implements JsonParser<Collection<Message>> {

    @Override
    public Collection<Message> parse(final String pJson) throws JSONException {
        final Collection<Message> messages = new ArrayList<>();
        final JsonParser<Message> parser = new MessageParser();

        final JSONArray jsonArray = new JSONObject(pJson).getJSONObject(RESPONSE).getJSONArray(ITEMS);
        final Iterable<String> iterable = new JsonIterable(jsonArray);

        for (final String messageJson : iterable) {
            messages.add(parser.parse(messageJson));
        }

        return messages;
    }
}
