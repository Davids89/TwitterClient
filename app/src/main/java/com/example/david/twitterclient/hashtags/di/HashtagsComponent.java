package com.example.david.twitterclient.hashtags.di;

import com.example.david.twitterclient.hashtags.HashtagsPresenter;
import com.example.david.twitterclient.hashtags.ui.HashtagsFragment;
import com.example.david.twitterclient.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by david on 16/12/16.
 */

@Singleton @Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment fragment);
    HashtagsPresenter getPresenter();
}
