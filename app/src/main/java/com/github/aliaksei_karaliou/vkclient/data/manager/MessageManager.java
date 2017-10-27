package com.github.aliaksei_karaliou.vkclient.data.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.github.aliaksei_karaliou.orlib.ContentManager;
import com.github.aliaksei_karaliou.orlib.interfaces.JsonParser;
import com.github.aliaksei_karaliou.orlib.interfaces.OnFailureListener;
import com.github.aliaksei_karaliou.orlib.interfaces.OnSuccessListener;
import com.github.aliaksei_karaliou.utils.StringUtils;
import com.github.aliaksei_karaliou.vkclient.data.ReceiverById;
import com.github.aliaksei_karaliou.vkclient.data.parser.ChatListParser;
import com.github.aliaksei_karaliou.vkclient.data.parser.MessageListParser;
import com.github.aliaksei_karaliou.vkclient.data.parser.messages.MessageChatIdParser;
import com.github.aliaksei_karaliou.vkclient.data.parser.messages.MessageGroupIdsParser;
import com.github.aliaksei_karaliou.vkclient.data.parser.messages.MessageUserIdsParser;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlBuilder;
import com.github.aliaksei_karaliou.vkclient.data.url.UrlParameters;
import com.github.aliaksei_karaliou.vkclient.data.url.method.Messages;
import com.github.aliaksei_karaliou.vkclient.model.Chat;
import com.github.aliaksei_karaliou.vkclient.model.Group;
import com.github.aliaksei_karaliou.vkclient.model.Message;
import com.github.aliaksei_karaliou.vkclient.model.User;

import org.json.JSONException;

import java.net.URL;
import java.util.Collection;
import java.util.List;

public class MessageManager {

    public void getDialogs(final Context pContext, final OnSuccessListener<List<Message>> pSuccessListener, final OnFailureListener pFailureListener) {
        getDialogs(pContext, 0, pSuccessListener, pFailureListener);
    }

    public void getDialogs(final Context pContext, final int pOffset, final OnSuccessListener<List<Message>> pSuccessListener, final OnFailureListener pFailureListener) {

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
                            loadChats(pData);

                            final ReceiverById receiverById = ReceiverById.getInstance();

                            final JsonParser<List<Message>> parser = new MessageListParser();
                            final List<Message> messages = parser.parse(pData);

                            if (pSuccessListener != null) {
                                final Handler handler = new Handler(Looper.getMainLooper());
                                handler.post(() -> pSuccessListener.onSuccess(messages));
                            }

                        } catch (final JSONException pE) {
                            if (pFailureListener != null) {
                                pFailureListener.onFailure(pE);
                            }
                        }
                    }

                    private void loadUsers(final String pData) throws JSONException {
                        final Collection<Long> userIds = new MessageUserIdsParser().parse(pData);

                        if (!userIds.isEmpty()) {
                            final Collection<User> users = new UserManager.Sync().get(pContext, userIds, pFailureListener);
                            ReceiverById.getInstance().add(users);
                        }
                    }

                    private void loadGroups(final String pData) throws JSONException {
                        final Collection<Long> groupIds = new MessageGroupIdsParser().parse(pData);

                        if (!groupIds.isEmpty()) {
                            final Collection<Group> groups = new GroupManager.Sync().getById(pContext, groupIds, pFailureListener);
                            ReceiverById.getInstance().add(groups);
                        }
                    }

                    private void loadChats(final String pData) throws JSONException {
                        final Collection<Long> chatIds = new MessageChatIdParser().parse(pData);

                        if (!chatIds.isEmpty()) {
                            final List<Chat> chats = new Async().getChats(pContext, chatIds, pFailureListener);
                            ReceiverById.getInstance().add(chats);
                        }
                    }

                });
        contentManager.execute(pContext);
    }

    public static class Async {

        public List<Chat> getChats(final Context pContext, final Iterable<Long> pIds, final OnFailureListener pFailureListener) {
            final String ids = StringUtils.glue(pIds, ",");

            final String url = new UrlBuilder()
                    .setMethod(Messages.GET_CHAT)
                    .addParameter(UrlParameters.CHAT_IDS, ids)
                    .build();

            return new ContentManager<List<Chat>>()
                    .setUrl(url)
                    .setParser(new ChatListParser())
                    .executeSync(pContext);
        }
    }

}
