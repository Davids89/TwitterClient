package com.example.david.twitterclient.hashtags.ui;

import com.example.david.twitterclient.entities.Hashtag;

import java.util.List;

/**
 * Created by david on 14/12/16.
 */

public interface HashtagsView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Hashtag> items);
}
