package com.eirinimitsopoulou.technewstoday.models;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class Article implements Parcelable {
    private String author;
    private String description;
    private String title;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public Article() {
    }

    public Article(Parcel in) {
        author = in.readString();
        description  = in.readString();
        title   = in.readString();
        url  = in.readString();
        urlToImage  = in.readString();
        publishedAt  = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(description);
        parcel.writeString(title);
        parcel.writeString(url);
        parcel.writeString(urlToImage);
        parcel.writeString(publishedAt);

    }
}
