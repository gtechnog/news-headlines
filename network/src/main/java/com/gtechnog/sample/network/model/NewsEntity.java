package com.gtechnog.sample.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class NewsEntity {

    @SerializedName("title")
    private String title;

    @SerializedName("abstract")
    private String summary;

    @SerializedName("url")
    private String articleUrl;

    @SerializedName("byline")
    private String byline;

    @SerializedName("published_date")
    private String publishedDate;

    private List<MediaEntity> multimediaList;

    public List<MediaEntity> getMultimediaList() {
        return multimediaList;
    }

    public void setMediaList(List<MediaEntity> multimediaList) {
        this.multimediaList = multimediaList;
    }

    public String getUrl() {
        return articleUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity that = (NewsEntity) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(articleUrl, that.articleUrl) &&
                Objects.equals(byline, that.byline) &&
                Objects.equals(publishedDate, that.publishedDate) &&
                Objects.equals(multimediaList, that.multimediaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, summary, articleUrl, byline, publishedDate, multimediaList);
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", byline='" + byline + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", multimediaList=" + multimediaList +
                '}';
    }
}
