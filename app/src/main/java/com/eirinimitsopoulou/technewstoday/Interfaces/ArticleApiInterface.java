package com.eirinimitsopoulou.technewstoday.Interfaces;

import com.eirinimitsopoulou.technewstoday.Models.Source;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleApiInterface {

    @GET("articles")
    Call<Source> getNews(@Query("source") String source , @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);
}