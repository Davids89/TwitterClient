package com.example.david.twitterclient.hashtags;

import com.example.david.twitterclient.hashtags.events.HashtagsEvent;

/**
 * Created by david on 14/12/16.
 */

public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagsEvent event);
}
