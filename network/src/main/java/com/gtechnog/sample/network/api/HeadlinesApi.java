package com.gtechnog.sample.network.api;

import com.gtechnog.sample.network.model.HeadlinesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HeadlinesApi {

    @GET("bins/nl6jh")
    Call<HeadlinesResponse> getHeadlines();
}
