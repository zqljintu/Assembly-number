package com.zql.app_ji.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearSmoothScroller;

import com.zql.app_ji.Bean.Entity.MovieEntity;
import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.Entity.ZhihuEntity;
import com.zql.app_ji.Model.greendao.db.DaoMaster;
import com.zql.app_ji.Model.greendao.db.DaoSession;
import com.zql.app_ji.Model.greendao.db.MovieEntityDao;
import com.zql.app_ji.Model.greendao.db.WanEntityDao;
import com.zql.app_ji.Model.greendao.db.ZhihuEntityDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class DatabaseJi implements DatabasejiImp {
    private Context context;
    private ZhihuEntityDao zhihuEntityDao;
    private MovieEntityDao movieEntityDao;
    private WanEntityDao wanEntityDao;
    private SQLiteDatabase database;
    private DaoSession daoSession;

    public DatabaseJi(Context mcontext){
        this.context=mcontext;
        initGreendao();
    }
    private void initGreendao(){
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(context,"ji_db",null);
        database=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(database);
        daoSession=daoMaster.newSession();
        zhihuEntityDao=daoSession.getZhihuEntityDao();
        movieEntityDao=daoSession.getMovieEntityDao();
        wanEntityDao=daoSession.getWanEntityDao();
    }

    /**
     * 关于知乎日报的接口是新
     * @param zhihuEntity
     */
    @Override
    public void insertZhihuEntitytoDB(ZhihuEntity zhihuEntity) {
        if (!hascontainsTheZhihuEntityfromDB(zhihuEntity)){
            zhihuEntityDao.insert(zhihuEntity);
        }
    }

    @Override
    public List<ZhihuEntity> queryAllZhihuEntityfromDB() {
        return zhihuEntityDao.loadAll();
    }

    @Override
    public boolean hascontainsTheZhihuEntityfromDB(ZhihuEntity zhihuEntity) {
        QueryBuilder<ZhihuEntity>queryBuilder=zhihuEntityDao.queryBuilder();
        queryBuilder.where(ZhihuEntityDao.Properties.Note_id.like(zhihuEntity.getNote_id()))
                .orderAsc(ZhihuEntityDao.Properties.Note_id);
        if (queryBuilder.list().size()==0){
            return false;
        }
        return true;
    }

    @Override
    public void deleteFavoriteZhihufromDB(Long id) {
        zhihuEntityDao.deleteByKey(id);
    }

    /**
     * 关于电影的接口实现
     * @param movieEntity
     */

    @Override
    public void insertMovieEntitytoDB(MovieEntity movieEntity) {
        if (!hascontainsTheMovieEntityfromDB(movieEntity)){
            movieEntityDao.insert(movieEntity);
        }
    }

    @Override
    public List<MovieEntity> queryAllMovieEntityfromDB() {
        return movieEntityDao.loadAll();
    }

    @Override
    public boolean hascontainsTheMovieEntityfromDB(MovieEntity movieEntity) {
        QueryBuilder<MovieEntity>queryBuilder=movieEntityDao.queryBuilder();
        queryBuilder.where(MovieEntityDao.Properties.Movie_id.like(movieEntity.getMovie_id()))
                .orderAsc(MovieEntityDao.Properties.Movie_id);
        if (queryBuilder.list().size()==0){
            return false;
        }
        return true;
    }

    @Override
    public void deleteFavoriteMoviefromDB(Long id) {
        movieEntityDao.deleteByKey(id);
    }

    /**
     * 关于开发的接口实现
     * @param wanEntity
     */

    @Override
    public void insertWanEntitytoDB(WanEntity wanEntity) {
        if (!hascontainsTheWanEntityfromDB(wanEntity)){
            wanEntityDao.insert(wanEntity);
        }
    }

    @Override
    public List<WanEntity> queryAllWanEntityfromDB() {
        return wanEntityDao.loadAll();
    }

    @Override
    public boolean hascontainsTheWanEntityfromDB(WanEntity wanEntity) {
        QueryBuilder<WanEntity>queryBuilder=wanEntityDao.queryBuilder();
        queryBuilder.where(WanEntityDao.Properties.Wan_url.like(wanEntity.getWan_url()))
                .orderAsc(WanEntityDao.Properties.Title);
        if (queryBuilder.list().size()==0){
            return false;
        }
        return true;
    }

    @Override
    public void deleteFavoriteWanfromDB(Long id) {
        wanEntityDao.deleteByKey(id);
    }
    /**
     * 获取收藏的数量
     */
    @Override
    public List<Integer> queryAllMenuSum() {
        List<Integer>list=new ArrayList<>();
        list.add(zhihuEntityDao.loadAll().size());
        list.add(movieEntityDao.loadAll().size());
        list.add(wanEntityDao.loadAll().size());
        list.add(0);
        return list;
    }
}
