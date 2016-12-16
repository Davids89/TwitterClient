package com.example.david.twitterclient.images.di;

import com.example.david.twitterclient.images.ImagesPresenter;
import com.example.david.twitterclient.images.ui.ImagesFragment;
import com.example.david.twitterclient.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by david on 16/12/16.
 */

@Singleton @Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    ImagesPresenter getPresenter();
}
