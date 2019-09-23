package com.gtechnog.sample.news.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gtechnog.sample.network.model.MediaEntity;
import com.gtechnog.sample.network.model.NewsEntity;
import com.gtechnog.sample.news.R;
import com.gtechnog.sample.news.media.ImageHelper;
import com.gtechnog.sample.news.media.MediaHelper;
import com.gtechnog.sample.news.media.MediaType;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.HeadlineViewHolder> {

    interface OnItemClickListener {
        void onItemClick(View view);
    }

    private List<NewsEntity> newsEntityList;
    private static OnItemClickListener onItemClickListener;
    private ImageHelper imageHelper;

    NewsListAdapter(List<NewsEntity> list, OnItemClickListener listener, ImageHelper imageHelper) {
        this.newsEntityList = list;
        onItemClickListener = listener;
        this.imageHelper = imageHelper;
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
        MediaEntity entity = newsEntityList.get(position).getMediaEntityByType(MediaHelper.getMediaFormatStringByMediaType(MediaType.STANDARD_THUMBNAIL));

        if (entity != null) {
            imageHelper.loadImageUrl(holder.imageView, entity.getUrl());
        } else {
            holder.imageView.setImageResource(R.drawable.place_holder);
        }
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
