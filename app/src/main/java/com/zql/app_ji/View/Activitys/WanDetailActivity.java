package com.zql.app_ji.View.Activitys;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;

import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Prestener.PrestenerCodeFragment;
import com.zql.app_ji.Prestener.PrestenerCodeFragmentImp;
import com.zql.app_ji.R;
import com.zql.app_ji.Util.HtmlUtil;

import java.net.URI;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.internal.http.HttpDate;
import retrofit2.Retrofit;
import retrofit2.http.HTTP;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

public class WanDetailActivity extends SwipeActivity implements WanDetailActivityImp{
    private WebView wan_webview;
    private Toolbar wan_toolbar;
    private NestedScrollView wan_nestedScrollView;
    private PrestenerCodeFragmentImp prestenerCodeFragmentImp;
    private WanEntity wanEntity;

    private WanEntity getWanEntity() {
        return wanEntity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_detail);
        setSwipeAnyWhere(true);
        Intent mintent=getIntent();
        String url=mintent.getStringExtra("url");
        String title=mintent.getStringExtra("title");
        this.wanEntity=new WanEntity();
        this.wanEntity.setWan_url(url);
        this.wanEntity.setTitle(title);
        initprestener();
        initView(url,title);
    }
    private void initprestener(){
        prestenerCodeFragmentImp=new PrestenerCodeFragment(this);
    }
    private void initView(String url,String title){
        InterfaceState interfaceState=prestenerCodeFragmentImp.getActivityInterfacestatefromUserSting();
        wan_toolbar=(Toolbar)findViewById(R.id.wan_toolBar);
        wan_toolbar.setBackgroundColor(interfaceState.getPagecolor());
        wan_nestedScrollView=(NestedScrollView)findViewById(R.id.wan_scroller);
        wan_webview=(WebView)findViewById(R.id.wan_detail_webview);
        setSupportActionBar(wan_toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wan_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        wan_webview.loadUrl(url);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:
                break;
            case R.id.action_favorite:
                prestenerCodeFragmentImp.addFavoritetoDatabase(getWanEntity());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getBasecontext() {
        return this;
    }

    @Override
    public Application getbaseapplication() {
        return getApplication();
    }
}