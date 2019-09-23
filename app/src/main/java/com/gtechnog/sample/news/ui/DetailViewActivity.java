package com.gtechnog.sample.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.gtechnog.sample.news.Constants;
import com.gtechnog.sample.news.R;
import com.gtechnog.sample.news.dagger.DaggerInjector;
import com.gtechnog.sample.news.dagger.FragmentModule;
import com.gtechnog.sample.news.dagger.Injector;

public class DetailViewActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG_NEWS_DETAIL = "fragment_tag_news_detail";
    private static final String TAG = DetailViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intent = getIntent();
        String newsEntityString = intent.getStringExtra(Constants.BUNDLE_EXTRA_KEYS_NEWS_ENTITY);
        if (newsEntityString != null && !newsEntityString.isEmpty()) {

            Injector injector = DaggerInjector.builder()
                    .fragmentModule(new FragmentModule(newsEntityString))
                    .build();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.root_container, injector.getNewsDetailFragment(), FRAGMENT_TAG_NEWS_DETAIL)
                    .commit();

        } else {
            Log.e(TAG, "onCreate: news entity either null or empty");
            finish();
        }
    }
}
