package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.WanArticle;
import com.zql.app_ji.Bean.WanProject;

public interface PrestenerCodeFragmentImp {
    public void getWanArticlefromWanAPI(int page);//从玩Android后台获取最新博文数据
    public void setWanArticleonArticleRecyclerView(WanArticle wanArticle);//将最新博文数据加载到recyclerview上
    public void setErrorMessageOnArticleFragment(int ErrorMessage);//将错误信息加载到fragment上
    public void getWanProjectfromWanAPI(int page);//从玩Android后台获取最新项目数据
    public void setWanProjectonArticleRecyclerView(WanProject wanProject);//将最新项目数据加载到recyclerview上
    public void setErrorMessageOnProjectFragment(int ErrorMessage);//将错误信息加载到fragment上
    public void addFavoritetoDatabase(WanEntity wanEntity);//加载到数据库
    public void setNightstateBackgroundtoArticle();//设置夜间模式
    public void setNightstateBackgroundtoProject();//设置夜间模式
    public void setNightstateBackgroundtoFragment();//设置夜间模式
    public InterfaceState getArticleInterfacestatefromUserseting();//获取背景
    public InterfaceState getProjectInterfacestacefromUserseting();//获取背景
    public InterfaceState getActivityInterfacestatefromUserSting();//获取背景

}
