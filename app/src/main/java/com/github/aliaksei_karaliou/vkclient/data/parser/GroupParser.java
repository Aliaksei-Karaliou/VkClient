package com.github.aliaksei_karaliou.vkclient.data.parser;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.vkclient.model.Group;

import org.json.JSONException;
import org.json.JSONObject;

import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.ID;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.NAME;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.PHOTO_MAX;

public class GroupParser implements JsonParser<Group> {

    @Override
    public Group parse(final String pJson) throws JSONException {
        final JSONObject groupJson = new JSONObject(pJson);
        final long id = groupJson.getLong(ID);
        final String name = groupJson.getString(NAME);
        final String photoUrl = groupJson.getString(PHOTO_MAX);

        return new Group.Builder()
                .setId(id)
                .setFullName(name)
                .setPhotoUrl(photoUrl)
                .build();
    }
}
