package com.zql.app_ji.View.Fragments;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zql.app_ji.Broadcast.NetworkStateRecevier;


/**
 * Created by 尽途 on 2018/10/29.
 */

public abstract class BaseFragment  extends Fragment{
    private boolean isLazyLoaded;
    private boolean isPrepared;
    NetworkStateRecevier networkStateRecevier;

    @Override
    public void onResume() {
        if (networkStateRecevier==null){
            networkStateRecevier=new NetworkStateRecevier();
        }
        IntentFilter filter=new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(networkStateRecevier,filter);
        super.onResume();
    }

    @Override
    public void onPause() {
        getActivity().unregisterReceiver(networkStateRecevier);
        super.onPause();
    }

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
