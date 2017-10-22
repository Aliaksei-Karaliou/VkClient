package com.github.aliaksei_karaliou.vkclient.data.manager;

import android.content.Context;

import com.github.aliaksei_karaliou.orlib.ContentManager;
import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.orlib.interfaces.OnFailureListener;
import com.github.aliaksei_karaliou.orlib.interfaces.OnSuccessListener;
import com.github.aliaksei_karaliou.vkclient.data.parser.MessageListParser;
import com.github.aliaksei_karaliou.vkclient.data.parser.messages.MessageGroupIdsParser;
import com.github.aliaksei_karaliou.vkclient.data.parser.messages.MessageUserIdsParser;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlBuilder;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters;
import com.github.aliaksei_karaliou.vkclient.data.url.method.Messages;
import com.github.aliaksei_karaliou.vkclient.model.Message;
import com.github.aliaksei_karaliou.vkclient.model.User;

import org.json.JSONException;

import java.util.Collection;
import java.util.List;

public class MessageManager {

    public void getDialogs(final Context pContext, final OnSuccessListener<Collection<Message>> pSuccessListener, final OnFailureListener pFailureListener) {
        getDialogs(pContext, 0, pSuccessListener, pFailureListener);
    }

    public void getDialogs(final Context pContext, final int pOffset, final OnSuccessListener<Collection<Message>> pSuccessListener, final OnFailureListener pFailureListener) {

        final String urlBuilder = new UrlBuilder()
                .setMethod(Messages.GET_DIALOGS)
                .addParameter(UrlParameters.OFFSET, pOffset)
                .build();

        final ContentManager contentManager = new ContentManager<List<Message>>()
                .setUrl(urlBuilder)
                .setJsonSuccessListener(new OnSuccessListener<String>() {

                    @Override
                    public void onSuccess(final String pData) {
                        try {
                            loadUsers(pData);
                            loadGroups(pData);

                            final JsonParser<Collection<Message>> parser = new MessageListParser();
                            final Collection<Message> messages = parser.parse(pData);

                            if (pSuccessListener!=null){
                                pSuccessListener.onSuccess(messages);
                            }

                        } catch (final JSONException pE) {
                            if (pFailureListener != null) {
                                pFailureListener.onFailure(pE);
                            }
                        }
                    }

                    private void loadUsers(final String pData) throws JSONException {
                        final Collection<Long> userIds = new MessageUserIdsParser().parse(pData);
                        final Collection<User> users = new UserManager.Sync().get(pContext, userIds, pFailureListener);

                        new StringBuilder();
                    }

                    private void loadGroups(final String pData) throws JSONException {
                        final List<Long> groupIds = new MessageGroupIdsParser().parse(pData);
                    }

                });
        contentManager.execute(pContext);
    }
}
