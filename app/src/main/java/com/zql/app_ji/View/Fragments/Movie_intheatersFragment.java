package com.zql.app_ji.View.Fragments;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zql.app_ji.Adapter.MovieRecyclerAdapter;
import com.zql.app_ji.Bean.DoubanMovie;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.MessageEvent;
import com.zql.app_ji.Bean.MessageEventType;
import com.zql.app_ji.Listener.OnVerticalScrollListener;
import com.zql.app_ji.Prestener.PrestenerMovieFragment;
import com.zql.app_ji.Prestener.PrestenerMovieFragmentImp;
import com.zql.app_ji.R;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class Movie_intheatersFragment extends BaseFragment implements Movie_intheatersFragmentImp {
    private PrestenerMovieFragmentImp prestenerMovieFragmentImp;
    private RecyclerView recyclerView_movie;
    private MovieRecyclerAdapter movieRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RefreshLayout refreshLayout;
    private DoubanMovie doubanMovie;
    private List<DoubanMovie.SubjectsBean> subjectsBeans;
    private Handler mhander;
    private View movieview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.movieview=inflater.inflate(R.layout.fragment_movie_intheater,container,false);
        initPrester();
        initView(movieview);
        prestenerMovieFragmentImp.setNightStateBackgroundtoIntheaters();
        refreshLayout.autoRefresh();
        return movieview;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getGroupId()==10){
            DoubanMovie.SubjectsBean subjectsBean=movieRecyclerAdapter.getLongClickResult();
            switch (item.getItemId()){
                case 0:
                    prestenerMovieFragmentImp.addFavoritetoDataBase(subjectsBean);
                    Toast.makeText(getContext(), subjectsBean.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }

    /**
     * 夜间模式
     */

    @Override
    public void setTheNightstateonFragment(InterfaceState interfaceState) {
        ConstraintLayout constraintLayout=(ConstraintLayout)movieview.findViewById(R.id.fragment_movie_intheaters);
        constraintLayout.setBackgroundColor(interfaceState.getBackgroundcolor());
    }
    /**
     * 切换到夜间
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessagetype()){
            case MessageEvent.NIGHTCHAGE:
                prestenerMovieFragmentImp.setNightStateBackgroundtoIntheaters();
                movieRecyclerAdapter.notifyDataSetChanged();
                break;
                default:
                    break;
        }
    }
    private void initView(View view){
        subjectsBeans=new  ArrayList<>();
        doubanMovie=new DoubanMovie();
        //prestenerMovieFragmentImp.getDoubanMoviefromDoubanAPI();
        recyclerView_movie=(RecyclerView)view.findViewById(R.id.recyclerView_movie_intheater);
        layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        movieRecyclerAdapter = new MovieRecyclerAdapter(subjectsBeans,getContext(),prestenerMovieFragmentImp);
        recyclerView_movie.setLayoutManager(layoutManager);
        recyclerView_movie.setAdapter(movieRecyclerAdapter);
        recyclerView_movie.setOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        refreshLayout=(RefreshLayout)view.findViewById(R.id.smartfresh_movie_intheater);
        refreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setColorSchemeColors(getResources().getColor(R.color.colorAccent),getResources().getColor(R.color.color_song)));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                prestenerMovieFragmentImp.getDoubanMoviefromDoubanAPI();
                mhander=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what){
                            case MessageEventType.MOVIE_UP_REFECH_DATE:
                                refreshLayout.finishRefresh();
                                subjectsBeans.clear();
                                subjectsBeans.addAll(doubanMovie.getSubjects());
                                movieRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                };
            }
        });

    }
    private void initPrester(){
        prestenerMovieFragmentImp=new PrestenerMovieFragment(this);
    }
    @Override
    public void setRecyclerItemonPagefromDouban(DoubanMovie mdoubanMovie) {
        this.doubanMovie=mdoubanMovie;
        mhander.sendEmptyMessage(MessageEventType.MOVIE_UP_REFECH_DATE);
    }

    @Override
    public void setErrorMessageonFragment(String errorMessageonFragment) {
        Toast.makeText(getContext(), errorMessageonFragment, Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Context getBaseContext() {
        return getContext();
    }

    @Override
    public Application getbaseapplication() {
        return getActivity().getApplication();
    }
}
