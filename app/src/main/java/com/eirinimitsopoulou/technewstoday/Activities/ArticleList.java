package com.eirinimitsopoulou.technewstoday.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.eirinimitsopoulou.technewstoday.adapters.ArticlesAdapter;
import com.eirinimitsopoulou.technewstoday.models.Article;
import com.eirinimitsopoulou.technewstoday.models.Source;
import com.eirinimitsopoulou.technewstoday.interfaces.ArticleApiInterface;
import com.eirinimitsopoulou.technewstoday.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.IntentService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.eirinimitsopoulou.technewstoday.BuildConfig;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class ArticleList extends AppCompatActivity {
    @BindView(R.id.grid_view)
    GridView gridView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView((R.id.toolbar))
    Toolbar toolbar;
    private String mSource = "";


    final static private String BASE_URL = "https://newsapi.org/v1/";
    private ArticlesAdapter newsAdapter;
    private ArrayList<Article> data;
    private ApiServive apiServive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this);

        Bundle thisBundlesExtras = getIntent().getExtras();

        mSource = thisBundlesExtras.getString("source");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mSource);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        apiServive = new ApiServive();
        apiServive.fetchNews();

        data = new ArrayList<Article>();
        newsAdapter = new ArticlesAdapter(this, data);

        gridView.setAdapter(new ArticlesAdapter(this, data));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ArticleList.this, DetailActivity.class);
                intent.putExtra("news", (Parcelable) data.get(position));
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        apiServive.cancel();
    }

    private class ApiServive extends IntentService {
        public Call<Source> call;


        public ApiServive() {
            super("ApiServive");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {

            fetchNews();
        }

        public void fetchNews() {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            ArticleApiInterface gerritAPI = retrofit.create(ArticleApiInterface.class);

            call = gerritAPI.getNews(mSource, "latest", BuildConfig.API_KEY);
            try {
                call.enqueue(new Callback<Source>() {

                    @Override
                    public void onResponse(Call<Source> call, Response<Source> response) {
                        if (response.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Source res = response.body();

                            for (Article news : res.getArticles()) {
                                data.add(news);
                            }
                            newsAdapter.setData(data);
                            newsAdapter.notifyDataSetChanged();
                            gridView.invalidateViews();
                        } else {
                            System.out.println(response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<Source> call, Throwable t) {
                        if (call.isCanceled()) {
                            Log.e("Retrofit call", "Cancelled Request!");
                        } else {
                            Log.e("Retrofit call", "No Network Connection!");
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void cancel() {
            call.cancel();
        }

    }
}