package com.zql.app_ji.View.Activitys;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zql.app_ji.Bean.DetailZhihuNote;
import com.zql.app_ji.Bean.Entity.ZhihuEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.ZhihuNote;
import com.zql.app_ji.Prestener.PrestenerNoteFragment;
import com.zql.app_ji.Prestener.PrestenerNoteFragmentImp;
import com.zql.app_ji.R;
import com.zql.app_ji.Util.HtmlUtil;

public class ZhihuNoteActivity extends AppCompatActivity implements ZhihuNoteActivityImp {
    private ImageView zhihu_bar_image;
    private TextView zhihu_bar_title,zhihu_bar_copyright,zhihu_title;
    private Toolbar zhihu_toolbar;
    private WebView zhihu_webview;
    private PrestenerNoteFragmentImp prestenerNoteFragmentImp;
    private ZhihuEntity zhihuEntity;
    private ZhihuEntity getZhihuEntity() {
        return zhihuEntity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mintent=getIntent();
        int note_id=mintent.getIntExtra("id" ,102708);
        setContentView(R.layout.activity_zhihu_note);
        initprestener();
        initViw();
        prestenerNoteFragmentImp.getDetailZhihuNotefromZhihuAPI(mintent.getIntExtra("id",102708));
        String title=mintent.getStringExtra("title");
        this.zhihuEntity=new ZhihuEntity();
        this.zhihuEntity.setNote_id(String.valueOf(note_id));
        this.zhihuEntity.setTitle(title);
    }
    private void initViw(){//实例化view
        zhihu_bar_image=(ImageView)findViewById(R.id.zhihu_bar_image);
        zhihu_bar_title=(TextView)findViewById(R.id.zhihu_bar_title);
        zhihu_bar_copyright=(TextView)findViewById(R.id.zhihu_bar_copyright);
        zhihu_toolbar=(Toolbar)findViewById(R.id.zhihu_toolBar);
        zhihu_webview=(WebView)findViewById(R.id.zhihu_detail_webview);
        zhihu_toolbar.setTitleTextColor(Color.WHITE);
        zhihu_toolbar.setTitle("");
        zhihu_title=(TextView)findViewById(R.id.toolbar_title);
        setSupportActionBar(zhihu_toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        zhihu_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initprestener(){
        this.prestenerNoteFragmentImp=new PrestenerNoteFragment(this);
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
                prestenerNoteFragmentImp.addFavoritetoDBfromZhihuDetailActivity(getZhihuEntity());
                break;
                default:
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void setDetailZhihuOnActivity(DetailZhihuNote detailZhihuNote) {
        InterfaceState interfaceState=prestenerNoteFragmentImp.getIntegerfacetoActivityfromUserseting();
        CoordinatorLayout coordinatorLayout=(CoordinatorLayout)findViewById(R.id.zhihu_detail);
        coordinatorLayout.setBackgroundColor(interfaceState.getBackgroundcolor());
        zhihu_toolbar.setBackgroundColor(interfaceState.getPagecolor());
        zhihu_webview.setBackgroundColor(interfaceState.getBackgroundcolor());
        Glide.with(this).load(detailZhihuNote.getImage()) .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE) .into(zhihu_bar_image);
        zhihu_bar_title.setText(detailZhihuNote.getTitle());
        zhihu_bar_copyright.setText(detailZhihuNote.getImage_source());
        String htmldata=HtmlUtil.createHtmlData(detailZhihuNote.getBody(),detailZhihuNote.getCss(),detailZhihuNote.getJs(),interfaceState.isNight());
        zhihu_webview.loadData(htmldata,HtmlUtil.MIME_TYPE,HtmlUtil.ENCODING);
        zhihu_title.setText(detailZhihuNote.getTitle());
    }

    @Override
    public void setErrorMessageOnActivity(int ErrorMesage) {

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
