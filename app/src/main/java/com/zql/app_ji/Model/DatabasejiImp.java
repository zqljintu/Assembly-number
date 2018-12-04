package com.zql.app_ji.Model;

import com.zql.app_ji.Bean.Entity.MovieEntity;
import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.Entity.ZhihuEntity;

import java.util.List;

public interface DatabasejiImp {
    public void insertZhihuEntitytoDB(ZhihuEntity zhihuEntity);//将知乎日报加入数据库
    public List<ZhihuEntity> queryAllZhihuEntityfromDB();//从数据库加载全部数据
    public boolean hascontainsTheZhihuEntityfromDB(ZhihuEntity zhihuEntity);//是否存在这条数据
    public void insertMovieEntitytoDB(MovieEntity movieEntity);//将电影加入数据库
    public List<MovieEntity> queryAllMovieEntityfromDB();//从数据库加载全部数据
    public boolean hascontainsTheMovieEntityfromDB(MovieEntity movieEntity);//是否存在这条数据
    public void insertWanEntitytoDB(WanEntity wanEntity);//将数据加入数据库
    public List<WanEntity> queryAllWanEntityfromDB();//从数据库加载全部数据
    public boolean hascontainsTheWanEntityfromDB(WanEntity wanEntity);//是否存在这条数据
    public void deleteFavoriteMoviefromDB(Long id);//删除某项
    public void deleteFavoriteZhihufromDB(Long id);//删除某项
    public void deleteFavoriteWanfromDB(Long id);//删除某项
    public List<Integer> queryAllMenuSum();//加载是数据库中的收藏数量
}
