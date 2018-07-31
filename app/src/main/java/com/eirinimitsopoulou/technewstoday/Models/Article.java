package com.eirinimitsopoulou.technewstoday.Models;

import java.io.Serializable;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class Article implements Serializable {
    private String author;
    private String description;
    private String title;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public Article() {
    }

    public Article(String author, String description, String title, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.description = description;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

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
}
