package com.gtechnog.sample.news.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.news.Constants;
import com.gtechnog.sample.news.R;
import com.gtechnog.sample.news.utils.InjectorUtils;
import com.gtechnog.sample.news.viewmodels.NewsDetailViewModel;

import java.lang.reflect.Type;

public class NewsDetailFragment extends Fragment {

    private NewsDetailViewModel mViewModel;
    private TextView textView;

    static NewsDetailFragment newInstance(String newsEntity) {
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
        textView = view.findViewById(R.id.title);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        String newsEntityString = bundle.getString(Constants.BUNDLE_EXTRA_KEYS_NEWS_ENTITY);
        Type type = new TypeToken<NewsEntity>(){}.getType();
        NewsEntity newsEntity = (new Gson()).fromJson(newsEntityString, type);

        mViewModel = ViewModelProviders.of(this, InjectorUtils
                .getNewsDetailViewModelFactory(getActivity().getApplication(), newsEntity))
                .get(NewsDetailViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        textView.setText(mViewModel.getTitle());
    }
}
