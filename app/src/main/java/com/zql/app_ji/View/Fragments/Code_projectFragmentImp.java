package com.zql.app_ji.View.Fragments;

import android.app.Application;
import android.content.Context;

import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.WanProject;

public interface Code_projectFragmentImp {
    public void setWanProjectsOnRecyclerview(WanProject wanProject);//从玩Android后台获取最新项目列表加载到recycler view
    public void setErrorMessageOnFragment(int ErrorMessage);//获取错误信息加载到Fragment
    public void setTheNightstateonFragment(InterfaceState interfaceState);
    public Context getBasecontext();
    public Application getbaseapplication();
}
