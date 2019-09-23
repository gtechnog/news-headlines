package com.gtechnog.sample.network.repository;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gtechnog.sample.network.api.HeadlinesApi;
import com.gtechnog.sample.network.constants.Constants;
import com.gtechnog.sample.network.model.HeadlinesResponse;
import com.gtechnog.sample.network.model.NewsEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeadlinesRepository {

    private static final String TAG = HeadlinesRepository.class.getSimpleName();
    private static HeadlinesRepository sInstance;
    private final Retrofit retrofit;
    private final HeadlinesApi headlinesApi;

    private HeadlinesRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapter(new TypeToken<NewsEntity>(){}.getType(), new NewsEntityDesrializer())
                        .create()))
                .build();
        headlinesApi = retrofit.create(HeadlinesApi.class);
    }

    public static HeadlinesRepository getsInstance() {
        if (sInstance == null) {
            synchronized (HeadlinesRepository.class) {
                if (sInstance == null) {
                    sInstance = new HeadlinesRepository();
                }
            }
        }
        return sInstance;
    }

    public void getHeadlines(final com.gtechnog.sample.network.api.Callback<HeadlinesResponse, Throwable> callback) {
        Call<HeadlinesResponse> call =  headlinesApi.getHeadlines();
        call.enqueue(new Callback<HeadlinesResponse>() {
            @Override
            public void onResponse(Call<HeadlinesResponse> call, Response<HeadlinesResponse> response) {
                if (callback != null && response.body() != null) {
                    callback.onResult(response.body());
                    Log.d(TAG, "onResponse: success" + response.body());
                }
            }

            @Override
            public void onFailure(Call<HeadlinesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
                if (callback != null) {
                    callback.onError(t);
                }
            }
        });
    }
}
