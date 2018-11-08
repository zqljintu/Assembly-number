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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.zql.app_ji.Adapter.ImageRecyclerAdapter;
import com.zql.app_ji.Bean.GankImage;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.MessageEvent;
import com.zql.app_ji.Listener.OnVerticalScrollListener;
import com.zql.app_ji.Prestener.PrestenerHappyFragment;
import com.zql.app_ji.Prestener.PrestenerHappyFragmentImp;
import com.zql.app_ji.R;
import com.zql.app_ji.Bean.MessageEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class HappyFragment extends BaseFragment implements HappyFragmentImp{
    private RecyclerView meizirecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageRecyclerAdapter imageRecyclerAdapter;
    private PrestenerHappyFragmentImp prestenerHappyFragmentImp;
    private SmartRefreshLayout meizi_smartRefreshLayout;
    private GankImage gankImage;
    private List<GankImage.ResultsBean>gankImages;
    private Handler mhander;
    private TextView textView_meizi;
    private  View happyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.happyView=inflater.inflate(R.layout.fragment_happy,container,false);
        initPrestener();
        initView(happyView);
        prestenerHappyFragmentImp.setTheStatenightfromSetring();
        meizi_smartRefreshLayout.autoLoadMore();
        return happyView;
    }

    @Override
    public void onLazyLoad() {

    }


    private void initPrestener(){//实现代理接口
        prestenerHappyFragmentImp=new PrestenerHappyFragment(this);
    }
    private void initView(View view){//实例化界面
        gankImages=new ArrayList<>();
        textView_meizi=(TextView)view.findViewById(R.id.text_meizi);
        textView_meizi.setVisibility(View.INVISIBLE);
        meizirecyclerView=(RecyclerView)view.findViewById(R.id.recyclerView_meizi);
        layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        textView_meizi=(TextView)view.findViewById(R.id.text_meizi);
        imageRecyclerAdapter=new ImageRecyclerAdapter(gankImages,getContext(),prestenerHappyFragmentImp);
        meizirecyclerView.setLayoutManager(layoutManager);
        meizirecyclerView.setAdapter(imageRecyclerAdapter);
        meizirecyclerView.setOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
                textView_meizi.setVisibility(View.VISIBLE);
            }

            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                textView_meizi.setVisibility(View.INVISIBLE);
            }
        });
        meizi_smartRefreshLayout=(SmartRefreshLayout)view.findViewById(R.id.smartfresh_meizi);
        meizi_smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                prestenerHappyFragmentImp.getGankImagefromGankAPI(imageRecyclerAdapter.getItemCount()/20+1);//获取更多照片，+1表示页数。
                mhander=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what){
                            case MessageEventType.MOVIE_DOWN_REFECH_DATE:
                                refreshLayout.finishLoadMore();
                                gankImages.addAll(imageRecyclerAdapter.getItemCount(), gankImage.getResults());
                                imageRecyclerAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                };
            }
        });
    }
    /**
     * 直接设置夜间
     * @param interfaceState
     */
    @Override
    public void setTheNightstateonFragment(InterfaceState interfaceState) {
        ConstraintLayout constraintLayout=(ConstraintLayout)happyView.findViewById(R.id.fragment_happy);
        constraintLayout.setBackgroundColor(interfaceState.getBackgroundcolor());
        textView_meizi.setTextColor(interfaceState.getTextcolor());
        textView_meizi.setBackgroundColor(interfaceState.getItemcolor());
    }
    /**
     * 切换到夜间
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessagetype()){
            case MessageEvent.NIGHTCHAGE:
                prestenerHappyFragmentImp.setTheStatenightfromSetring();
                imageRecyclerAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void openImageviewOnDialog(String url) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View centerview=layoutInflater.inflate(R.layout.dialog_meizi,null);
        final ImageView dialogimageView=(ImageView)centerview.findViewById(R.id.dialog_meizi_image);
        final AlertDialog alertDialog=builder.setView(centerview).create();
        Glide.with(getContext()).load(url).into(dialogimageView);
        alertDialog.show();
    }

    @Override
    public void setGankImagesOnRecyclerView(GankImage mgankImage) {
        this.gankImage=mgankImage;
        mhander.sendEmptyMessage(MessageEventType.MOVIE_DOWN_REFECH_DATE);
    }

    @Override
    public void setErrorMessage(int ErrorMessage) {

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
    public Context getbasecontext() {
        return getContext();
    }

    @Override
    public Application getbaseapplication() {
        return getActivity().getApplication();
    }
}
