package com.gtechnog.sample.news.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.news.R;

public class SharedNewsViewModel extends AndroidViewModel {

    private Application application;
    private Boolean mIsDualPaneMode;
    private MutableLiveData<NewsEntity> mSelectedNewsItem = new MutableLiveData<>();

    SharedNewsViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        mIsDualPaneMode = application.getResources().getBoolean(R.bool.is_dual_pane_mode);
    }

    public Boolean isDualPaneMode() {
        return mIsDualPaneMode;
    }

    public void setCurrentSelectedNewsItem(NewsEntity entity) {
        mSelectedNewsItem.postValue(entity);
    }

    public MutableLiveData<NewsEntity> getSelectedNewsItem() {
        return mSelectedNewsItem;
    }
}
