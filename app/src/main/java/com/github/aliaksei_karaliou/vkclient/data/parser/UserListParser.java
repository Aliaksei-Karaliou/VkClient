package com.github.aliaksei_karaliou.vkclient.data.parser;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.utils.JsonIterable;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters;
import com.github.aliaksei_karaliou.vkclient.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserListParser implements JsonParser<List<User>> {

    @Override
    public List<User> parse(final String pJson) throws JSONException {
        final List<User> users = new ArrayList<>();
        final JsonParser<User> userParser = new UserParser();

        final JSONArray responseArray = new JSONObject(pJson).getJSONArray(UrlParameters.RESPONSE);
        final Iterable<String> usersJson = new JsonIterable(responseArray);

        for (final String jsonObject : usersJson) {
            users.add(userParser.parse(jsonObject));
        }

        return users;
    }
}
