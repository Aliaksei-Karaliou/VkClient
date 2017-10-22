package com.github.aliaksei_karaliou.vkclient.data.parser;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.vkclient.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.FIRST_NAME;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.ID;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.LAST_NAME;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.ONLINE;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.PHOTO_MAX;

public class UserParser implements JsonParser<User> {

    @Override
    public User parse(final String pJson) throws JSONException {
        final JSONObject jsonObject = new JSONObject(pJson);

        final long id = jsonObject.getLong(ID);
        final String firstName = jsonObject.getString(FIRST_NAME);
        final String lastName = jsonObject.getString(LAST_NAME);
        final String photoUrl = jsonObject.getString(PHOTO_MAX);
        final boolean online = jsonObject.getInt(ONLINE) > 0;

        return new User.Builder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhotoUrl(photoUrl)
                .setOnline(online)
                .build();
    }
}
