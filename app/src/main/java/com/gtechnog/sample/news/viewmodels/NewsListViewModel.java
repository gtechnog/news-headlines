package com.gtechnog.sample.news.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gtechnog.sample.network.api.Callback;
import com.gtechnog.sample.network.model.HeadlinesResponse;
import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.network.repository.HeadlinesRepository;

import java.util.List;

public class NewsListViewModel extends AndroidViewModel {

    private Application application;
    private HeadlinesRepository headlinesRepository;
    private MutableLiveData<List<NewsEntity>> newsList = new MutableLiveData<>();

    NewsListViewModel(@NonNull Application application, HeadlinesRepository headlinesRepository) {
        super(application);
        this.application = application;
        this.headlinesRepository = headlinesRepository;
        fetchHeadlines();
    }

    private void fetchHeadlines() {
        headlinesRepository.getHeadlines(new Callback<HeadlinesResponse, Throwable>() {
            @Override
            public void onResult(HeadlinesResponse result) {
                getNewsList().postValue(result.getNewsEntities());
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    public MutableLiveData<List<NewsEntity>> getNewsList() {
        return newsList;
    }
}
