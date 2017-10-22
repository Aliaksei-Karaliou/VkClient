package com.github.aliaksei_karaliou.vkclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.aliaksei_karaliou.orlib.ContentManager;
import com.github.aliaksei_karaliou.orlib.interfaces.OnFailureListener;
import com.github.aliaksei_karaliou.orlib.interfaces.OnSuccessListener;
import com.github.aliaksei_karaliou.vkclient.data.manager.MessageManager;
import com.github.aliaksei_karaliou.vkclient.data.manager.UserManager;
import com.github.aliaksei_karaliou.vkclient.model.Message;
import com.github.aliaksei_karaliou.vkclient.model.User;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);

        new MessageManager().getDialogs(this, new OnSuccessListener<Collection<Message>>() {

            @Override
            public void onSuccess(final Collection<Message> pData) {
                new StringBuilder();
            }
        },null);
    }
}
