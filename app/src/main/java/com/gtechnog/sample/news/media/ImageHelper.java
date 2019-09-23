package com.gtechnog.sample.news.helper;

import android.widget.ImageView;

import androidx.annotation.IdRes;

interface ImageHelper {

    void loadImageUrl(ImageView imageView, String url);

    void loadImageUrl(ImageView imageView, String url, @IdRes int placeHolderResource, ImageLoadingListener listener);

    void loadImageUrl(ImageView imageView, String url, String fallbackUrl, ImageLoadingListener listener);

    void loadImageUrl(ImageView imageView, String url, String fallbackUrl, @IdRes int placeHolderResource, ImageLoadingListener listener);

    void loadImageUrl(ImageView imageView, String url, String fallbackUrl, @IdRes int placeHolderResource, @IdRes int errorImageResource, ImageLoadingListener listener);
}
