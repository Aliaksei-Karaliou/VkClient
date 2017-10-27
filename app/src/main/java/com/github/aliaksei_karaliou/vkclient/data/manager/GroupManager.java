package com.github.aliaksei_karaliou.vkclient.data.manager;

import android.content.Context;

import com.github.aliaksei_karaliou.orlib.ContentManager;
import com.github.aliaksei_karaliou.orlib.interfaces.OnFailureListener;
import com.github.aliaksei_karaliou.utils.StringUtils;
import com.github.aliaksei_karaliou.vkclient.data.parser.GroupListParser;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlBuilder;
import com.github.aliaksei_karaliou.vkclient.data.url.method.Groups;
import com.github.aliaksei_karaliou.vkclient.model.Group;

import java.util.List;

import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.FIELDS;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.GROUP_IDS;
import static com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters.PHOTO_MAX;

public class GroupManager {

    public static class Sync {

        public List<Group> getById(final Context pContext, final Iterable<Long> pIterable, final OnFailureListener pFailureListener) {

            final String ids = StringUtils.glue(pIterable, ",");

            final String url = new UrlBuilder()
                    .setMethod(Groups.GET_BY_ID)
                    .addParameter(GROUP_IDS, ids)
                    .addParameter(FIELDS, PHOTO_MAX)
                    .build();

            final ContentManager<List<Group>> contentManager = new ContentManager<List<Group>>()
                    .setUrl(url)
                    .setParser(new GroupListParser())
                    .setFailureListener(pFailureListener);

            return contentManager.executeSync(pContext);

        }
    }
}
