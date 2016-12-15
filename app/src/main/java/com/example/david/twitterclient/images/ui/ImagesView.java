package com.example.david.twitterclient.images.ui;

import com.example.david.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by david on 14/12/16.
 */

public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}
