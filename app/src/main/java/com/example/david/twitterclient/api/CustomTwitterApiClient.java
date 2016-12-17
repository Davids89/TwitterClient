package com.example.david.twitterclient.api;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by david on 5/12/16.
 */

public class CustomTwitterApiClient extends TwitterApiClient {

    public CustomTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public TimelineService getTimeLineService(){
        return getService(TimelineService.class);
    }
}
