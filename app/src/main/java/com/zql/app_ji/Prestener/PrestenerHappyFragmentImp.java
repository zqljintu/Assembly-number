package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.GankImage;
import com.zql.app_ji.Bean.InterfaceState;

public interface PrestenerHappyFragmentImp {
    public void getGankImagefromGankAPI(int page,int type);
    public void setGankImagesOnRecyclerView(GankImage gankImage);//从gank平台获取图片传递到RecyclerView
    public void setErrorMessageOnFragment();//将错误信息显示到Fragment上
    public void openImagetoDialog(String url);//打开图片到dialog
    public void setTheStatenightfromSetring();//设置夜间模式
    public InterfaceState getIntegerfacefromSeting();//获取
}
