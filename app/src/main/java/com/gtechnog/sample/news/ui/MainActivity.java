package com.gtechnog.sample.news.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.gtechnog.sample.news.R;
import com.gtechnog.sample.news.utils.InjectorUtils;
import com.gtechnog.sample.news.viewmodels.SharedNewsViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG_NEWS_LIST = "fragment_tag_news_list";
    private SharedNewsViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedViewModel = ViewModelProviders.of(this, InjectorUtils.getSharedNewsViewModelFactory(getApplication()))
                .get(SharedNewsViewModel.class);

        getSupportFragmentManager().beginTransaction().add(R.id.root_container, NewsListFragment.newInstance(), FRAGMENT_TAG_NEWS_LIST).commit();
    }
}
