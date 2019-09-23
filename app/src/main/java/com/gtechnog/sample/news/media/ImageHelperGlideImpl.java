package com.gtechnog.sample.news.helper;

import android.widget.ImageView;

public class ImageHelperGlideImpl implements ImageHelper {


    private static ImageHelperGlideImpl sInstance;

    private ImageHelperGlideImpl() {

    }

    public static ImageHelperGlideImpl getInstance() {
        if (sInstance == null) {
            synchronized (ImageHelperGlideImpl.class) {
                if (sInstance == null) {
                    sInstance = new ImageHelperGlideImpl();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void loadImageUrl(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext()).load(url).into(imageView);
    }

    @Override
    public void loadImageUrl(ImageView imageView, String url, int placeHolderResource, ImageLoadingListener listener) {

    }

    @Override
    public void loadImageUrl(ImageView imageView, String url, String fallbackUrl, ImageLoadingListener listener) {

    }

    @Override
    public void loadImageUrl(ImageView imageView, String url, String fallbackUrl, int placeHolderResource, ImageLoadingListener listener) {

    }

    @Override
    public void loadImageUrl(ImageView imageView, String url, String fallbackUrl, int placeHolderResource, int errorImageResource, ImageLoadingListener listener) {

    }

}
