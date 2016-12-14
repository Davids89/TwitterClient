package com.example.david.twitterclient.entities;

/**
 * Created by david on 14/12/16.
 */

public class Image {
    private String id;
    private String ImageURL;
    private String tweetText;
    private int favoriteCount;
    private final static String BASE_TWEET_URL = "https://twitter.com/null/status/";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getTweeetUrl(){
        return BASE_TWEET_URL + this.id;
    }
}
