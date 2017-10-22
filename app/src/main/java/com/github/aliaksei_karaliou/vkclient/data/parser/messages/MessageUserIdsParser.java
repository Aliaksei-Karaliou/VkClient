package com.github.aliaksei_karaliou.vkclient.data.parser.messages;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.utils.JsonIterable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashSet;

import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.ITEMS;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.MESSAGE;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.RESPONSE;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.USER_ID;

public class MessageUserIdsParser implements JsonParser<Collection<Long>> {

    @Override
    public Collection<Long> parse(final String pJson) throws JSONException {
        final Collection<Long> ids = new HashSet<>();

        final JSONArray jsonArray = new JSONObject(pJson).getJSONObject(RESPONSE).getJSONArray(ITEMS);
        final Iterable<String> iterable = new JsonIterable(jsonArray);

        for (final String messageJson : iterable) {
            final long userId = new JSONObject(messageJson).getJSONObject(MESSAGE).getLong(USER_ID);
            if (userId > 0) {
                ids.add(userId);
            }
        }

        return ids;
    }
}
