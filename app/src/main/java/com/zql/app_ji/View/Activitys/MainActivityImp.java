package com.zql.app_ji.View.Activitys;

import android.app.Application;
import android.content.Context;

import com.zql.app_ji.Bean.InterfaceState;

import java.util.List;

public interface MainActivityImp{
    public Application getBaseapplication();
    public void setInterfacestateonActivity(InterfaceState interfaceState);
    public void showDrawerLayout();//侧滑
    public void setDrawerMenusandSum(List<Integer>listsum);//设置侧滑菜单和获取数据
    public Context getbasecontext();//获取context
}