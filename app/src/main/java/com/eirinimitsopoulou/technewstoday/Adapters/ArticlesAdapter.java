package com.eirinimitsopoulou.technewstoday.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eirinimitsopoulou.technewstoday.models.Article;
import com.eirinimitsopoulou.technewstoday.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class ArticlesAdapter extends BaseAdapter {
    @BindView(R.id.grid_title)
    TextView title;
    @BindView(R.id.grid_image)
    ImageView image;

    private Context context;
    private ArrayList<Article> data;

    public ArticlesAdapter(Context context, ArrayList<Article> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_item, null);
            ButterKnife.bind(this, grid);

            title.setText(data.get(position).getTitle());
            Picasso.with(context).load(data.get(position).getUrlToImage()).placeholder(R.drawable.no).into(image);

        } else {
            grid = (View) convertView;
        }

        return grid;
    }

    public void setData(ArrayList<Article> data) {
        this.data = data;
    }
}
