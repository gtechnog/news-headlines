package com.gtechnog.sample.news.dagger;

import com.gtechnog.sample.news.media.ImageHelper;
import com.gtechnog.sample.news.media.MediaFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageHelperModule {

    public ImageHelperModule() {
    }

    @Provides
    ImageHelper provideImageHelper() {
        return MediaFactory.getImageHelper();
    }
}
