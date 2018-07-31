package com.eirinimitsopoulou.technewstoday.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eirinimitsopoulou.technewstoday.Models.Article;
import com.eirinimitsopoulou.technewstoday.Data.FavoriteContract;
import com.eirinimitsopoulou.technewstoday.Data.FavoriteDBHelper;

import com.eirinimitsopoulou.technewstoday.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.detail_view_title)
    TextView title;
    @BindView(R.id.detail_view_description)
    TextView description;
    @BindView(R.id.detail_view_iamgeview)
    ImageView imageview;
    @BindView(R.id.detail_view_source)
    TextView author;
    @BindView(R.id.read_complete)
    Button readComplete;
    @BindView((R.id.heart_button))
    FloatingActionButton heartButton;

    AdView mAdview;
    public boolean favourite;
    public Article news;
    private SQLiteDatabase movieDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        mAdview = (AdView) findViewById(R.id.adView);
        AdRequest adrequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdview.loadAd(adrequest);
        ButterKnife.bind(this);
        FavoriteDBHelper dbHelper = new FavoriteDBHelper(this);
        movieDb = dbHelper.getWritableDatabase();

        getSupportActionBar().hide();

        Intent i = getIntent();
        news = (Article) i.getSerializableExtra("news");

        title.setText(news.getTitle());
        description.setText(news.getDescription());
        author.setText(news.getAuthor());
        Picasso.with(this).load(news.getUrlToImage()).placeholder(R.drawable.no).into(imageview);

        final String url = news.getUrl();
        readComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, ReadCompleteActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        favourite = favExists(url);

        if (favourite) {
            favourite = true;
            heartButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_like, null));
        } else {
            favourite = false;
            heartButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_like_white, null));
        }

        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favourite) {
                    heartButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_like_white, null));
                    deleteFromFavorite();
                } else {
                    heartButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_like, null));
                    addToFavourite();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean favExists(String url) {
        String selectString = "SELECT * FROM " + FavoriteContract.ArticleEntry.TABLE_NAME +
                " WHERE " + FavoriteContract.ArticleEntry.COLUMN_ARTICLE_URL +
                " =?";
        Cursor cursor = movieDb.rawQuery(selectString, new String[]{url});
        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
            }
        }
        cursor.close();
        return hasObject;
    }

    public void addToFavourite() {

        favourite = true;

        ContentValues articleValues = new ContentValues();
        articleValues.put(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_AUTHOR,
                news.getAuthor());
        articleValues.put(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_TITLE,
                news.getTitle());
        articleValues.put(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_DESCRIPTION,
                news.getDescription());
        articleValues.put(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_URL,
                news.getUrl());
        articleValues.put(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_IMAGE_URL,
                news.getUrlToImage());
        articleValues.put(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_TIME,
                news.getPublishedAt());
        getContentResolver().insert(
                FavoriteContract.ArticleEntry.CONTENT_URI,
                articleValues
        );
    }

    public void deleteFromFavorite() {

        favourite = false;

        getContentResolver().delete(FavoriteContract.ArticleEntry.CONTENT_URI,
                FavoriteContract.ArticleEntry.COLUMN_ARTICLE_URL + " = " + "'" + news.getUrl() + "'", null);

    }
}