package com.zql.app_ji.View.Fragments;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.zql.app_ji.Adapter.CodeProjectRecyclerAdapter;
import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.MessageEvent;
import com.zql.app_ji.Bean.MessageEventType;
import com.zql.app_ji.Bean.WanProject;
import com.zql.app_ji.Listener.OnVerticalScrollListener;
import com.zql.app_ji.Prestener.PrestenerCodeFragment;
import com.zql.app_ji.Prestener.PrestenerCodeFragmentImp;
import com.zql.app_ji.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class Code_projectFragment extends BaseFragment implements Code_projectFragmentImp {
    private RecyclerView codeprojectrecyclerview;
    private CodeProjectRecyclerAdapter codeProjectRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private PrestenerCodeFragmentImp prestenerCodeFragmentImp;
    private SmartRefreshLayout project_smartRefreshLayout;
    private List<WanProject.DataBean.DatasBean>projects;
    private WanProject wanProject;
    private Handler mhander;
    private boolean isRefresh=false;//判断是刷新还是加载
    private View codeview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.codeview=inflater.inflate(R.layout.fragment_code_project,container,false);
        initPrestener();
        initView(codeview);
        prestenerCodeFragmentImp.setNightstateBackgroundtoProject();
        project_smartRefreshLayout.autoLoadMore();
        return codeview;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getGroupId()==21){
            WanProject.DataBean.DatasBean datasBean=codeProjectRecyclerAdapter.getLongClickRseult();
            WanEntity wanEntity=new WanEntity();
            wanEntity.setTitle(datasBean.getTitle());
            wanEntity.setWan_url(datasBean.getLink());
            switch (item.getItemId()){
                case 0:
                    prestenerCodeFragmentImp.addFavoritetoDatabase(wanEntity);
                    Toast.makeText(getContext(), datasBean.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    shareDialog(datasBean.getLink());
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }
    private void shareDialog(String url){//创建分享菜单界面
        Intent mintent=new Intent(Intent.ACTION_SEND);
        mintent.setType("text/plain");
        mintent.putExtra(Intent.EXTRA_TEXT,url);
        startActivity(mintent);
    }
    private void initView(View view){
        projects=new ArrayList<>();
        codeprojectrecyclerview=(RecyclerView)view.findViewById(R.id.recyclerview_codeproject);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        codeProjectRecyclerAdapter=new CodeProjectRecyclerAdapter(projects,getContext(),prestenerCodeFragmentImp);
        codeprojectrecyclerview.setLayoutManager(layoutManager);
        codeprojectrecyclerview.setAdapter(codeProjectRecyclerAdapter);
        codeprojectrecyclerview.setOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        project_smartRefreshLayout=(SmartRefreshLayout)view.findViewById(R.id.smartfresh_code_project);
       /* project_smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                prestenerCodeFragmentImp.getWanProjectfromWanAPI(codeProjectRecyclerAdapter.getItemCount()/20);
                isRefresh=false;
                mhander=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what){
                            case MessageEventType.MOVIE_DOWN_REFECH_DATE:
                                refreshLayout.finishLoadMore();
                                projects.addAll(codeProjectRecyclerAdapter.getItemCount(),wanProject.getData().getDatas());
                                codeProjectRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                };
            }

            @SuppressLint("HandlerLeak")
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                 prestenerCodeFragmentImp.getWanProjectfromWanAPI(0);
                isRefresh=true;
                mhander=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what){
                            case MessageEventType.MOVIE_DOWN_REFECH_DATE:
                                refreshLayout.finishRefresh();
                                projects.clear();
                                projects=wanProject.getData().getDatas();
                                codeProjectRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                };
            }
        });*/
        project_smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                prestenerCodeFragmentImp.getWanProjectfromWanAPI(codeProjectRecyclerAdapter.getItemCount()/20);
                isRefresh=false;
                mhander=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what){
                            case MessageEventType.MOVIE_DOWN_REFECH_DATE:
                                refreshLayout.finishLoadMore();
                                projects.addAll(codeProjectRecyclerAdapter.getItemCount(),wanProject.getData().getDatas());
                                codeProjectRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                };
            }
        });
    }
    private void initPrestener(){
        prestenerCodeFragmentImp=new PrestenerCodeFragment(this);
    }

    /**
     * 夜间模式
     */
    @Override
    public void setTheNightstateonFragment(InterfaceState interfaceState) {
        ConstraintLayout constraintLayout=(ConstraintLayout)codeview.findViewById(R.id.fragment_code_project);
        constraintLayout.setBackgroundColor(interfaceState.getBackgroundcolor());
    }
    /**
     * 切换到夜间
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessagetype()){
            case MessageEvent.NIGHTCHAGE:
                prestenerCodeFragmentImp.setNightstateBackgroundtoProject();
                codeProjectRecyclerAdapter.notifyDataSetChanged();
                break;
        }
    }


    @Override
    public void setWanProjectsOnRecyclerview(WanProject wanProject) {
        this.wanProject=wanProject;
        mhander.sendEmptyMessage(MessageEventType.MOVIE_DOWN_REFECH_DATE);
    }

    @Override
    public void setErrorMessageOnFragment(int ErrorMessage) {

    }

    @Override
    public Context getBasecontext() {
        return getContext();
    }

    @Override
    public Application getbaseapplication() {
        return getActivity().getApplication();
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
}
