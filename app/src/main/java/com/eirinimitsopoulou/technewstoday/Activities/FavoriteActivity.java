package com.eirinimitsopoulou.technewstoday.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DefaultItemAnimator;

import com.eirinimitsopoulou.technewstoday.Adapters.FavoriteAdapter;
import com.eirinimitsopoulou.technewstoday.Data.FavoriteDBHelper;
import com.eirinimitsopoulou.technewstoday.Models.Article;
import com.eirinimitsopoulou.technewstoday.R;

import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class FavoriteActivity extends AppCompatActivity {


    @BindView(R.id.recyclerView_favorite)
    RecyclerView recyclerView;

    private FavoriteAdapter mAdapter;

    private FavoriteDBHelper favoriteDBHelper;
    private ArrayList<Article> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.my_favorite_title);

        favoriteDBHelper = new FavoriteDBHelper(this);

        mAdapter = new FavoriteAdapter(this, mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareContactData();
    }

    private void prepareContactData() {

        Article article;
        List<Article> articles = favoriteDBHelper.getDataFromDB();
        if (articles.isEmpty()) {
            Toast.makeText(FavoriteActivity.this, R.string.no_favorites, Toast.LENGTH_LONG).show();
        }
        for (Article con : articles) {
            article = new Article(con.getAuthor(), con.getDescription(), con.getTitle(), con.getUrl(), con.getUrlToImage(), con.getPublishedAt());
            mList.add(article);
            mAdapter.notifyDataSetChanged();
        }

    }

}

