package com.gtechnog.sample.news.dagger;

import com.gtechnog.sample.news.ui.DetailViewFragment;
import com.gtechnog.sample.news.ui.NewsListFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private String newsEntity;

    FragmentModule() {

    }

    public FragmentModule(String newsEntity) {
        this.newsEntity = newsEntity;
    }

    @Provides
    NewsListFragment provideNewsListFragment() {
        return NewsListFragment.newInstance();
    }

    @Provides
    DetailViewFragment provideNewsDetailFragment() {
        return DetailViewFragment.newInstance(newsEntity);
    }
}
