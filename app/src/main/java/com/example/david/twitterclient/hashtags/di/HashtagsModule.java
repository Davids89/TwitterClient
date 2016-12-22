package com.example.david.twitterclient.hashtags.di;

import com.example.david.twitterclient.api.CustomTwitterApiClient;
import com.example.david.twitterclient.entities.Hashtag;
import com.example.david.twitterclient.entities.Image;
import com.example.david.twitterclient.hashtags.HashtagsInteractor;
import com.example.david.twitterclient.hashtags.HashtagsInteractorImpl;
import com.example.david.twitterclient.hashtags.HashtagsPresenter;
import com.example.david.twitterclient.hashtags.HashtagsPresenterImpl;
import com.example.david.twitterclient.hashtags.HashtagsRepository;
import com.example.david.twitterclient.hashtags.HashtagsRepositoryImpl;
import com.example.david.twitterclient.hashtags.ui.HashtagsView;
import com.example.david.twitterclient.hashtags.ui.adapters.HashtagAdapter;
import com.example.david.twitterclient.hashtags.ui.adapters.OnItemClickListener;
import com.example.david.twitterclient.lib.base.EventBus;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by david on 16/12/16.
 */

@Module
public class HashtagsModule {
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagAdapter providesAdapter(List<Hashtag> dataset, OnItemClickListener onItemClickListener){
        return new HashtagAdapter(dataset, onItemClickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesImageList(){
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    HashtagsPresenter providesImagesPresenter(EventBus eventBus, HashtagsView view, HashtagsInteractor interactor){
        return new HashtagsPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    HashtagsView providesImagesView(){
        return this.view;
    }

    @Provides
    @Singleton
    HashtagsInteractor providesImagesInteractor(HashtagsRepository repository){
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new HashtagsRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(TwitterSession session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession providesTwitterSession(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
