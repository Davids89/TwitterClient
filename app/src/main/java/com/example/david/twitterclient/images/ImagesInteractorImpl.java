package com.example.david.twitterclient.images;

/**
 * Created by david on 15/12/16.
 */

public class ImagesInteractorImpl implements ImagesInteractor {

    private ImagesRepository repository;

    public ImagesInteractorImpl(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
