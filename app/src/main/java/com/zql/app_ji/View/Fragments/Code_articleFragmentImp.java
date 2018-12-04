package com.zql.app_ji.View.Fragments;

import android.content.Context;

import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.WanArticle;

public interface Code_articleFragmentImp {
    public void setWanAricleOnRecyclerView(WanArticle wanArticle,int type);//将获取的数据加载到recycler view
    public void setErrorMessageOnfragment(int ErrorMessage);//将错误信息加载到fragment上
    public void setTheNightstateonFragent(InterfaceState interfaceState);
    public Context getBasecontext();
    public Context getbaseapplication();
}
