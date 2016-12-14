package com.example.david.twitterclient.images;

import com.example.david.twitterclient.images.events.ImagesEvent;

/**
 * Created by david on 14/12/16.
 */

public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);
}
