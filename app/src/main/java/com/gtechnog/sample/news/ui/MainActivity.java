package com.gtechnog.sample.news.ui;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.gtechnog.sample.news.R;
import com.gtechnog.sample.news.dagger.DaggerInjector;
import com.gtechnog.sample.news.dagger.FragmentModule;
import com.gtechnog.sample.news.dagger.Injector;
import com.gtechnog.sample.news.dagger.ViewModelFactoryModule;
import com.gtechnog.sample.news.viewmodels.SharedNewsViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String FRAGMENT_TAG_NEWS_LIST = "fragment_tag_news_list";
    public static final String FRAGMENT_TAG_NEWS_DETAILS = "fragment_tag_news_details";

    private SharedNewsViewModel mSharedViewModel;
    private ViewGroup mNewsListContainer, mNewsDetailContainer;
    private Injector injector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsListContainer = findViewById(R.id.news_list_container);
        mNewsDetailContainer = findViewById(R.id.news_detail_container);


        injector = DaggerInjector.builder()
                .viewModelFactoryModule(new ViewModelFactoryModule(this.getApplication()))
                .fragmentModule(new FragmentModule(""))
                .build();

        mSharedViewModel = ViewModelProviders.of(this, injector.getSharedNewsViewModelFactory())
                .get(SharedNewsViewModel.class);

        setupFragments();
    }

    private void setupFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(mNewsListContainer.getId(), injector.getNewsListFragment(), FRAGMENT_TAG_NEWS_LIST);

        if (mSharedViewModel.isDualPaneMode()) {
            fragmentTransaction.add(mNewsDetailContainer.getId(), injector.getNewsDetailFragment(), FRAGMENT_TAG_NEWS_DETAILS);
        }

        fragmentTransaction.commit();
    }
}
