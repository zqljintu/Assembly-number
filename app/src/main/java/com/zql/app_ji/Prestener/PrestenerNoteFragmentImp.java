package com.zql.app_ji.Prestener;

import android.content.Context;

import com.zql.app_ji.Bean.DetailZhihuNote;
import com.zql.app_ji.Bean.Entity.ZhihuEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.ZhihuNote;


public interface PrestenerNoteFragmentImp {
    public void getZhihuNotefromZhihuAPI();//fragment获取知乎后台上的数据
    public void getBeforeDateZhihufromZhihuAPI(String befordate);//fragment获取知乎前段时间的数据
    public void setZhihuNotetoRecyclerView(ZhihuNote zhihuNote);//Modul获取完数据后然后加载到Fragment的RecyclerView中
    public void setErrorMessagetoNoteFragment(String errMessage);//出现错误信息后加载到Fragment上
    public void getDetailZhihuNotefromZhihuAPI(int id);//从知乎获取详情数据
    public void setDetailZhihuNotetoActivity(DetailZhihuNote detailZhihuNote);//获取完后加载到Activity上
    public void setErrorMssagetoActivity(int errorMssa);//错误信息加载到Activity
    public void addFavoratetoDataBase(ZhihuNote.StoriesBean storiesBean);//加入数据库
    public void addFavoritetoDBfromZhihuDetailActivity(ZhihuEntity zhihuEntity);//从详情页加入数据库
    public void setTheNightStatefromUserseting();//设置颜色
    public InterfaceState getIntegerfacefromUserseting();//获取背景色
    public InterfaceState getIntegerfacetoActivityfromUserseting();//获取背景色
}
