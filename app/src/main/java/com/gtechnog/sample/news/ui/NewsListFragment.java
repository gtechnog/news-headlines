package com.gtechnog.sample.news.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.news.Constants;
import com.gtechnog.sample.news.R;
import com.gtechnog.sample.news.dagger.DaggerInjector;
import com.gtechnog.sample.news.dagger.Injector;
import com.gtechnog.sample.news.dagger.ViewModelFactoryModule;
import com.gtechnog.sample.news.viewmodels.NewsListViewModel;
import com.gtechnog.sample.news.viewmodels.SharedNewsViewModel;

import java.util.List;

public class NewsListFragment extends Fragment {

    private NewsListViewModel mViewModel;
    private SharedNewsViewModel mSharedViewModel;

    private RecyclerView recyclerView;

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.news_list);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void dispatchToDetailActivity(NewsEntity entity) {
        Intent intent = new Intent(getActivity(), DetailViewActivity.class);
        String entityString = (new Gson()).toJson(entity);
        intent.putExtra(Constants.BUNDLE_EXTRA_KEYS_NEWS_ENTITY, entityString);
        startActivity(intent);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Injector injector = DaggerInjector.builder()
                .viewModelFactoryModule(new ViewModelFactoryModule(getActivity().getApplication()))
                .build();

        mViewModel = ViewModelProviders.of(this, injector.getNewsListViewModelFactory())
                .get(NewsListViewModel.class);
        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedNewsViewModel.class);
        registerObservers();
    }

    private void registerObservers() {
        mViewModel.getNewsList().observe(this, new Observer<List<NewsEntity>>() {

            @Override
            public void onChanged(List<NewsEntity> list) {
                recyclerView.setAdapter(new NewsListAdapter(list, onItemClickListener));
            }
        });
    }

    private NewsListAdapter.OnItemClickListener onItemClickListener = new NewsListAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view) {
            int position = recyclerView.getChildAdapterPosition(view);
            NewsEntity entity = mViewModel.getNewsList().getValue().get(position);
            if (mSharedViewModel.isDualPaneMode()) {
                mSharedViewModel.setCurrentSelectedNewsItem(entity);
            } else {
                dispatchToDetailActivity(entity);
            }
        }
    };
}
