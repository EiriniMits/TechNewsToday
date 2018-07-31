package com.eirinimitsopoulou.technewstoday.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.eirinimitsopoulou.technewstoday.Adapters.ArticlesAdapter;
import com.eirinimitsopoulou.technewstoday.Models.Article;
import com.eirinimitsopoulou.technewstoday.Models.Source;
import com.eirinimitsopoulou.technewstoday.Interfaces.ArticleApiInterface;
import com.eirinimitsopoulou.technewstoday.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
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
    private String mSource = "";


    final static private String BASE_URL = "https://newsapi.org/v1/";
    private ArticlesAdapter newsAdapter;
    private ArrayList<Article> data;
    private LoadNews loadNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this);

        Bundle thisBundlesExtras = getIntent().getExtras();

        mSource = thisBundlesExtras.getString("source");

        getSupportActionBar().setTitle(mSource);

        loadNews = new LoadNews();
        loadNews.starts();

        data = new ArrayList<Article>();
        newsAdapter = new ArticlesAdapter(this, data);

        gridView.setAdapter(new ArticlesAdapter(this, data));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ArticleList.this, DetailActivity.class);
                intent.putExtra("news", (Serializable) data.get(position));
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        loadNews.cancel();
    }

    private class LoadNews implements Callback<Source> {
        public Call<Source> call;

        public void starts() {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            ArticleApiInterface gerritAPI = retrofit.create(ArticleApiInterface.class);

            call = gerritAPI.getNews(mSource, "latest", BuildConfig.API_KEY);
            call.enqueue(this);

        }

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

        public void cancel() {
            call.cancel();
        }
    }
}
