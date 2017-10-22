package com.github.aliaksei_karaliou.vkclient.data.manager;

import android.content.Context;
import android.support.annotation.Nullable;

import com.github.aliaksei_karaliou.orlib.ContentManager;
import com.github.aliaksei_karaliou.orlib.interfaces.OnFailureListener;
import com.github.aliaksei_karaliou.orlib.interfaces.OnSuccessListener;
import com.github.aliaksei_karaliou.utils.StringUtils;
import com.github.aliaksei_karaliou.vkclient.data.parser.UserListParser;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlBuilder;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters;
import com.github.aliaksei_karaliou.vkclient.data.url.method.Users;
import com.github.aliaksei_karaliou.vkclient.model.User;

import java.util.Collection;

public class UserManager {

    public void get(final Context pContext, final Iterable<Long> pIds, @Nullable final OnSuccessListener<Collection<User>> pSuccessListener, @Nullable final OnFailureListener pFailureListener) {

        final String ids = StringUtils.glue(pIds, ",");
        final String url = new UrlBuilder()
                .setMethod(Users.GET)
                .addParameter(UrlParameters.USER_IDS, ids)
                .addParameter(UrlParameters.FIELDS, UrlParameters.PHOTO_MAX, UrlParameters.ONLINE)
                .build();

        final ContentManager contentManager = new ContentManager<Collection<User>>()
                .setUrl(url)
                .setParser(new UserListParser())
                .setSuccessListener(pSuccessListener)
                .setFailureListener(pFailureListener);
        contentManager.execute(pContext);
    }

    public static class Sync {

        public Collection<User> get(final Context pContext, final Iterable<Long> pIds, final OnFailureListener pFailureListener) {

            final String ids = StringUtils.glue(pIds, ",");
            final String url = new UrlBuilder()
                    .setMethod(Users.GET)
                    .addParameter(UrlParameters.USER_IDS, ids)
                    .addParameter(UrlParameters.FIELDS, UrlParameters.PHOTO_MAX, UrlParameters.ONLINE)
                    .build();

            final ContentManager<Collection<User>> contentManager = new ContentManager<Collection<User>>()
                    .setUrl(url)
                    .setParser(new UserListParser())
                    .setFailureListener(pFailureListener);

            return contentManager.executeSync(pContext);
        }
    }
}
