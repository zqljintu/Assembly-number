package com.zql.app_ji.View.Fragments;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zql.app_ji.Adapter.ImageRecyclerAdapter;
import com.zql.app_ji.Bean.GankImage;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.MessageEvent;
import com.zql.app_ji.Listener.OnVerticalScrollListener;
import com.zql.app_ji.Prestener.PrestenerHappyFragment;
import com.zql.app_ji.Prestener.PrestenerHappyFragmentImp;
import com.zql.app_ji.R;
import com.zql.app_ji.Bean.MessageEventType;
import com.zql.app_ji.Util.SaveImageUtil;
import com.zql.app_ji.View.Viewabout.StaggeredDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class HappyFragment extends BaseFragment implements HappyFragmentImp{
    private RecyclerView meizirecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageRecyclerAdapter imageRecyclerAdapter;
    private PrestenerHappyFragmentImp prestenerHappyFragmentImp;
    private SmartRefreshLayout meizi_smartRefreshLayout;
    private GankImage gankImage=new GankImage();
    private List<GankImage.ResultsBean>gankImages=new ArrayList<>();
    private Handler mhander,dhander;
    private TextView textView_meizi;
    private View happyView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.happyView=inflater.inflate(R.layout.fragment_happy,container,false);
        initPrestener();
        initSubscriber();
        initView(happyView);
        prestenerHappyFragmentImp.setTheStatenightfromSetring();
        meizi_smartRefreshLayout.autoRefresh();
        return happyView;
    }

    @Override
    public void onLazyLoad() {

    }
    private void initSubscriber(){
    }
    private void initPrestener(){//实现代理接口
        prestenerHappyFragmentImp=new PrestenerHappyFragment(this);
    }
    private void initView(View view){//实例化界面
        textView_meizi=(TextView)view.findViewById(R.id.text_meizi);
        textView_meizi.setVisibility(View.INVISIBLE);
        meizirecyclerView=(RecyclerView)view.findViewById(R.id.recyclerView_meizi);
        layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        ((StaggeredGridLayoutManager) layoutManager).setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        textView_meizi=(TextView)view.findViewById(R.id.text_meizi);
        imageRecyclerAdapter=new ImageRecyclerAdapter(gankImages,getContext(),prestenerHappyFragmentImp);
        meizirecyclerView.addItemDecoration(new StaggeredDividerItemDecoration(getContext(),0));
        meizirecyclerView.setLayoutManager(layoutManager);
        meizirecyclerView.setAdapter(imageRecyclerAdapter);
        meizirecyclerView.setOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                ((StaggeredGridLayoutManager) layoutManager).invalidateSpanAssignments();
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
        meizi_smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setColorSchemeColors(getResources().getColor(R.color.colorAccent),getResources().getColor(R.color.color_song)));
        meizi_smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                prestenerHappyFragmentImp.getGankImagefromGankAPI(0,0);//获取更多照片，+1表示页数。
                dhander=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what){
                            case MessageEventType.MOVIE_UP_REFECH_DATE:
                                refreshLayout.finishRefresh();
                                gankImages.clear();
                                gankImages.addAll(gankImage.getResults());
                                imageRecyclerAdapter.notifyDataSetChanged();
                                break;
                        }
                    }
                };
            }
        });
        meizi_smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                prestenerHappyFragmentImp.getGankImagefromGankAPI(imageRecyclerAdapter.getItemCount()/20+1,1);//获取更多照片，+1表示页数。
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
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getGroupId()==4){
            GankImage.ResultsBean resultsBean=imageRecyclerAdapter.getLongClickRseult();
            switch (item.getItemId()){
                case 0:
                    saveImagefromurl(resultsBean.getUrl());
                    break;
                case 1:
                    shareDialog(resultsBean.getUrl(),resultsBean.getDesc());
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }
    private void saveImagefromurl(final String url) {
        Glide.with(getContext())
                .load(url)
                .asBitmap()
                .toBytes()
                .into(new SimpleTarget<byte[]>() {
                    @Override
                    public void onResourceReady(byte[] resource, GlideAnimation<? super byte[]> glideAnimation) {
                        File pictureFolder=Environment.getExternalStorageDirectory();
                        File appdir=new File(pictureFolder,"/jiimage");
                        if (!appdir.exists()){
                            appdir.mkdir();
                        }
                        String filename=System.currentTimeMillis()+".jpg";
                        File destfile=new File(appdir,filename);
                        if (SaveImageUtil.copyfile(resource,destfile)){
                            Toast.makeText(getContext(), "图片保存成功", Toast.LENGTH_SHORT).show();
                            getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                    Uri.fromFile(new File(appdir.getAbsoluteFile(),filename))));

                        }else {
                            Toast.makeText(getContext(), "图片保存失败+n内存", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        Toast.makeText(getContext(), "图片保存失败+glide", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void shareDialog(String url,String title) {//创建分享菜单界面
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"分享");
        startActivity(intent);
        /*Glide.with(getContext())
                .load(url)
                .asBitmap()
                .toBytes()
                .into(new SimpleTarget<byte[]>() {
                    @Override
                    public void onResourceReady(byte[] resource, GlideAnimation<? super byte[]> glideAnimation) {
                        File pictureFolder=Environment.getExternalStorageDirectory();
                        File appdir=new File(pictureFolder,"/jishare");
                        if (!appdir.exists()){
                            appdir.mkdir();
                        }
                        String filename=System.currentTimeMillis()+".jpg";
                        File destfile=new File(appdir,filename);
                        if (SaveImageUtil.copyfile(resource,destfile)){
                            Toast.makeText(getContext(), "图片保存成功", Toast.LENGTH_SHORT).show();
                            getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                    Uri.fromFile(new File(appdir.getAbsoluteFile(),filename))));
                            Intent mintent=new Intent(Intent.ACTION_SEND);
                            mintent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(new File(appdir.getAbsoluteFile(),filename)));
                            mintent.setType("image/*");
                            startActivity(mintent);
                        }else {
                            Toast.makeText(getContext(), "图片分享失败+内存", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        Toast.makeText(getContext(), "图片分享失败+glide", Toast.LENGTH_SHORT).show();
                    }
                });*/
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
    public void setGankImagesOnRecyclerView(GankImage mgankImage,int type) {
        this.gankImage=mgankImage;
        switch (type){
            case 0:
                dhander.sendEmptyMessage(MessageEventType.MOVIE_UP_REFECH_DATE);
                break;
            case 1:
                mhander.sendEmptyMessage(MessageEventType.MOVIE_DOWN_REFECH_DATE);
                break;
                default:
                    break;
        }
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
