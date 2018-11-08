package com.zql.app_ji.View.Fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.zql.app_ji.Adapter.NoteRecyclerAdapter;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.MessageEvent;
import com.zql.app_ji.Bean.MessageEventType;
import com.zql.app_ji.Bean.ZhihuNote;
import com.zql.app_ji.Listener.OnVerticalScrollListener;
import com.zql.app_ji.Prestener.PrestenerNoteFragment;
import com.zql.app_ji.Prestener.PrestenerNoteFragmentImp;
import com.zql.app_ji.R;
import com.zql.app_ji.Util.Ji_DateUtils;
import com.zql.app_ji.View.Activitys.ZhihuNoteActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class NoteFragment extends BaseFragment implements NoteFragmentImp{
    private PrestenerNoteFragmentImp prestenerNoteFragmentImp;
    private RecyclerView note_recyclerview;
    private RecyclerView.LayoutManager note_recyclermanager;
    private NoteRecyclerAdapter noteRecyclerAdapter;
    private CalendarView calendarView_note;
    private View noteView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.noteView=inflater.inflate(R.layout.fragment_note,container,false);
        initPrestener();
        initView(noteView);
        prestenerNoteFragmentImp.setTheNightStatefromUserseting();
        return noteView;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getGroupId()==0){
            ZhihuNote.StoriesBean storiesBean=noteRecyclerAdapter.getLongClickResult();
            switch (item.getItemId()){
                case 0:
                    prestenerNoteFragmentImp.addFavoratetoDataBase(storiesBean);
                    Toast.makeText(getContext(), "收藏"+storiesBean.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getContext(), "分享"+storiesBean.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }

    private void initPrestener(){//实现接口
        prestenerNoteFragmentImp=new PrestenerNoteFragment(this);
    }
    private void initView(View view){//实例化界面
        note_recyclerview=(RecyclerView)view.findViewById(R.id.recyclerView_page);
        note_recyclermanager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        note_recyclerview.setOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        initCalenderView(view);
    }
    private void initCalenderView(final View view){
        calendarView_note=(CalendarView)view.findViewById(R.id.calendarView_note);
        calendarView_note.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                String date=new String(calendar.getYear()+"-"+calendar.getMonth()+"-"+calendar.getDay());
                prestenerNoteFragmentImp.getBeforeDateZhihufromZhihuAPI(Ji_DateUtils.getTomorrowDate(Ji_DateUtils.getDateStringForUrl(date)));
            }
        });
    }

    @Override
    public void setRecyclerItemonPagefromNews(ZhihuNote zhihuNote) {
        noteRecyclerAdapter=new NoteRecyclerAdapter(zhihuNote.getStories(),getContext(),prestenerNoteFragmentImp);
        note_recyclerview.setLayoutManager(note_recyclermanager);
        note_recyclerview.setAdapter(noteRecyclerAdapter);
    }

    /**
     * 直接设置夜间
     * @param interfaceState
     */
    @Override
    public void setInterfacestateonFragment(InterfaceState interfaceState) {
        calendarView_note.setWeeColor(interfaceState.getItemcolor(),interfaceState.getTextcolor());
        RelativeLayout relativeLayout=(RelativeLayout)noteView.findViewById(R.id.note_fragement);
        relativeLayout.setBackgroundColor(interfaceState.getBackgroundcolor());
    }

    /**
     * 切换到夜间
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessagetype()){
            case MessageEvent.NIGHTCHAGE:
                prestenerNoteFragmentImp.setTheNightStatefromUserseting();
                noteRecyclerAdapter.notifyDataSetChanged();
                break;
                default:
                    break;
        }
    }


    @Override
    public void setErrorMessageonFragment(String errorMessageonFragment) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public Application getbaseapplication() {
        return getActivity().getApplication();
    }
}
