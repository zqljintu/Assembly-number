package com.zql.app_ji.View.Fragments;

import android.app.Application;
import android.content.Context;

import com.zql.app_ji.Bean.DoubanMovie;
import com.zql.app_ji.Bean.InterfaceState;

public interface Movie_topFragmentImp {
    public void setRecyclerItemonPagefromDouban(DoubanMovie doubanMovie,int type);//从网上拉取信息
    public void setErrorMessageonFragment(String errorMessageonFragment);//显示错误信息
    public Context getBaseContext();
    public void setTheNightstateonFragment(InterfaceState interfaceState);//设置背景色
    public Application getbaseapplication();
}
