package com.simformsolutions.ashutoshtiwari.glidevspicasso.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.simformsolutions.ashutoshtiwari.glidevspicasso.model.ImageModel;

/**
 * Created by Ashutosh.tiwari on 24/07/17.
 * Custom View model that performs UI logic for loading images
 */

public class ImageViewModel extends BaseObservable {

    private ImageModel imageModel;

    public ImageViewModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @Bindable
    public String getImageUrl() {
        return imageModel.getImageUrl();
    }
}
