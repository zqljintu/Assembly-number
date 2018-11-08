package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.Entity.MovieEntity;
import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.Entity.ZhihuEntity;
import com.zql.app_ji.Bean.InterfaceState;

import java.util.List;

public interface PrestenerFavoriteActivityImp {
    public void getFavoriteDatafromData(int JI_TYPE);//获取数据
    public void setFavoriteZhihuDatatoActivity(List<ZhihuEntity> favoriteEntyList);//加载到数据到Activity
    public void setFavoriteMovieDataontoActivity(List<MovieEntity> favoriteMovieList);
    public void setFavoriteWanDatatoActivity(List<WanEntity> favoriteWanList);
    public void delteFavoriteMoviefromData(MovieEntity movieEntity);//删除某一项
    public void delteFavoriteZhihufromData(ZhihuEntity zhihuEntity);//删除某一项
    public void delteFavoriteWanfromData(WanEntity wanEntity);//删除某一项
    public InterfaceState getTheNightstatefromUserseting();
}
