package com.zql.app_ji.View.Fragments;

import android.annotation.SuppressLint;
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
import com.zql.app_ji.Adapter.CodeArticleRecyclerAdapter;
import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.MessageEvent;
import com.zql.app_ji.Bean.MessageEventType;
import com.zql.app_ji.Bean.WanArticle;
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

public class Code_articleFragment extends BaseFragment implements Code_articleFragmentImp {
    private RecyclerView recyclerView_codeArticle;
    private RecyclerView.LayoutManager layoutManager;
    private CodeArticleRecyclerAdapter codeArticleRecyclerAdapter;
    private PrestenerCodeFragmentImp prestenerCodeFragmentImp;
    private SmartRefreshLayout smartRefreshLayout;
    private Handler mhander;
    private List<WanArticle.DataBean.DatasBean>datasBeans;
    private WanArticle wanArticle;
    private View articleview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.articleview=inflater.inflate(R.layout.fragment_code_article,container,false);
        initprestener();
        initView(articleview);
        prestenerCodeFragmentImp.setNightstateBackgroundtoArticle();
        smartRefreshLayout.autoLoadMore();
        return articleview;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getGroupId()==20){
            WanArticle.DataBean.DatasBean datasBean=codeArticleRecyclerAdapter.getLongClickResult();
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
    private void initView(View view){//实例化view
        datasBeans=new ArrayList<>();
        wanArticle=new WanArticle();
        recyclerView_codeArticle=(RecyclerView)view.findViewById(R.id.recyclerview_codeaticle);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        codeArticleRecyclerAdapter=new CodeArticleRecyclerAdapter(datasBeans,getContext(),prestenerCodeFragmentImp);
        recyclerView_codeArticle.setLayoutManager(layoutManager);
        recyclerView_codeArticle.setAdapter(codeArticleRecyclerAdapter);
        recyclerView_codeArticle.setOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        smartRefreshLayout=(SmartRefreshLayout)view.findViewById(R.id.smartfresh_code_artticle);
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                prestenerCodeFragmentImp.getWanArticlefromWanAPI(codeArticleRecyclerAdapter.getItemCount()/20);
                mhander=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what){
                            case MessageEventType.MOVIE_DOWN_REFECH_DATE:
                                refreshLayout.finishLoadMore();
                                datasBeans.addAll(codeArticleRecyclerAdapter.getItemCount(),wanArticle.getData().getDatas());
                                codeArticleRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                };
            }
        });
    }
    private void initprestener(){
        prestenerCodeFragmentImp=new PrestenerCodeFragment(this);
    }
    /**
     * 夜间模式
     */

    @Override
    public void setTheNightstateonFragent(InterfaceState interfaceState) {
        ConstraintLayout constraintLayout=(ConstraintLayout)articleview.findViewById(R.id.fragment_code_article);
        constraintLayout.setBackgroundColor(interfaceState.getBackgroundcolor());
    }
    /**
     * 切换到夜间
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessagetype()){
            case MessageEvent.NIGHTCHAGE:
                prestenerCodeFragmentImp.setNightstateBackgroundtoArticle();
                codeArticleRecyclerAdapter.notifyDataSetChanged();
                break;
                default:
                    break;
        }
    }
    @Override
    public void setWanAricleOnRecyclerView(WanArticle wanArticle) {
        this.wanArticle=wanArticle;
        mhander.sendEmptyMessage(MessageEventType.MOVIE_DOWN_REFECH_DATE);
    }

    @Override
    public void setErrorMessageOnfragment(int ErrorMessage) {

    }

    @Override
    public Context getBasecontext() {
        return getContext();
    }

    @Override
    public Context getbaseapplication() {
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
