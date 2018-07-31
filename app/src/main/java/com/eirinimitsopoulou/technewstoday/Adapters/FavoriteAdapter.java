package com.eirinimitsopoulou.technewstoday.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.eirinimitsopoulou.technewstoday.Activities.ReadCompleteActivity;
import com.eirinimitsopoulou.technewstoday.Data.FavoriteContract;
import com.eirinimitsopoulou.technewstoday.Models.Article;
import com.eirinimitsopoulou.technewstoday.R;
import com.squareup.picasso.Picasso;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteArticleViewHolder> {

    private Context mContext;
    private ArrayList<Article> mArticlesList;
    private Article news;


    public FavoriteAdapter(Context mContext, ArrayList<Article> mArticlesList) {
        this.mContext = mContext;
        this.mArticlesList = mArticlesList;
    }


    @Override
    public FavoriteArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.recycler_item, parent, false);

        return new FavoriteArticleViewHolder(view);
    }


    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    public void onBindViewHolder(FavoriteArticleViewHolder holder, final int position) {

        news = mArticlesList.get(position);
        Picasso.with(mContext)
                .load(news.getUrlToImage())
                .error(R.drawable.no)
                .into(holder.thumbnail);
        holder.title.setText(news.getTitle());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news = mArticlesList.get(position);
                Intent intent = new Intent(mContext, ReadCompleteActivity.class);
                final String url = news.getUrl();
                intent.putExtra("url", url);
                mContext.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                news = mArticlesList.get(position);
                                mContext.getContentResolver().delete(FavoriteContract.ArticleEntry.CONTENT_URI,
                                        FavoriteContract.ArticleEntry.COLUMN_ARTICLE_URL + " = " + "'" + news.getUrl() + "'", null);
                                mArticlesList.remove(position);
                                notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage(R.string.message).setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticlesList.size();
    }


    class FavoriteArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.news_title)
        TextView title;
        @BindView(R.id.delete)
        Button delete;
        @BindView(R.id.button2)
        Button button;


        public FavoriteArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
