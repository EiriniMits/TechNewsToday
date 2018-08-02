package com.eirinimitsopoulou.technewstoday.activities;

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

        arsTechnica.setOnClickListener(myhandlerOnClickListener);
        engadget.setOnClickListener(myhandlerOnClickListener);
        techCrunch.setOnClickListener(myhandlerOnClickListener);
        techRadar.setOnClickListener(myhandlerOnClickListener);
        theNextWeb.setOnClickListener(myhandlerOnClickListener);
        verge.setOnClickListener(myhandlerOnClickListener);
        recode.setOnClickListener(myhandlerOnClickListener);
        grunderszene.setOnClickListener(myhandlerOnClickListener);
        t3n.setOnClickListener(myhandlerOnClickListener);
        hacker_news.setOnClickListener(myhandlerOnClickListener);
    }

    private View.OnClickListener myhandlerOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ars_technica:
                    displaySourceArticles("ars-technica");
                    break;
                case R.id.engadget:
                    displaySourceArticles("engadget");
                    break;
                case R.id.techcrunch:
                    displaySourceArticles("techcrunch");
                    break;
                case R.id.techradar:
                    displaySourceArticles("techradar");
                    break;
                case R.id.tnw:
                    displaySourceArticles("the-next-web");
                    break;
                case R.id.verge:
                    displaySourceArticles("the-verge");
                    break;
                case R.id.recode:
                    displaySourceArticles("recode");
                    break;
                case R.id.grunderszene:
                    displaySourceArticles("gruenderszene");
                    break;
                case R.id.t3n:
                    displaySourceArticles("t3n");
                    break;
                case R.id.hackernews:
                    displaySourceArticles("hacker-news");
                    break;
            }
        }
    };

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


