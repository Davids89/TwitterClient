package com.example.david.twitterclient.hashtags;

import com.example.david.twitterclient.api.CustomTwitterApiClient;
import com.example.david.twitterclient.entities.Hashtag;
import com.example.david.twitterclient.entities.Image;
import com.example.david.twitterclient.hashtags.events.HashtagsEvent;
import com.example.david.twitterclient.lib.base.EventBus;
import com.twitter.sdk.android.core.models.HashtagEntity;
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

                if(response.isSuccessful()){
                    List<Tweet> items = response.body();
                    List<Hashtag> hashtags = new ArrayList<Hashtag>();

                    for(Tweet tweet : items){
                        if(containsHashtags(tweet)){
                            Hashtag tweetModel = new Hashtag();

                            tweetModel.setId(tweet.idStr);
                            tweetModel.setFavoriteCount(tweet.favoriteCount);
                            tweetModel.setTweetText(tweet.text);

                            List<String> hashtagsArray = new ArrayList<String>();
                            for(HashtagEntity hashtag : tweet.entities.hashtags){
                                hashtagsArray.add(hashtag.text);
                            }

                            tweetModel.setHashtags(hashtagsArray);

                            hashtags.add(tweetModel);
                        }
                    }

                    Collections.sort(hashtags, new Comparator<Hashtag>() {
                        @Override
                        public int compare(Hashtag hashtag, Hashtag t1) {
                            return t1.getFavoriteCount() - hashtag.getFavoriteCount();
                        }
                    });
                    post(hashtags);
                }else{
                    post(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        });
    }

    private boolean containsHashtags(Tweet tweet){
        return tweet.entities != null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
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
