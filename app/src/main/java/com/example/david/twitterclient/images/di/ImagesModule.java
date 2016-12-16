package com.example.david.twitterclient.images.di;

import com.example.david.twitterclient.api.CustomTwitterApiClient;
import com.example.david.twitterclient.entities.Image;
import com.example.david.twitterclient.images.ImagesInteractor;
import com.example.david.twitterclient.images.ImagesInteractorImpl;
import com.example.david.twitterclient.images.ImagesPresenter;
import com.example.david.twitterclient.images.ImagesPresenterImpl;
import com.example.david.twitterclient.images.ImagesRepository;
import com.example.david.twitterclient.images.ImagesRepositoryImpl;
import com.example.david.twitterclient.images.ui.ImagesView;
import com.example.david.twitterclient.images.ui.adapters.ImagesAdapter;
import com.example.david.twitterclient.images.ui.adapters.OnItemClickListener;
import com.example.david.twitterclient.lib.base.EventBus;
import com.example.david.twitterclient.lib.base.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by david on 16/12/16.
 */

@Module
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> imageList, ImageLoader imageLoader, OnItemClickListener onItemClickListener){
        return new ImagesAdapter(imageList, imageLoader, onItemClickListener);
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
    ImagesPresenter providesImagesPresenter(EventBus eventBus, ImagesView view, ImagesInteractor interactor){
        return new ImagesPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView(){
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository){
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(){
        return new CustomTwitterApiClient();
    }
}
