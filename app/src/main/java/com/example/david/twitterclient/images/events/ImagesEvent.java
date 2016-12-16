package com.example.david.twitterclient.images.events;

import com.example.david.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by david on 14/12/16.
 */
public class ImagesEvent {
    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
