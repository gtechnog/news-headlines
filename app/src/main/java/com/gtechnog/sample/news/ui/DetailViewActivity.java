package com.gtechnog.sample.news.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.news.Constants;
import com.gtechnog.sample.news.R;

import java.lang.reflect.Type;

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

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.root_container, NewsDetailFragment.newInstance(newsEntityString), FRAGMENT_TAG_NEWS_DETAIL)
                    .commit();

        } else {
            Log.e(TAG, "onCreate: news entity either null or empty");
            finish();
        }
    }
}
