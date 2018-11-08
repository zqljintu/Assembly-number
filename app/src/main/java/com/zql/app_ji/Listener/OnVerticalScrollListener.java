package com.zql.app_ji.Listener;

import android.support.v7.widget.RecyclerView;

import com.zql.app_ji.Bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public abstract class OnVerticalScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!recyclerView.canScrollVertically(-1)){
            onScrolledToTop();
        }else if (!recyclerView.canScrollVertically(1)){
            onScrolledToBottom();
        }else if (dy<0){
            onScrolledUp();
        }else if (dy>0){
            onScrolledDown();
        }
    }
    public void onScrolledUp() {
        MessageEvent messageEvent=new MessageEvent(MessageEvent.RECYCLERVIEW_UP);
        EventBus.getDefault().post(messageEvent);
    }

    public void onScrolledDown() {
        MessageEvent messageEvent=new MessageEvent(MessageEvent.RECYCLERVIEW_DOWN);
        EventBus.getDefault().post(messageEvent);
    }

    public void onScrolledToTop() {}

    public void onScrolledToBottom() {}
}
