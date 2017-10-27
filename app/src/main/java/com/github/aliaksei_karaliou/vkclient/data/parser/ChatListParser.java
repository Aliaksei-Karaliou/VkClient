package com.github.aliaksei_karaliou.vkclient.data.parser;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.utils.JsonIterable;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters;
import com.github.aliaksei_karaliou.vkclient.model.Chat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatListParser implements JsonParser<List<Chat>> {

    @Override
    public List<Chat> parse(final String pJson) throws JSONException {
        final List<Chat> chats = new ArrayList<>();
        final JsonParser<Chat> chatParser = new ChatParser();

        final Iterable<String> iterable = new JsonIterable(new JSONObject(pJson).getJSONArray(UrlParameters.RESPONSE));

        for (final String s : iterable) {
            chats.add(chatParser.parse(s));
        }
        return chats;
    }
}
