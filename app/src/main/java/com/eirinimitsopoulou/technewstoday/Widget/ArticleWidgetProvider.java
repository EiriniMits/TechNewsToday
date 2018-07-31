package com.eirinimitsopoulou.technewstoday.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.eirinimitsopoulou.technewstoday.R;
import com.eirinimitsopoulou.technewstoday.Activities.FavoriteActivity;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class ArticleWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_detail);

            Intent intent = new Intent(context, FavoriteActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.widget, pendingIntent);
            setRemoteAdapter(context, remoteViews);


            Intent clickIntentTemplate = new Intent(context, FavoriteActivity.class);
            PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(clickIntentTemplate)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setPendingIntentTemplate(R.id.widget_list_new, clickPendingIntentTemplate);
            remoteViews.setEmptyView(R.id.widget_list_new, R.id.widget_empty);


            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list_new);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


    }

    private void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.widget_list_new,
                new Intent(context, ArticleWidgetRemoteViewsService.class));
    }
}