package com.gtechnog.sample.news.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gtechnog.sample.network.repository.HeadlinesRepository;
import com.gtechnog.sample.news.ui.NewsListFragment;

import javax.inject.Inject;

public class NewsListViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {
    private Application application;
    private HeadlinesRepository headlinesRepository;

    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link androidx.lifecycle.AndroidViewModel}
     * @param headlinesRepository
     */

    @Inject
    public NewsListViewModelFactory(@NonNull Application application, HeadlinesRepository headlinesRepository) {
        super(application);
        this.application = application;
        this.headlinesRepository = headlinesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewsListViewModel(application, headlinesRepository);
    }
}
