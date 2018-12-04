package com.zql.app_ji.View.Fragments;


import android.app.Application;
import android.content.Context;

import com.zql.app_ji.Bean.GankImage;
import com.zql.app_ji.Bean.InterfaceState;

public interface HappyFragmentImp {
    public void setGankImagesOnRecyclerView(GankImage gankImage,int type);//将获取的图片列表加载到recyclerView上
    public void openImageviewOnDialog(String url);//打开图片在dialog
    public void setErrorMessage(int ErrorMessage);//将错误信息加载到fragment上
    public void setTheNightstateonFragment(InterfaceState interfaceState);//设置夜间模式
    public Context getbasecontext();
    public Application getbaseapplication();
}
