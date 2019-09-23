package com.gtechnog.sample.news.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.news.Constants;
import com.gtechnog.sample.news.R;
import com.gtechnog.sample.news.dagger.DaggerInjector;
import com.gtechnog.sample.news.dagger.Injector;
import com.gtechnog.sample.news.dagger.ViewModelFactoryModule;
import com.gtechnog.sample.news.viewmodels.NewsDetailViewModel;

import java.lang.reflect.Type;

public class NewsDetailFragment extends Fragment {

    private NewsDetailViewModel mViewModel;
    private TextView titleTextView;
    private TextView summaryTextView;
    private Button fullLinkButton;

    public NewsDetailFragment() {

    }

    public static NewsDetailFragment newInstance(String newsEntity) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_EXTRA_KEYS_NEWS_ENTITY, newsEntity);
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        newsDetailFragment.setArguments(bundle);
        return newsDetailFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_detail_fragment, container, false);
        titleTextView = view.findViewById(R.id.title);
        summaryTextView = view.findViewById(R.id.summary_content);
        fullLinkButton = view.findViewById(R.id.full_story_link);

        setClickListeners();
        return view;
    }

    private void setClickListeners() {
        fullLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullLinkDetailInBrowser();
            }
        });
    }

    private void showFullLinkDetailInBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mViewModel.getUrl()));
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        String newsEntityString = bundle.getString(Constants.BUNDLE_EXTRA_KEYS_NEWS_ENTITY);

        Type type = new TypeToken<NewsEntity>(){}.getType();
        NewsEntity newsEntity = (new Gson()).fromJson(newsEntityString, type);

        Injector injector = DaggerInjector.builder()
                .viewModelFactoryModule(new ViewModelFactoryModule(getActivity().getApplication(), newsEntity))
                .build();
        mViewModel = ViewModelProviders.of(this, injector
                .getNewsDetailViewModelFactory())
                .get(NewsDetailViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        titleTextView.setText(mViewModel.getTitle());
        summaryTextView.setText(mViewModel.getSummary());
    }
}
