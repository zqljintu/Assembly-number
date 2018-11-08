package com.zql.app_ji.View.Activitys;

import android.app.Application;
import android.content.Context;

import com.zql.app_ji.Bean.Entity.MovieEntity;
import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.Entity.ZhihuEntity;

import java.util.List;

public interface FavoriteActivityImp {
    public void setFavoriteDataonRecyclerView(List<ZhihuEntity> favoriteEntyList);//获取日报数据库数据加载到界面
    public void setFavoriteMovieDataonRecyclerView(List<MovieEntity> movieEntities);//获取电影数据加载到界面
    public void setFavoriteWanDataonRecyclerView(List<WanEntity> wanEntities);//获取code数据
    public Context getBasecontext();
    public Application getbaseapplication();
}
