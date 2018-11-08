package com.zql.app_ji.View.Activitys;

import android.app.Application;

import com.zql.app_ji.Bean.InterfaceState;

public interface MainActivityImp{
    public Application getBaseapplication();
    public void setInterfacestateonActivity(InterfaceState interfaceState);
    public void showDrawerLayout();//侧滑
}