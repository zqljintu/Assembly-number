package com.zql.app_ji.View.Activitys;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zql.app_ji.Adapter.FavoriteAdapter.FavoriteMovieRecyclerAdapter;
import com.zql.app_ji.Adapter.FavoriteAdapter.FavoriteRecyclerAdapter;
import com.zql.app_ji.Adapter.FavoriteAdapter.FavoriteWanRecyclerAdapter;
import com.zql.app_ji.Bean.Entity.MovieEntity;
import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.Entity.ZhihuEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Prestener.PrestenerFavoriteActivity;
import com.zql.app_ji.Prestener.PrestenerFavoriteActivityImp;
import com.zql.app_ji.R;

import java.util.List;

public class FavoriteActivity extends SwipeActivity implements FavoriteActivityImp {
    private Toolbar favorite_toolbar;
    private RecyclerView favorite_recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PrestenerFavoriteActivityImp prestenerFavoriteActivityImp;
    private FavoriteWanRecyclerAdapter favoriteWanRecyclerAdapter;
    private FavoriteMovieRecyclerAdapter favoriteMovieRecyclerAdapter;
    private FavoriteRecyclerAdapter favoriteRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        setSwipeAnyWhere(true);
        Intent mintent=getIntent();
        int type=mintent.getIntExtra("type",0);
        initPrestener();
        initView(type);
        prestenerFavoriteActivityImp.getFavoriteDatafromData(type);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getGroupId()){
            case 101:
                ZhihuEntity zhihuEntity=favoriteRecyclerAdapter.getLongClickResult();
                switch (item.getItemId()){
                    case 0:
                        prestenerFavoriteActivityImp.delteFavoriteZhihufromData(zhihuEntity);
                        Toast.makeText(this, "删除"+zhihuEntity.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
                return true;
            case 201:
                MovieEntity movieEntity=favoriteMovieRecyclerAdapter.getLongClickResult();
                switch (item.getItemId()){
                    case 0:
                        prestenerFavoriteActivityImp.delteFavoriteMoviefromData(movieEntity);
                        Toast.makeText(this, "删除"+movieEntity.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        break;
                        default:
                            break;
                }
                return true;
            case 301:
                WanEntity wanEntity=favoriteWanRecyclerAdapter.getLongClickResult();
                switch (item.getItemId()){
                    case 0:
                        prestenerFavoriteActivityImp.delteFavoriteWanfromData(wanEntity);
                        Toast.makeText(this, "删除"+wanEntity.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
                return true;
                default:
                    break;
        }
        return false;
    }

    private void initPrestener(){
        prestenerFavoriteActivityImp=new PrestenerFavoriteActivity(this);
    }
    private void initView(int type){
        InterfaceState interfaceState=prestenerFavoriteActivityImp.getTheNightstatefromUserseting();
        favorite_toolbar=(Toolbar)findViewById(R.id.favorite_toolBar);
        favorite_toolbar.setBackgroundColor(interfaceState.getPagecolor());
        favorite_recyclerView=(RecyclerView)findViewById(R.id.favorite_recycleView);
        favorite_recyclerView.setBackgroundColor(interfaceState.getBackgroundcolor());
        setSupportActionBar(favorite_toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        favorite_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switch (type){
            case 0:
                getSupportActionBar().setTitle("日报");
                break;
            case 1:
                getSupportActionBar().setTitle("电影");
                break;
            case 2:
                getSupportActionBar().setTitle("开发");
                break;
            case 3:
                getSupportActionBar().setTitle("娱乐");
                break;
                default:
                    break;
        }
    }

    @Override
    public Context getBasecontext() {
        return this;
    }

    @Override
    public Application getbaseapplication() {
        return getApplication();
    }

    @Override
    public void setFavoriteDataonRecyclerView(List<ZhihuEntity> favoriteEntyList) {
        favoriteRecyclerAdapter=new FavoriteRecyclerAdapter(this,favoriteEntyList,prestenerFavoriteActivityImp);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        favorite_recyclerView.setAdapter(favoriteRecyclerAdapter);
        favorite_recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setFavoriteMovieDataonRecyclerView(List<MovieEntity> movieEntities) {
        favoriteMovieRecyclerAdapter=new FavoriteMovieRecyclerAdapter(this,movieEntities,prestenerFavoriteActivityImp);
        layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        favorite_recyclerView.setAdapter(favoriteMovieRecyclerAdapter);
        favorite_recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setFavoriteWanDataonRecyclerView(List<WanEntity> wanEntities) {
        favoriteWanRecyclerAdapter=new FavoriteWanRecyclerAdapter(this,wanEntities,prestenerFavoriteActivityImp);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        favorite_recyclerView.setAdapter(favoriteWanRecyclerAdapter);
        favorite_recyclerView.setLayoutManager(layoutManager);
    }
}
