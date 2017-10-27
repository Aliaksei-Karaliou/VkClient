package com.github.aliaksei_karaliou.vkclient.data.parser;

import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.vkclient.model.Chat;

import org.json.JSONException;
import org.json.JSONObject;

import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.ID;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.PHOTO_200;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.TITLE;

public class ChatParser implements JsonParser<Chat> {

    @Override
    public Chat parse(final String pJson) throws JSONException {

        final JSONObject chatJson = new JSONObject(pJson);

        final String title = chatJson.getString(TITLE);
        final int id = chatJson.getInt(ID);
        final Chat.Builder builder = new Chat.Builder()
                .setId(id)
                .setFullName(title);

        if (chatJson.has(PHOTO_200)) {
            final String photoUrl = chatJson.getString(PHOTO_200);
            builder.setPhotoUrl(photoUrl);
        }

        return builder.build();
    }
}
