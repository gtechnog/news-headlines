package com.gtechnog.sample.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;

public class HeadlinesResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("section")
    private String section;

    @SerializedName("num_results")
    private int resultsCount;

    @SerializedName("results")
    private ArrayList<NewsEntity> newsEntities;

    public ArrayList<NewsEntity> getNewsEntities() {
        return newsEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeadlinesResponse that = (HeadlinesResponse) o;
        return resultsCount == that.resultsCount &&
                Objects.equals(status, that.status) &&
                Objects.equals(section, that.section) &&
                Objects.equals(newsEntities, that.newsEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, section, resultsCount, newsEntities);
    }

    @Override
    public String toString() {
        return "HeadlinesResponse{" +
                "status='" + status + '\'' +
                ", section='" + section + '\'' +
                ", resultsCount=" + resultsCount +
                ", newsEntities=" + newsEntities +
                '}';
    }
}
