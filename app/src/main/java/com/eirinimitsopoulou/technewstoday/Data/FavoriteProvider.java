package com.eirinimitsopoulou.technewstoday.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import static com.eirinimitsopoulou.technewstoday.data.FavoriteContract.ArticleEntry.TABLE_NAME;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class FavoriteProvider extends ContentProvider {


    public static final int ARTICLES = 100;
    private static final UriMatcher mUriMatcher = buildUriMatcher();


    public static UriMatcher buildUriMatcher() {

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(FavoriteContract.AUTHORITY, FavoriteContract.PATH_ARTICLES, ARTICLES);
        return uriMatcher;
    }

    private FavoriteDBHelper mArticleDBHelper;


    @Override
    public boolean onCreate() {
        Context context = getContext();
        mArticleDBHelper = new FavoriteDBHelper(context);
        return true;
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {

        final SQLiteDatabase db = mArticleDBHelper.getWritableDatabase();

        int match = mUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case ARTICLES:

                long id = db.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(FavoriteContract.ArticleEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row" + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }


    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        final SQLiteDatabase db = mArticleDBHelper.getReadableDatabase();

        int match = mUriMatcher.match(uri);
        Cursor retCursor;

        switch (match) {
            case ARTICLES:
                retCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            // Default exception
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }


    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mArticleDBHelper.getWritableDatabase();

        int match = mUriMatcher.match(uri);
        int tasksDeleted;

        if (null == selection) {
            selection = "1";
        }

        switch (match) {

            case ARTICLES:
                tasksDeleted = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (tasksDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return tasksDeleted;
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public String getType(@NonNull Uri uri) {

        throw new UnsupportedOperationException("Not implemented");
    }

}
