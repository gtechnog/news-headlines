package com.gtechnog.sample.news.utils;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.network.repository.HeadlinesRepository;
import com.gtechnog.sample.news.viewmodels.NewsDetailViewModelFactory;
import com.gtechnog.sample.news.viewmodels.NewsListViewModelFactory;
import com.gtechnog.sample.news.viewmodels.SharedNewsViewModelFactory;

public class InjectorUtils {


    public static ViewModelProvider.Factory getSharedNewsViewModelFactory(Application application) {
        return new SharedNewsViewModelFactory(application);
    }

    public static ViewModelProvider.Factory getNewsListViewModelFactory(Application application, HeadlinesRepository headlinesRepository) {
        return new NewsListViewModelFactory(application, headlinesRepository);
    }

    public static HeadlinesRepository getHeadlinesRepository() {
        return new HeadlinesRepository();
    }

    public static NewsDetailViewModelFactory getNewsDetailViewModelFactory(Application application, NewsEntity newsEntity) {
        return new NewsDetailViewModelFactory(application, newsEntity);
    }
}
