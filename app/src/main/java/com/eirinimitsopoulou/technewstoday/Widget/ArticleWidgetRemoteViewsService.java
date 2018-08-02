package com.eirinimitsopoulou.technewstoday.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class ArticleWidgetRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ArticleWidgetRemoteViewsFactory(this, intent);
    }
}
