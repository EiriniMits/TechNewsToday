package com.eirinimitsopoulou.technewstoday.Models;

import java.util.List;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class Source {
    private String source;
    private List<Article> articles;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Article> getArticles() {
        return articles;
    }

}
