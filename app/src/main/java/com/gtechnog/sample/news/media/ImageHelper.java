package com.gtechnog.sample.news.media;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;

public interface ImageHelper {

    void loadImageUrl(ImageView imageView, String url);

    void loadImageUrl(ImageView imageView, String url, @DrawableRes int placeHolderResource, ImageLoadingListener listener);

    void loadImageUrl(ImageView imageView, String url, String fallbackUrl, ImageLoadingListener listener);

    void loadImageUrl(ImageView imageView, String url, String fallbackUrl, @DrawableRes int placeHolderResource, ImageLoadingListener listener);

    void loadImageUrl(ImageView imageView, String url, String fallbackUrl, @DrawableRes int placeHolderResource, @DrawableRes int errorImageResource, ImageLoadingListener listener);
}
