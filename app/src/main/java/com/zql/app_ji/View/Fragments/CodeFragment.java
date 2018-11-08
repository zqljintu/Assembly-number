package com.zql.app_ji.View.Fragments;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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
import com.zql.app_ji.Prestener.PrestenerCodeFragment;
import com.zql.app_ji.Prestener.PrestenerCodeFragmentImp;
import com.zql.app_ji.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class CodeFragment extends BaseFragment implements CodeFragmentImp {
    private TabLayout code_tablayout;
    private ViewPager code_viewpager;
    private View codeView;
    private PrestenerCodeFragmentImp prestenerCodeFragmentImp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.codeView=inflater.inflate(R.layout.fragment_code,container,false);
        initPrestener();
        initView(codeView);
        prestenerCodeFragmentImp.setNightstateBackgroundtoFragment();
        return codeView;
    }

    @Override
    public void onLazyLoad() {

    }

    private void initPrestener(){//实现代理接口
        this.prestenerCodeFragmentImp=new PrestenerCodeFragment(this);
    }
    private void initView(View view){//实例化界面
        code_tablayout=(TabLayout)view.findViewById(R.id.tab_code);
        code_viewpager=(ViewPager)view.findViewById(R.id.viewpager_code);
        List<Fragment>fragments=new ArrayList<>();
        Code_articleFragment code_articleFragment=new Code_articleFragment();
        Code_projectFragment code_projectFragment=new Code_projectFragment();
        fragments.add(code_articleFragment);
        fragments.add(code_projectFragment);
        String[] titles=new String[2];
        titles[0]="最新博文";
        titles[1]="最新项目";
        MovieFragmentsAdapter codeFragmentAdapter=new MovieFragmentsAdapter(getChildFragmentManager(),fragments,titles);
        code_viewpager.setAdapter(codeFragmentAdapter);
        code_tablayout.setupWithViewPager(code_viewpager);
    }
    /**
     * 切换到夜间
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessagetype()){
            case MessageEvent.NIGHTCHAGE:
                prestenerCodeFragmentImp.setNightstateBackgroundtoFragment();
                break;
            default:
                break;
        }
    }

    @Override
    public void setTheNightstatefromUserseting(InterfaceState interfaceState) {
        ConstraintLayout constraintLayout=(ConstraintLayout)codeView.findViewById(R.id.fragment_code);
        code_tablayout.setTabTextColors(interfaceState.getTextcolor(),getResources().getColor(R.color.color_feng));
        code_tablayout.setBackgroundColor(interfaceState.getItemcolor());
        constraintLayout.setBackgroundColor(interfaceState.getBackgroundcolor());
    }

    /**
     * d设置夜间模式
     */


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
