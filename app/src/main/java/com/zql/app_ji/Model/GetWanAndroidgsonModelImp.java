package com.zql.app_ji.Model;

public interface GetWanAndroidgsonModelImp {
    public void getArticlesfromWanAPI(int page);//从玩Android获取最新博文
    public void getProjectsfromWanAPI(int page);//从玩Android获取最新项目
    public void getErrorMessagefromWanAPI();//获取错误信息
}
