package com.example.david.twitterclient.hashtags;

import com.example.david.twitterclient.api.CustomTwitterApiClient;
import com.example.david.twitterclient.entities.Hashtag;
import com.example.david.twitterclient.entities.Image;
import com.example.david.twitterclient.hashtags.events.HashtagsEvent;
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

public class HashtagsRepositoryImpl implements HashtagsRepository {

    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public HashtagsRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getHashtags() {
        Call<List<Tweet>> service = client.getTimeLineService().homeTimeline(TWEET_COUNT, true, true, true, true);
        service.enqueue(new retrofit2.Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {

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

    private void post(List<Hashtag> items){
        post(items, null);
    }

    private void post(String error){
        post(null, error);
    }

    private void post(List<Hashtag> items, String error){
        HashtagsEvent event = new HashtagsEvent();
        event.setError(error);
        event.setHashtags(items);
        eventBus.post(event);
    }
}
