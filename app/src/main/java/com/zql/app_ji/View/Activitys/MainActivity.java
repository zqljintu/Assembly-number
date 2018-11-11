package com.zql.app_ji.View.Activitys;

import android.app.Application;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kyleduo.switchbutton.SwitchButton;
import com.zql.app_ji.Adapter.FragmentsAdapter;
import com.zql.app_ji.Adapter.MenuRecyclerViewAdapter;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.MessageEvent;
import com.zql.app_ji.Model.UserSeting;
import com.zql.app_ji.Prestener.PrestenerMainActivity;
import com.zql.app_ji.Prestener.PrestenerMainActivityImp;
import com.zql.app_ji.R;
import com.zql.app_ji.Util.CleanMessageUtil;
import com.zql.app_ji.Util.GlideCacheUtil;
import com.zql.app_ji.Util.LoderCleanUtil;
import com.zql.app_ji.View.Fragments.CodeFragment;
import com.zql.app_ji.View.Fragments.HappyFragment;
import com.zql.app_ji.View.Fragments.MovieFragment;
import com.zql.app_ji.View.Fragments.NoteFragment;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;


public class MainActivity extends AppCompatActivity implements MainActivityImp{
    private PageNavigationView pageNavigationView;
    private NavigationController navigationController;
    private CardView cardView_bottom;
    private DrawerLayout drawerLayout_main;
    private RecyclerView recyclerView_menu;
    private MenuRecyclerViewAdapter menuRecyclerViewAdapter;
    private SwitchButton switchButton_night;
    private UserSeting userSeting;


    private PrestenerMainActivity prestenerMainActivityImp;//MainActivity的代理接口
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userSeting=(UserSeting)getApplication();
        setContentView(R.layout.activity_main);
        initPrestener();
        initview();
        prestenerMainActivityImp.setTheNightStatefromUserseting();
        EventBus.getDefault().register(this);
    }
    private void initPrestener(){//实现代理接口
        prestenerMainActivityImp=new PrestenerMainActivity(this);
    }
    private void initview(){//界面实例化
        initNavigationView();
        initFragmentsViewandViewpagerView();
        initBottonbar();
        initDrawerLayout();
    }
    private void initBottonbar(){
        cardView_bottom=(CardView)findViewById(R.id.cardview_bottom);
    }
    private void initNavigationView(){//实例化底部菜单
        pageNavigationView=(PageNavigationView)findViewById(R.id.main_pageNavigation);
        navigationController=pageNavigationView.material()
                .addItem(R.drawable.page_press,"日报")
                .addItem(R.drawable.movie_select,"电影")
                .addItem(R.drawable.code_select,"开发")
                .addItem(R.drawable.huo_select,"娱乐")
                .build();
    }

    /**
     * 实例化fragment和vieepager并且和NavigationView联系起来
     */
    private void initFragmentsViewandViewpagerView(){
        List<Fragment>fragments_list=new ArrayList<Fragment>();
        CodeFragment codeFragment=new CodeFragment();
        HappyFragment happyFragment=new HappyFragment();
        MovieFragment movieFragment=new MovieFragment();
        NoteFragment noteFragment=new NoteFragment();
        fragments_list.add(noteFragment);
        fragments_list.add(movieFragment);
        fragments_list.add(codeFragment);
        fragments_list.add(happyFragment);
        FragmentsAdapter fragmentsAdapter=new FragmentsAdapter(getSupportFragmentManager(),fragments_list);
        ViewPager main_viewpager=(ViewPager)findViewById(R.id.main_viewpager);
        main_viewpager.setAdapter(fragmentsAdapter);
        navigationController.setupWithViewPager(main_viewpager);
    }
    private void initDrawerLayout(){//实例化侧滑栏
        List<String> list=new ArrayList<>();
        list.add("日报");
        list.add("电影");
        list.add("开发");
        list.add("娱乐");
        drawerLayout_main=(DrawerLayout)findViewById(R.id.drawerlayout_main);
        recyclerView_menu=(RecyclerView)findViewById(R.id.recyclerview_main_menu);
        menuRecyclerViewAdapter=new MenuRecyclerViewAdapter(this,list,prestenerMainActivityImp);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView_menu.setAdapter(menuRecyclerViewAdapter);
        recyclerView_menu.setLayoutManager(manager);

        switchButton_night=(SwitchButton)findViewById(R.id.main_switchbutton_background);
        switchButton_night.setChecked(userSeting.getNightState());
        switchButton_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userSeting.putNightState(isChecked);
                prestenerMainActivityImp.setTheNightStatefromUserseting();
                MessageEvent messageEventnote=new MessageEvent(MessageEvent.NIGHTCHAGE);
                EventBus.getDefault().post(messageEventnote);
            }
        });
        drawerLayout_main.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View content=drawerLayout_main.getChildAt(0);
                View menu=drawerView;
                float scale=1-slideOffset;
                content.setTranslationX(menu.getMeasuredWidth()*(1-scale));
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public void showDrawerLayout() {
        if (!drawerLayout_main.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout_main.openDrawer(Gravity.LEFT);
        } else {
            drawerLayout_main.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    public Application getBaseapplication() {
        return getApplication();
    }

    /**
     * 直接设置背景色
     * @param interfaceState
     */
    @Override
    public void setInterfacestateonActivity(InterfaceState interfaceState) {
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.main_relativelayout);
        TextView textView_favorite=(TextView)findViewById(R.id.main_favorite);
        TextView text_background=(TextView)findViewById(R.id.text_bacground);
        relativeLayout.setBackgroundColor(interfaceState.getBackgroundcolor());
        textView_favorite.setTextColor(interfaceState.getTextcolor());
        text_background.setTextColor(interfaceState.getTextcolor());
        menuRecyclerViewAdapter.notifyDataSetChanged();
        pageNavigationView.setBackgroundColor(interfaceState.getPagecolor());
    }

    /**
     * EventBus接受事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handEvent(MessageEvent messageEvent){
        switch (messageEvent.getMessagetype()){
            case MessageEvent.RECYCLERVIEW_DOWN:
                cardView_bottom.setVisibility(View.INVISIBLE);
                break;
            case MessageEvent.RECYCLERVIEW_UP:
                cardView_bottom.setVisibility(View.VISIBLE);
                break;
            case MessageEvent.RECYCLERVIEW_TOP:
                break;
            case MessageEvent.RECYCLERVIEW_BOTTOM:
                break;
                default:
                    break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        CleanMessageUtil.clearAllCache(getApplicationContext());
        GlideCacheUtil.getInstance().clearImageAllCache(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
