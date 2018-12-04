package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.DetailDoubanMovie;
import com.zql.app_ji.Bean.DoubanMovie;
import com.zql.app_ji.Bean.InterfaceState;

public interface PrestenerMovieFragmentImp {
    public void getDoubanMoviefromDoubanAPI();//fragment获取豆瓣上的数据
    public void setDoubanMovietoRecyclerView(DoubanMovie doubanMovie);//Modul获取完数据后加载到Fragment;
    public void setErrorMessageOnFragment();//显示错误信息
    public void getDoubantopMoviefromDoubanAPI();//fragment获取豆瓣上的数据(Top界面)
    public void setDoubantopMovietoRecyclerView(DoubanMovie doubanMovie);//Modul获取完数据后加载到Fragment;（Top界面）
    public void setErrorMessageOntopFragment();//显示错误信息（top界面）
    public void getMoreDoubantopMoviefromDoubanAPI(int start,int count,int type);//获取更多的top电影条目
    public void getDetailDoubanMoviefromDoubanAPI(String id);//从豆瓣API获取数据
    public void setDetailDoubanMovietoActivity(DetailDoubanMovie detailDoubanMovie);//获取电影详情到activity
    public void addFavoritetoDataBase(DoubanMovie.SubjectsBean subjectsBean);//收藏到数据库
    public void setErrorMessagetoActivity();//加载错误信息到Acytvity
    public void setNightStateBackgroundtoTop();//设置夜间模式
    public void setNightStateBackgroundtoIntheaters();//设置夜间模式
    public InterfaceState  getActivityInterfacefromUserting();
    public InterfaceState  getTopInterfacestatefromUserseting();//获取背景
    public InterfaceState  getIntheatersInterfacestatefromUserseting();//获取背景
    public void setNightStateBackgroundtoFragment();//设置夜间模式
}
