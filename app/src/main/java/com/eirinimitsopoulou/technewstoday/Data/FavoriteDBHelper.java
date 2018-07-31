package com.eirinimitsopoulou.technewstoday.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.eirinimitsopoulou.technewstoday.Models.Article;

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

    public ArrayList<Article> getDataFromDB() {
        ArrayList<Article> modelList = new ArrayList<Article>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from " + FavoriteContract.ArticleEntry.TABLE_NAME;
        try {
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            if (cursor.moveToFirst()) {
                do {
                    Article model = new Article();
                    model.setAuthor(cursor.getString(cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_AUTHOR)));
                    model.setDescription(cursor.getString(cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_DESCRIPTION)));
                    model.setTitle(cursor.getString(cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_TITLE)));
                    model.setUrl(cursor.getString(cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_URL)));
                    model.setUrlToImage(cursor.getString(cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_IMAGE_URL)));
                    model.setPublishedAt(cursor.getString(cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_TIME)));
                    modelList.add(model);
                } while (cursor.moveToNext());
            }
            db.close();
            cursor.close();
            return modelList;
        } catch (SQLiteException e) {
            return new ArrayList<>();
        }
    }
}
