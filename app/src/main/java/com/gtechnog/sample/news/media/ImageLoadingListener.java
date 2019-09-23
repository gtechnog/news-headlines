package com.gtechnog.sample.news.helper;

import android.graphics.Bitmap;

public interface ImageLoadingListener {

    void onLoadingStarted();

    void onLoadingFinished(Bitmap bitmap);

    void onLoadingError(Error error);

    void onLoadCancelled();
}
