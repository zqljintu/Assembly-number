package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.GankImage;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Model.GetGankgsonModel;
import com.zql.app_ji.Model.UserSeting;
import com.zql.app_ji.View.Fragments.HappyFragmentImp;

public class PrestenerHappyFragment implements PrestenerHappyFragmentImp {
    private HappyFragmentImp happyFragmentImp;
    private GetGankgsonModel getGankgsonModel;
    public PrestenerHappyFragment(HappyFragmentImp happyFragmentImp){
        this.happyFragmentImp=happyFragmentImp;
        getGankgsonModel=new GetGankgsonModel(this);
    }

    @Override
    public void getGankImagefromGankAPI(int page) {
       getGankgsonModel.getGankImagesfremGankAPI(page);
        //getGankgsonModel.getTestGank();
    }

    @Override
    public void setGankImagesOnRecyclerView(GankImage gankImage) {
        happyFragmentImp.setGankImagesOnRecyclerView(gankImage);
    }

    @Override
    public void openImagetoDialog(String url) {
        happyFragmentImp.openImageviewOnDialog(url);
    }

    @Override
    public void setErrorMessageOnFragment() {

    }

    @Override
    public void setTheStatenightfromSetring() {
        UserSeting userSeting=(UserSeting)happyFragmentImp.getbaseapplication();
        happyFragmentImp.setTheNightstateonFragment(userSeting.getInterfaceState());
    }

    @Override
    public InterfaceState getIntegerfacefromSeting() {
        UserSeting userSeting=(UserSeting)happyFragmentImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }
}
