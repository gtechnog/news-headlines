package com.gtechnog.sample.news.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gtechnog.sample.network.model.NewsEntity;

import javax.inject.Inject;

public class NewsDetailViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private Application application;
    private NewsEntity newsEntity;

    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link androidx.lifecycle.AndroidViewModel}
     * @param newsEntity
     */

    @Inject
    public NewsDetailViewModelFactory(@NonNull Application application, NewsEntity newsEntity) {
        super(application);
        this.application = application;
        this.newsEntity = newsEntity;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewsDetailViewModel(application, newsEntity);
    }
}
