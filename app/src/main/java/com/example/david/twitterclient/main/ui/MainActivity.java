package com.example.david.twitterclient.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.david.twitterclient.R;
import com.example.david.twitterclient.hashtags.HashtagsFragment;
import com.example.david.twitterclient.images.ui.ImagesFragment;
import com.example.david.twitterclient.login.LoginActivity;
import com.example.david.twitterclient.main.ui.adapters.MainSectionsPagerAdapter;
import com.twitter.sdk.android.Twitter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.container)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        setupAdapter();
    }

    private void setupAdapter() {
        Fragment[] fragments = new Fragment[]{new ImagesFragment(), new HashtagsFragment()};
        String[] titles = new String[]{getString(R.string.main_header_images), getString(R.string.main_header_hashtags)};

        MainSectionsPagerAdapter adapter =
                new MainSectionsPagerAdapter(getSupportFragmentManager(), titles, fragments);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
