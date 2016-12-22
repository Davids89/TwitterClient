package com.example.david.twitterclient.hashtags.events;

import com.example.david.twitterclient.entities.Hashtag;
import com.example.david.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by david on 14/12/16.
 */
public class HashtagsEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
