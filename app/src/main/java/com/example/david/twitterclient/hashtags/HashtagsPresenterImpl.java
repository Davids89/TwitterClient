package com.example.david.twitterclient.hashtags;

import com.example.david.twitterclient.hashtags.events.HashtagsEvent;
import com.example.david.twitterclient.hashtags.ui.HashtagsView;
import com.example.david.twitterclient.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by david on 15/12/16.
 */

public class HashtagsPresenterImpl implements HashtagsPresenter {

    private EventBus eventBus;
    private HashtagsView view;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(EventBus eventBus, HashtagsView view, HashtagsInteractor interactor) {
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
    public void getHashtagTweets() {
        if(view != null){
            view.hideImages();
            view.showProgress();
        }

        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashtagsEvent event) {

        String errorMsg = event.getError();

        if(view != null){
            view.showImages();
            view.hideProgress();

            if(errorMsg != null){
                view.onError(errorMsg);
            }
            else{
                view.setContent(event.getHashtags());
            }
        }
    }
}
