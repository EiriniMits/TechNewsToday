package com.eirinimitsopoulou.technewstoday.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.database.Cursor;
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

import com.eirinimitsopoulou.technewstoday.activities.ReadCompleteActivity;
import com.eirinimitsopoulou.technewstoday.data.FavoriteContract;
import com.eirinimitsopoulou.technewstoday.models.Article;
import com.eirinimitsopoulou.technewstoday.R;
import com.squareup.picasso.Picasso;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteArticleViewHolder> {

    private Context mContext;
    private ArrayList<Article> mArticlesList;
    private Article news;
    private Cursor mCursor;

    private final FavoriteAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface FavoriteAdapterOnClickHandler {
        void onFavoriteArticleClick(Article article);
    }

    public FavoriteAdapter(Context mContext, ArrayList<Article> mArticlesList, FavoriteAdapterOnClickHandler clickHandler) {
        this.mContext = mContext;
        mClickHandler = clickHandler;
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
                builder.setMessage(R.string.message).setPositiveButton(R.string.yes, dialogClickListener)
                        .setNegativeButton(R.string.no, dialogClickListener).show();
            }
        });
    }

    public Cursor swapCursor(Cursor cursor) {

        if (mCursor == cursor) {
            return null;
        }


        Cursor temp = mCursor;
        this.mCursor = cursor;
        this.mArticlesList.clear();

        if (cursor != null && cursor.moveToFirst()) {
            int articleAuthorIndex = cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_AUTHOR);
            int articleTitleIndex = cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_TITLE);
            int articleDescriptionIndex = cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_DESCRIPTION);
            int articleUrlIndex = cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_URL);
            int articleUrlImageIndex = cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_IMAGE_URL);
            int articlePublishedIndex = cursor.getColumnIndex(FavoriteContract.ArticleEntry.COLUMN_ARTICLE_TIME);

            do {
                String articleAuthor = cursor.getString(articleAuthorIndex);
                String articleTitle = cursor.getString(articleTitleIndex);
                String articleDescription = cursor.getString(articleDescriptionIndex);
                String articleUrl = cursor.getString(articleUrlIndex);
                String articleUrlImage = cursor.getString(articleUrlImageIndex);
                String articlePublished = cursor.getString(articlePublishedIndex);

                Article article = new Article();
                article.setAuthor(articleAuthor);
                article.setTitle(articleTitle);
                article.setDescription(articleDescription);
                article.setUrl(articleUrl);
                article.setUrlToImage(articleUrlImage);
                article.setPublishedAt(articlePublished);
                mArticlesList.add(article);
            }
            while (cursor.moveToNext());

            this.notifyDataSetChanged();
        }
        return temp;
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
