package com.example.david.twitterclient.hashtags;

/**
 * Created by david on 15/12/16.
 */

public class HashtagsInteractorImpl implements HashtagsInteractor {

    private HashtagsRepository repository;

    public HashtagsInteractorImpl(HashtagsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getHashtags();
    }
}
