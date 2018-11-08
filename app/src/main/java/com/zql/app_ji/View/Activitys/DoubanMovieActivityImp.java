package com.zql.app_ji.View.Activitys;

import android.app.Application;
import android.content.Context;

import com.zql.app_ji.Bean.DetailDoubanMovie;

public interface DoubanMovieActivityImp {
    public void setDetailDoubanMovieonActivity(DetailDoubanMovie detailDoubanMovie);//加载信息到Activity
    public Context getBasecontext();
    public Application getbaseapplication();
}
