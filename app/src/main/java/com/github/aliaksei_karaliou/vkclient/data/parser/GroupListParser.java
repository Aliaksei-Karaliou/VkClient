package com.github.aliaksei_karaliou.vkclient.data.parser;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.utils.JsonIterable;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters;
import com.github.aliaksei_karaliou.vkclient.model.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GroupListParser implements JsonParser<List<Group>> {

    @Override
    public List<Group> parse(final String pJson) throws JSONException {

        final List<Group> groups = new ArrayList<>();
        final JsonParser<Group> groupParser = new GroupParser();

        final JSONArray array = new JSONObject(pJson).getJSONArray(UrlParameters.RESPONSE);
        final Iterable<String> iterable = new JsonIterable(array);

        for (final String s : iterable) {
            groups.add(groupParser.parse(s));
        }

        return groups;
    }
}
