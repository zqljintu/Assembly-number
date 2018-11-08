package com.zql.app_ji.View.Activitys;

import android.app.Application;
import android.content.Context;

import com.zql.app_ji.Bean.DetailZhihuNote;

public interface ZhihuNoteActivityImp {
    public void setDetailZhihuOnActivity(DetailZhihuNote detailZhihuNote);//将获取的信息加载到Activity上
    public void  setErrorMessageOnActivity(int ErrorMesage);//将错误信息加载到Activity上
    public Context getBasecontext();
    public Application getbaseapplication();
}
