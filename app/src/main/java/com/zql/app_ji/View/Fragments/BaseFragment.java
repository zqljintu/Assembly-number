package com.zql.app_ji.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by 尽途 on 2018/10/29.
 */

public abstract class BaseFragment  extends Fragment{
    private boolean isLazyLoaded;
    private boolean isPrepared;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared=true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    private void lazyLoad(){
        if (getUserVisibleHint()&&isPrepared&&!isLazyLoaded){
            onLazyLoad();
        }
    }

    @UiThread
    public abstract void onLazyLoad();
}
