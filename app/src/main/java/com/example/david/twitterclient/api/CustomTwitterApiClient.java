package com.example.david.twitterclient.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Created by david on 5/12/16.
 */

public class CustomTwitterApiClient extends TwitterApiClient {
    /*public CustomTwitterApiClient(Session session) {
        super(session);
    }*/

    public TimelineService getTimeLineService(){
        return getService(TimelineService.class);
    }
}
