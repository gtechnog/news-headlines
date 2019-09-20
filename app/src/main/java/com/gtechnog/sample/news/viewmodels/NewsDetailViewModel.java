package com.gtechnog.sample.news.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.gtechnog.sample.network.model.NewsEntity;

public class NewsDetailViewModel extends AndroidViewModel {

    private NewsEntity newsEntity;

    NewsDetailViewModel(@NonNull Application application, NewsEntity newsEntity) {
        super(application);
        this.newsEntity = newsEntity;
    }

    public String getTitle() {
        return newsEntity.getTitle();
    }
}
