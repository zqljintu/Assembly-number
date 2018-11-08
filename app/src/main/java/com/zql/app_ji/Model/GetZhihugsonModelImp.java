package com.zql.app_ji.Model;

import com.zql.app_ji.Bean.ZhihuNote;

import java.util.List;

public interface GetZhihugsonModelImp {
    public void getZhihuNotefromZhihuAPI();//从知乎API获取数据
    public void getBeforeDateZhihuNotefromZhihuAPI(String beforedate);//获取以前的信息
    public void getDetailZhihuNotefromZhihuAPI(int id);//获取详细信息
}
