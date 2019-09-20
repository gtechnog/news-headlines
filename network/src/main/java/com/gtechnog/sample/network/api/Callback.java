package com.gtechnog.sample.network.api;

public interface Callback<R, E> {
    void onResult(R result);
    void onError(E error);
}
