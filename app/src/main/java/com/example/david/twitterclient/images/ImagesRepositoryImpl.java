package com.example.david.twitterclient.images;

import com.example.david.twitterclient.api.CustomTwitterApiClient;
import com.example.david.twitterclient.entities.Image;
import com.example.david.twitterclient.images.events.ImagesEvent;
import com.example.david.twitterclient.lib.base.EventBus;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by david on 15/12/16.
 */

public class ImagesRepositoryImpl implements ImagesRepository {

    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public ImagesRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {
        Call<List<Tweet>> service = client.getTimeLineService().homeTimeline(TWEET_COUNT, true, true, true, true);
        service.enqueue(new retrofit2.Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {

                if (response.isSuccessful()) {
                    List<Tweet> items = response.body();
                    List<Image> images = new ArrayList<Image>();
                    for(Tweet tweet : items){
                        if(containsImages(tweet)){
                            Image tweetModel = new Image();

                            tweetModel.setId(tweet.idStr);
                            tweetModel.setFavoriteCount(tweet.favoriteCount);

                            String tweetText = tweet.text;
                            int index = tweetText.indexOf("http");
                            if(index > 0){
                                tweetText = tweetText.substring(0, index);
                            }

                            tweetModel.setTweetText(tweetText);

                            MediaEntity currentPhoto = tweet.entities.media.get(0);
                            String imageUrl = currentPhoto.mediaUrl;
                            tweetModel.setImageURL(imageUrl);

                            images.add(tweetModel);
                        }
                    }

                    Collections.sort(images, new Comparator<Image>() {
                        @Override
                        public int compare(Image image, Image t1) {
                            return t1.getFavoriteCount() - image.getFavoriteCount();
                        }
                    });
                    post(images);
                } else {
                    post(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        });
    }

    private boolean containsImages(Tweet tweet){
        return tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private void post(List<Image> items){
        post(items, null);
    }

    private void post(String error){
        post(null, error);
    }

    private void post(List<Image> items, String error){
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        event.setImages(items);
        eventBus.post(event);
    }
}
