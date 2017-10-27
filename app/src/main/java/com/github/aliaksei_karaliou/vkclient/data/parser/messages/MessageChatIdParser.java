package com.github.aliaksei_karaliou.vkclient.data.parser.messages;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.utils.JsonIterable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.CHAT_ID;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.ITEMS;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.MESSAGE;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.RESPONSE;

public class MessageChatIdParser implements JsonParser<Collection<Long>> {

    @Override
    public Collection<Long> parse(final String pJson) throws JSONException {
        final Collection<Long> ids = new ArrayList<>();

        final JSONArray jsonArray = new JSONObject(pJson).getJSONObject(RESPONSE).getJSONArray(ITEMS);
        final Iterable<String> iterable = new JsonIterable(jsonArray);

        for (final String messageJson : iterable) {
            final JSONObject message = new JSONObject(messageJson).getJSONObject(MESSAGE);
            if (message.has(CHAT_ID)) {
                ids.add(message.getLong(CHAT_ID));
            }
        }

        return ids;
    }
}
