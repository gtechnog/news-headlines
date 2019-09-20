package com.gtechnog.sample.news.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.news.R;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.HeadlineViewHolder> {

    interface OnItemClickListener {
        void onItemClick(View view);
    }

    private List<NewsEntity> newsEntityList;
    private static OnItemClickListener onItemClickListener;

    NewsListAdapter(List<NewsEntity> list, OnItemClickListener listener) {
        this.newsEntityList = list;
        onItemClickListener = listener;
    }

    @NonNull
    @Override
    public HeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new HeadlineViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlineViewHolder holder, int position) {
        holder.titleText.setText(newsEntityList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return newsEntityList.size();
    }

    static class HeadlineViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        final TextView titleText;

        HeadlineViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.news_item_image);
            titleText = itemView.findViewById(R.id.news_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v);
                    }
                }
            });
        }
    }
}
