package com.zql.app_ji.View.Fragments;

import android.app.Application;
import android.content.Context;

import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.ZhihuNote;

import java.util.ArrayList;
import java.util.HashMap;

public interface NoteFragmentImp {
    public void setRecyclerItemonPagefromNews(ZhihuNote zhihuNote);//从网上拉取信息
    public void setErrorMessageonFragment(String errorMessageonFragment);//显示错误信息
    public void setInterfacestateonFragment(InterfaceState interfaceState);//切换到夜间
    public Context getContext();
    public Application getbaseapplication();
}
