package com.ultimatix.sportskeeda.network;

import com.ultimatix.sportskeeda.datamodel.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface SKService {
    @Headers({"Accept: application/json"})
    @GET("/feed.json")
    Call<Feed> getFeed();
}
