package com.example.david.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.david.twitterclient.images.di.DaggerImagesComponent;
import com.example.david.twitterclient.images.di.ImagesComponent;
import com.example.david.twitterclient.images.di.ImagesModule;
import com.example.david.twitterclient.images.ui.ImagesView;
import com.example.david.twitterclient.images.ui.adapters.OnItemClickListener;
import com.example.david.twitterclient.lib.di.LibsModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by david on 5/12/16.
 */

public class TwitterClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnItemClickListener listener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, listener))
                .build();
    }


}
