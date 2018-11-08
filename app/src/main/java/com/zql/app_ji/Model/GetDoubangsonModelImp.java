package com.zql.app_ji.Model;

public interface GetDoubangsonModelImp {
    public void getMoviefromDoubanAPI();//从豆瓣后台获取数据
    public void getMovie_top_fromDoubanAPI();//从豆瓣后台获取数据
    public void getMoreMovie_top_fromDoubanAPI(int start,int count);//从豆瓣后台获取更多电影条目
    public void getDetailDoubanMoviefromAPI(String id);//从豆瓣加载详细信息
    public void getErrorMessage();//获取错误信息
}
