package com.github.aliaksei_karaliou.vkclient.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.aliaksei_karaliou.vkclient.R;
import com.github.aliaksei_karaliou.vkclient.data.manager.MessageManager;
import com.github.aliaksei_karaliou.vkclient.ui.fragment.DialogListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main_container, new DialogListFragment())
                .commit();
    }
}
