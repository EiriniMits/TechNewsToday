package com.eirinimitsopoulou.technewstoday.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.eirinimitsopoulou.technewstoday.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ars_technica)
    LinearLayout arsTechnica;
    @BindView(R.id.engadget)
    LinearLayout engadget;
    @BindView(R.id.techcrunch)
    LinearLayout techCrunch;
    @BindView(R.id.techradar)
    LinearLayout techRadar;
    @BindView(R.id.tnw)
    LinearLayout theNextWeb;
    @BindView(R.id.verge)
    LinearLayout verge;
    @BindView(R.id.recode)
    LinearLayout recode;
    @BindView(R.id.grunderszene)
    LinearLayout grunderszene;
    @BindView(R.id.t3n)
    LinearLayout t3n;
    @BindView(R.id.hackernews)
    LinearLayout hacker_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        arsTechnica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("ars-technica");
            }
        });

        engadget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("engadget");
            }
        });

        techCrunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("techcrunch");
            }
        });

        techRadar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("techradar");
            }
        });

        theNextWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("the-next-web");
            }
        });

        verge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("the-verge");
            }
        });

        recode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("recode");
            }
        });

        grunderszene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("gruenderszene");
            }
        });

        t3n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("t3n");
            }
        });

        hacker_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySourceArticles("hacker-news");
            }
        });
    }

    private void displaySourceArticles(String newsSource) {
        Intent openNewsResultsView = new Intent(getApplicationContext(), ArticleList.class);
        Bundle theNewsSource = new Bundle();
        theNewsSource.putString("source", newsSource);
        openNewsResultsView.putExtras(theNewsSource);
        startActivity(openNewsResultsView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorites) {
            startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


