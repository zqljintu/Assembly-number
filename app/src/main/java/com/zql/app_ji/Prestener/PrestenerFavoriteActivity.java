package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.Entity.MovieEntity;
import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.Entity.ZhihuEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Model.DatabaseJi;
import com.zql.app_ji.Model.DatabasejiImp;
import com.zql.app_ji.Model.UserSeting;
import com.zql.app_ji.View.Activitys.FavoriteActivityImp;

import java.util.List;

public class PrestenerFavoriteActivity implements PrestenerFavoriteActivityImp {
    private FavoriteActivityImp favoriteActivityImp;
    private DatabasejiImp databasejiImp;

    public PrestenerFavoriteActivity(FavoriteActivityImp favoriteActivityImp){
        this.favoriteActivityImp=favoriteActivityImp;
        databasejiImp=new DatabaseJi(favoriteActivityImp.getBasecontext());
    }

    @Override
    public void getFavoriteDatafromData(int JI_TYPE) {
       switch (JI_TYPE){
           case 0:
               setFavoriteZhihuDatatoActivity(databasejiImp.queryAllZhihuEntityfromDB());
               break;
           case 1:
               setFavoriteMovieDataontoActivity(databasejiImp.queryAllMovieEntityfromDB());
               break;
           case 2:
               setFavoriteWanDatatoActivity(databasejiImp.queryAllWanEntityfromDB());
               break;
           case 3:
               break;
               default:
                   break;
       }
    }

    /**
     * 知乎关系接口类实现
     * @param favoriteEntyList
     */

    @Override
    public void setFavoriteZhihuDatatoActivity(List<ZhihuEntity> favoriteEntyList) {
        favoriteActivityImp.setFavoriteDataonRecyclerView(favoriteEntyList);
    }

    @Override
    public void delteFavoriteZhihufromData(ZhihuEntity zhihuEntity) {
        databasejiImp.deleteFavoriteZhihufromDB(zhihuEntity.getId());
        getFavoriteDatafromData(0);
    }
    /**
     * 电影关系接口类实现
     * @param favoriteMovieList
     */
    @Override
    public void setFavoriteMovieDataontoActivity(List<MovieEntity> favoriteMovieList) {
        favoriteActivityImp.setFavoriteMovieDataonRecyclerView(favoriteMovieList);
    }

    @Override
    public void delteFavoriteMoviefromData(MovieEntity movieEntity) {
        databasejiImp.deleteFavoriteMoviefromDB(movieEntity.getId());
        getFavoriteDatafromData(1);
    }

    /**
     * 开发关系接口类实现
     * @param favoriteWanList
     */
    @Override
    public void setFavoriteWanDatatoActivity(List<WanEntity> favoriteWanList) {
        favoriteActivityImp.setFavoriteWanDataonRecyclerView(favoriteWanList);
    }

    @Override
    public void delteFavoriteWanfromData(WanEntity wanEntity) {
        databasejiImp.deleteFavoriteWanfromDB(wanEntity.getId());
        getFavoriteDatafromData(2);
    }

    @Override
    public InterfaceState getTheNightstatefromUserseting() {
        UserSeting userSeting=(UserSeting)favoriteActivityImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }
}
