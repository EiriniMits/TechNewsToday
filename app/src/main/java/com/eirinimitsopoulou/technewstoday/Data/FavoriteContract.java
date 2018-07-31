package com.eirinimitsopoulou.technewstoday.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class FavoriteContract {

    public static final String AUTHORITY = "com.eirinimitsopoulou.technewstoday";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    public static final String PATH_ARTICLES = "articles";


    public static final class ArticleEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTICLES).build();

        public static final String TABLE_NAME = "articles";
        public static final String COLUMN_ARTICLE_AUTHOR = "author";
        public static final String COLUMN_ARTICLE_TITLE = "title";
        public static final String COLUMN_ARTICLE_DESCRIPTION = "description";
        public static final String COLUMN_ARTICLE_URL = "url";
        public static final String COLUMN_ARTICLE_IMAGE_URL = "imageurl";
        public static final String COLUMN_ARTICLE_TIME = "published";
    }
}