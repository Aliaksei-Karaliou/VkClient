package com.github.aliaksei_karaliou.vkclient.data.parser.messages;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.utils.JsonIterable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.ITEMS;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.MESSAGE;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.RESPONSE;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.USER_ID;

public class MessageGroupIdsParser implements JsonParser<List<Long>> {

    @Override
    public List<Long> parse(final String pJson) throws JSONException {
        final List<Long> ids = new ArrayList<>();

        final JSONArray jsonArray = new JSONObject(pJson).getJSONObject(RESPONSE).getJSONArray(ITEMS);
        final Iterable<String> iterable = new JsonIterable(jsonArray);

        for (final String messageJson : iterable) {
            final long userId = new JSONObject(messageJson).getJSONObject(MESSAGE).getLong(USER_ID);
            if (userId < 0) {
                ids.add(-userId);
            }
        }

        return ids;
    }
}
