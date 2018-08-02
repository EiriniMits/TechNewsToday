package com.eirinimitsopoulou.technewstoday.interfaces;

import com.eirinimitsopoulou.technewstoday.models.Source;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleApiInterface {

    @GET("articles")
    Call<Source> getNews(@Query("source") String source , @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);
}