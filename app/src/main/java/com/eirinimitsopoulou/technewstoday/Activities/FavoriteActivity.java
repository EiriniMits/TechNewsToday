package com.eirinimitsopoulou.technewstoday.activities;

import android.os.Bundle;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DefaultItemAnimator;

import com.eirinimitsopoulou.technewstoday.adapters.FavoriteAdapter;
import com.eirinimitsopoulou.technewstoday.data.FavoriteContract;
import com.eirinimitsopoulou.technewstoday.data.FavoriteDBHelper;
import com.eirinimitsopoulou.technewstoday.models.Article;
import com.eirinimitsopoulou.technewstoday.R;

import java.util.ArrayList;

import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class FavoriteActivity extends AppCompatActivity implements FavoriteAdapter.FavoriteAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.recyclerView_favorite)
    RecyclerView recyclerView;

    private FavoriteAdapter mAdapter;
    private static final int ARTICLE_LOADER_ID = 0;

    private FavoriteDBHelper favoriteDBHelper;
    private ArrayList<Article> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.my_favorite_title);

        favoriteDBHelper = new FavoriteDBHelper(this);

        mAdapter = new FavoriteAdapter(this, mList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(ARTICLE_LOADER_ID, null, this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(ARTICLE_LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {

            Cursor cursor = null;

            @Override
            protected void onStartLoading() {
                if (cursor != null) {
                    deliverResult(cursor);
                } else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {

                try {
                    return getContentResolver().query(FavoriteContract.ArticleEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            null);

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Cursor data) {
                cursor = data;
                super.deliverResult(data);
            }
        };
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        boolean empty = favoriteDBHelper.isEmpty();
        if (empty) {
            Toast.makeText(FavoriteActivity.this, R.string.no_favorites, Toast.LENGTH_LONG).show();
        }
        mAdapter.swapCursor(data);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onFavoriteArticleClick(Article article) {

    }
}

