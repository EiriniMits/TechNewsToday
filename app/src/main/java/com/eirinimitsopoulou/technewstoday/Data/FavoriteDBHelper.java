package com.eirinimitsopoulou.technewstoday.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.eirinimitsopoulou.technewstoday.models.Article;

import java.util.ArrayList;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class FavoriteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "articles.db";

    private static final int VERSION = 1;


    public FavoriteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "CREATE TABLE " + FavoriteContract.ArticleEntry.TABLE_NAME + " (" +
                FavoriteContract.ArticleEntry._ID + " INTEGER PRIMARY KEY, " +
                FavoriteContract.ArticleEntry.COLUMN_ARTICLE_AUTHOR + " TEXT, " +
                FavoriteContract.ArticleEntry.COLUMN_ARTICLE_TITLE + " TEXT NOT NULL, " +
                FavoriteContract.ArticleEntry.COLUMN_ARTICLE_DESCRIPTION + " TEXT, " +
                FavoriteContract.ArticleEntry.COLUMN_ARTICLE_URL + " TEXT, " +
                FavoriteContract.ArticleEntry.COLUMN_ARTICLE_IMAGE_URL + " TEXT, " +
                FavoriteContract.ArticleEntry.COLUMN_ARTICLE_TIME + " TEXT);";

        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteContract.ArticleEntry.TABLE_NAME);
        onCreate(db);
    }

    public boolean isEmpty() {
        boolean empty = true;
        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from " + FavoriteContract.ArticleEntry.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                empty = false;
            } else {
                empty = true;
            }
            db.close();
            cursor.close();
            return empty;
        } catch (SQLiteException e) {
            return empty;
        }
    }
}
