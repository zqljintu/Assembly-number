package com.zql.app_ji.View.Activitys;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zql.app_ji.Adapter.DoubanActMovieRecyclerAdapter;
import com.zql.app_ji.Bean.DetailDoubanMovie;
import com.zql.app_ji.Bean.DoubanMovie;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Prestener.PrestenerMovieFragment;
import com.zql.app_ji.Prestener.PrestenerMovieFragmentImp;
import com.zql.app_ji.R;

import java.util.ArrayList;
import java.util.List;

import me.next.tagview.TagCloudView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class DoubanMovieActivity extends SwipeActivity implements DoubanMovieActivityImp{
    private PrestenerMovieFragmentImp prestenerMovieFragmentImp;
    private ImageView douban_image,douban_movie_image;
    private Toolbar douban_toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView douban_movie_title,douban_movie_original_title,douban_movie_time,douban_movie_grade,douban_movie_countrys,douban_movie_summary;
    private MaterialRatingBar douban_movie_ratingbar;
    private TagCloudView douban_movie_tags;
    private RecyclerView douban_act_recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mintent=getIntent();
        setContentView(R.layout.activity_douban_movie);


        initPrestener();
        initView();
        prestenerMovieFragmentImp.getDetailDoubanMoviefromDoubanAPI(mintent.getStringExtra("id"));
    }
    private void initPrestener(){//实例接口
        prestenerMovieFragmentImp=new PrestenerMovieFragment(this);
    }
    private void initView(){//实例化界面
        InterfaceState interfaceState=prestenerMovieFragmentImp.getActivityInterfacefromUserting();
        CoordinatorLayout coordinatorLayout=(CoordinatorLayout)findViewById(R.id.douban_coordinator);
        coordinatorLayout.setBackgroundColor(interfaceState.getBackgroundcolor());
        CardView cardView_movie_about=(CardView)findViewById(R.id.card_movie_about);
        cardView_movie_about.setBackgroundColor(interfaceState.getItemcolor());
        CardView cardView_movie_juqing=(CardView)findViewById(R.id.card_movie_juqing);
        cardView_movie_juqing.setBackgroundColor(interfaceState.getItemcolor());
        CardView cardView_movie_act=(CardView)findViewById(R.id.card_movie_act);
        cardView_movie_act.setBackgroundColor(interfaceState.getItemcolor());
        TextView text_movie_about=(TextView)findViewById(R.id.text_movie_about);
        TextView text_movie_juqing=(TextView)findViewById(R.id.text_movie_juqing);
        TextView text_movie_act=(TextView)findViewById(R.id.text_movie_act);
        text_movie_about.setTextColor(interfaceState.getTextcolor());
        text_movie_juqing.setTextColor(interfaceState.getTextcolor());
        text_movie_act.setTextColor(interfaceState.getTextcolor());
        douban_image=(ImageView)findViewById(R.id.douban_imageview);
        douban_movie_image=(ImageView)findViewById(R.id.douban_movie_image);
        douban_movie_title=(TextView)findViewById(R.id.douban_movie_title);
        douban_movie_title.setTextColor(interfaceState.getTextcolor());
        douban_movie_original_title=(TextView)findViewById(R.id.douban_movie_original_title);
        douban_movie_original_title.setTextColor(interfaceState.getTextcolor());
        douban_movie_grade=(TextView)findViewById(R.id.douban_movie_grade);
        douban_movie_grade.setTextColor(interfaceState.getItemcolor());
        douban_movie_countrys=(TextView)findViewById(R.id.douban_movie_countrys);
        douban_movie_countrys.setTextColor(interfaceState.getTextcolor());
        douban_movie_summary=(TextView)findViewById(R.id.douban_movie_summary);
        douban_movie_summary.setTextColor(interfaceState.getTextcolor());
        douban_movie_ratingbar=(MaterialRatingBar)findViewById(R.id.doubanmovie_ratingbar);
        douban_movie_ratingbar.setNumStars(5);
        douban_movie_tags=(TagCloudView)findViewById(R.id.douban_movie_tags);
        douban_movie_time=(TextView) findViewById(R.id.douban_movie_time);
        douban_movie_time.setTextColor(interfaceState.getTextcolor());
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.douban_colltoolbarlayout);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        douban_toolbar=(Toolbar)findViewById(R.id.douban_toolbar);
        setSupportActionBar(douban_toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        douban_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setTitle(" ");
        douban_act_recyclerView=(RecyclerView)findViewById(R.id.douban_acts_recyclerview);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
    }

    @Override
    public void setDetailDoubanMovieonActivity(DetailDoubanMovie detailDoubanMovie) {
        Glide.with(this).load(detailDoubanMovie.getImages().getMedium()) .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE) .into(douban_image);
        Glide.with(this).load(detailDoubanMovie.getImages().getMedium()).asBitmap() .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE) .into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                int imagewidth=resource.getWidth();
                int imageheight=resource.getHeight();
                int screenWidth=getResources().getDisplayMetrics().widthPixels;
                int show_width = (screenWidth-20)/3;
                int show_height= show_width*imageheight/imagewidth;
                ViewGroup.LayoutParams params=douban_movie_image.getLayoutParams();
                params.height=show_height;
                params.width=show_width;
                douban_movie_image.setImageBitmap(resource);
            }
        });
        collapsingToolbarLayout.setTitle(detailDoubanMovie.getTitle());
        douban_movie_title.setText(detailDoubanMovie.getTitle());
        douban_movie_original_title.setText(detailDoubanMovie.getOriginal_title());
        douban_movie_grade.setText(String.valueOf(detailDoubanMovie.getRating().getAverage()));
        douban_movie_ratingbar.setRating((float)detailDoubanMovie.getRating().getAverage()/10*5);
        douban_movie_time.setText("上映时间："+detailDoubanMovie.getYear());
        StringBuffer stringBuffer=new StringBuffer();
        int count_country=detailDoubanMovie.getCountries().size();
        if (count_country>=1){
            stringBuffer.append(detailDoubanMovie.getCountries().get(0));
            if (count_country>1){
                for (int i=1;i<count_country;i++){
                    stringBuffer.append("/"+detailDoubanMovie.getCountries().get(i));
                }
            }
        }
        douban_movie_countrys.setText(stringBuffer.toString());
        List<String>tags=new ArrayList<>();
        for (int i=0;i<detailDoubanMovie.getGenres().size();i++){
            tags.add(detailDoubanMovie.getGenres().get(i));
        }
        douban_movie_tags.setTags(tags);
        douban_movie_summary.setText(detailDoubanMovie.getSummary().toString());
        DoubanActMovieRecyclerAdapter doubanActMovieRecyclerAdapter=new DoubanActMovieRecyclerAdapter(this,detailDoubanMovie.getCasts(),prestenerMovieFragmentImp);
        douban_act_recyclerView.setLayoutManager(layoutManager);
        douban_act_recyclerView.setAdapter(doubanActMovieRecyclerAdapter);
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
