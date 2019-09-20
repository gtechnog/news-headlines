package com.gtechnog.sample.news.dagger;

import com.gtechnog.sample.news.viewmodels.NewsDetailViewModelFactory;
import com.gtechnog.sample.news.viewmodels.NewsListViewModelFactory;
import com.gtechnog.sample.news.viewmodels.SharedNewsViewModelFactory;

import dagger.Component;

@Component( modules = ViewModelFactoryModule.class)
public interface Injector {

    SharedNewsViewModelFactory getSharedNewsViewModelFactory();

    NewsListViewModelFactory getNewsListViewModelFactory();

    NewsDetailViewModelFactory getNewsDetailViewModelFactory();

}
