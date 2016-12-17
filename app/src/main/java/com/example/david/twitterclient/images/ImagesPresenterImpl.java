package com.example.david.twitterclient.images;

import com.example.david.twitterclient.images.events.ImagesEvent;
import com.example.david.twitterclient.images.ui.ImagesView;
import com.example.david.twitterclient.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by david on 15/12/16.
 */

public class ImagesPresenterImpl implements ImagesPresenter {

    private EventBus eventBus;
    private ImagesView view;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void getImageTweets() {
        if(view != null){
            view.hideImages();
            view.showProgress();
        }

        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {

        String errorMsg = event.getError();

        if(view != null){
            view.showImages();
            view.hideProgress();

            if(errorMsg != null){
                view.onError(errorMsg);
            }
            else{
                view.setContent(event.getImages());
            }
        }
    }
}
