package com.gtechnog.sample.news.media;

public class MediaFactory {

    public static final ImageHelperImpl DEFAULT_IMAGE_HELPER = ImageHelperImpl.GLIDE_IMPL;


    public static ImageHelper getImageHelper() {
        return getImageHelper(DEFAULT_IMAGE_HELPER);
    }

    public static ImageHelper getImageHelper(ImageHelperImpl imageHelperImplType) {
        switch (imageHelperImplType) {
            case GLIDE_IMPL:
                return ImageHelperGlideImpl.getInstance();
            case FRESCO_IMPL:
                // provide implementation;
            case PICASSO_IMPL:
                // provide implementation;
                default:
        }
        return null;
    }
}
