package com.zql.app_ji.View.Fragments;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zql.app_ji.Adapter.MovieFragmentsAdapter;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.MessageEvent;
import com.zql.app_ji.Model.UserSeting;
import com.zql.app_ji.Prestener.PrestenerMovieFragment;
import com.zql.app_ji.Prestener.PrestenerMovieFragmentImp;
import com.zql.app_ji.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;



public class MovieFragment extends BaseFragment implements MovieFragmentImp {
    private TabLayout movie_tablayout;
    private ViewPager movie_viewpager;
    private PrestenerMovieFragmentImp prestenerMovieFragmentImp;
    private View movieView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.movieView=inflater.inflate(R.layout.fragment_movie,container,false);
        initPrestener();
        initView(movieView);
        prestenerMovieFragmentImp.setNightStateBackgroundtoFragment();
        return movieView;
    }

    @Override
    public void onLazyLoad() {

    }
    private void initPrestener(){
        prestenerMovieFragmentImp=new PrestenerMovieFragment(this);
    }
    private void initView(View view){//实例化界面
          movie_tablayout=(TabLayout)view.findViewById(R.id.tab_movie);
          movie_viewpager=(ViewPager)view.findViewById(R.id.viewpager_movie);
          List<Fragment>fragments=new ArrayList<>();
          Movie_intheatersFragment movie_intheatersFragment=new Movie_intheatersFragment();
          Movie_topFragment movie_topFragment=new Movie_topFragment();
          fragments.add(movie_intheatersFragment);
          fragments.add(movie_topFragment);
          String[] titles=new String[2];
          titles[0]="最近热映";
          titles[1]="电影最top";
          MovieFragmentsAdapter movieFragmentsAdapter=new MovieFragmentsAdapter(getChildFragmentManager(),fragments,titles);
          movie_viewpager.setAdapter(movieFragmentsAdapter);
          movie_tablayout.setupWithViewPager(movie_viewpager);
    }
    /**
     * 切换到夜间
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessagetype()){
            case MessageEvent.NIGHTCHAGE:
                prestenerMovieFragmentImp.setNightStateBackgroundtoFragment();
                break;
            default:
                break;
        }
    }

    /**
     * d设置夜间模式
     */
    @Override
    public void setTheNightstatefromUserseting(InterfaceState interfaceState) {
        movie_tablayout.setTabTextColors(interfaceState.getTextcolor(),getResources().getColor(R.color.color_feng));
        movie_tablayout.setBackgroundColor(interfaceState.getItemcolor());
    }

    @Override
    public Context getBasecontext() {
        return getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public Application getbaseapplication() {
        return getActivity().getApplication();
    }
}
