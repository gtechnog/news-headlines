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
import androidx.lifecycle.Observer;
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
import com.gtechnog.sample.news.viewmodels.SharedNewsViewModel;

import java.lang.reflect.Type;

public class DetailViewFragment extends Fragment {

    private NewsDetailViewModel mViewModel;
    private SharedNewsViewModel mSharedViewModel;

    private TextView titleTextView;
    private TextView summaryTextView;
    private Button fullLinkButton;
    private ViewGroup detailViewLayout;

    public DetailViewFragment() {

    }

    public static DetailViewFragment newInstance(@NonNull String newsEntity) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_EXTRA_KEYS_NEWS_ENTITY, newsEntity);
        DetailViewFragment detailViewFragment = new DetailViewFragment();
        detailViewFragment.setArguments(bundle);
        return detailViewFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_detail_fragment, container, false);
        detailViewLayout = view.findViewById(R.id.detail_view_layout);
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
        String newsEntityString = bundle.getString(Constants.BUNDLE_EXTRA_KEYS_NEWS_ENTITY, "");

        NewsEntity newsEntity = null;
        if (!newsEntityString.isEmpty()) {
            Type type = new TypeToken<NewsEntity>(){}.getType();
            newsEntity = (new Gson()).fromJson(newsEntityString, type);
            detailViewLayout.setVisibility(View.VISIBLE);
        } else {
            detailViewLayout.setVisibility(View.GONE);
        }

        Injector injector = DaggerInjector.builder()
                .viewModelFactoryModule(new ViewModelFactoryModule(getActivity().getApplication(), newsEntity))
                .build();
        mViewModel = ViewModelProviders.of(this, injector
                .getNewsDetailViewModelFactory())
                .get(NewsDetailViewModel.class);

        if (mViewModel.isDualPaneMode()) {
            mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedNewsViewModel.class);
        }

        registerObservers();
    }

    private void registerObservers() {

        mViewModel.getmNewsEntity().observe(this, new Observer<NewsEntity>() {
            @Override
            public void onChanged(NewsEntity newsEntity) {
                if (newsEntity != null) {
                    updateUIData();
                }
            }
        });

        if (mViewModel.isDualPaneMode()) {
            mSharedViewModel.getSelectedNewsItem().observe(this, new Observer<NewsEntity>() {

                @Override
                public void onChanged(NewsEntity newsEntity) {
                    mViewModel.setNewsEntity(newsEntity);
                }
            });
        }
    }

    private void updateUIData() {
        detailViewLayout.setVisibility(View.VISIBLE);
        titleTextView.setText(mViewModel.getTitle());
        summaryTextView.setText(mViewModel.getSummary());
    }
}
