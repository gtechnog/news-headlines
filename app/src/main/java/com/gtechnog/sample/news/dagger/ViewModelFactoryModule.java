package com.gtechnog.sample.news.dagger;

import android.app.Application;

import androidx.annotation.Nullable;

import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.network.repository.HeadlinesRepository;
import com.gtechnog.sample.news.viewmodels.SharedNewsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelFactoryModule {

    private Application application;
    private NewsEntity newsEntity;

    public ViewModelFactoryModule() {
    }

    public ViewModelFactoryModule(Application application) {
        this.application = application;
    }

    public ViewModelFactoryModule(Application application, NewsEntity newsEntity) {
        this.application = application;
        this.newsEntity = newsEntity;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Nullable
    @Provides
    NewsEntity provideNewsEntity() {
        return newsEntity;
    }

    @Provides
    SharedNewsViewModelFactory provideSharedNewsViewModelFactory() {
        return new SharedNewsViewModelFactory(application);
    }

    @Provides
    HeadlinesRepository provideHeadlinesRepository() {
        return HeadlinesRepository.getsInstance();
    }

}
