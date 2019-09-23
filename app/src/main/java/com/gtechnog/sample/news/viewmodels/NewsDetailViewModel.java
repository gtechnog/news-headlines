package com.gtechnog.sample.news.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.news.R;

public class NewsDetailViewModel extends AndroidViewModel {

    private MutableLiveData<NewsEntity> mNewsEntity = new MutableLiveData<>();
    private Application application;

    NewsDetailViewModel(@NonNull Application application, @Nullable NewsEntity newsEntity) {
        super(application);
        this.application = application;
        mNewsEntity.setValue(newsEntity);
    }

    public void setNewsEntity(NewsEntity newsEntity) {
        mNewsEntity.postValue(newsEntity);
    }

    public Boolean isDualPaneMode() {
        return application.getResources().getBoolean(R.bool.is_dual_pane_mode);
    }

    public MutableLiveData<NewsEntity> getmNewsEntity() {
        return mNewsEntity;
    }

    public String getTitle() {
        if (mNewsEntity.getValue() != null) {
            return mNewsEntity.getValue().getTitle();
        }
        return "";
    }

    public String getSummary() {
        if (mNewsEntity.getValue() != null) {
            return mNewsEntity.getValue().getSummary();
        }
        return "";
    }

    public String getUrl() {
        if (mNewsEntity.getValue() != null) {
            return mNewsEntity.getValue().getUrl();
        }
        return "";
    }
}
