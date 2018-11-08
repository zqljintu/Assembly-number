package com.zql.app_ji.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zql.app_ji.Bean.DoubanMovie;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Prestener.PrestenerMovieFragmentImp;
import com.zql.app_ji.R;
import com.zql.app_ji.View.Activitys.DoubanMovieActivity;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.Viewholder> {
    private Context context;
    private List<DoubanMovie.SubjectsBean>doubanmovie_list;
    private PrestenerMovieFragmentImp prestenerMovieFragmentImp;
    private int index=0;//长按的item序号
    private int getIndex(){
        return this.index;
    }
    private void setIndex(int position){
        this.index=position;
    }


    public MovieRecyclerAdapter(List<DoubanMovie.SubjectsBean>mlist, Context mcontext,PrestenerMovieFragmentImp prestenerMovieFragmentImp){
        this.doubanmovie_list=mlist;
        this.context=mcontext;
        this.prestenerMovieFragmentImp=prestenerMovieFragmentImp;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        Viewholder viewholder=new Viewholder(item);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, int position) {
        InterfaceState interfaceState=prestenerMovieFragmentImp.getIntheatersInterfacestatefromUserseting();
        int count=doubanmovie_list.get(position).getCasts().size();
        StringBuffer casts=new StringBuffer();
        if (count>=1){
            casts.append("主演：");
            casts.append(doubanmovie_list.get(position).getCasts().get(0).getName());
            for (int i=1;i<count;i++){
                casts.append("/"+doubanmovie_list.get(position).getCasts().get(i).getName());
            }
        }
        holder.cardView.setBackgroundColor(interfaceState.getItemcolor());
        holder.mtext_title.setText(doubanmovie_list.get(position).getTitle());
        holder.mtext_title.setTextColor(interfaceState.getTextcolor());
        holder.text_grade.setText(String.valueOf(doubanmovie_list.get(position).getRating().getAverage()));
        holder.text_grade.setTextColor(interfaceState.getTextcolor());
        holder.text_acter.setText(casts.toString());
        holder.text_acter.setTextColor(interfaceState.getTextcolor());
        Glide.with(context).load(doubanmovie_list.get(position).getImages().getSmall()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                int imagewidth=resource.getWidth();
                int imageheight=resource.getHeight();
                int screenWidth=context.getResources().getDisplayMetrics().widthPixels;
                int show_width = (screenWidth-40)/2;
                int show_height= show_width*imageheight/imagewidth;
                ViewGroup.LayoutParams params=holder.imag_poster.getLayoutParams();
                params.height=show_height;
                params.width=show_width;
                holder.imag_poster.setImageBitmap(resource);
            }
        });
        holder.materialRatingBar_movie.setRating((float)doubanmovie_list.get(position).getRating().getAverage()/10*5);
        startContextMenu(holder.cardView,position);
        startDoubanActivity(holder.cardView,doubanmovie_list.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return doubanmovie_list==null? 0 :doubanmovie_list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView mtext_title,text_acter,text_date,text_grade;
        ImageView imag_poster;
        MaterialRatingBar materialRatingBar_movie;
        CardView cardView;
        public Viewholder(View itemview){
            super(itemview);
            mtext_title=(TextView)itemview.findViewById(R.id.textview_movie_title);
            text_acter=(TextView)itemview.findViewById(R.id.textview_movie_acter);
            text_grade=(TextView)itemview.findViewById(R.id.textview_movie_grade);
            imag_poster=(ImageView)itemview.findViewById(R.id.imageview_poster);
            materialRatingBar_movie=(MaterialRatingBar)itemview.findViewById(R.id.ratingbar_movie);
            materialRatingBar_movie.setNumStars(5);
            cardView=(CardView)itemview.findViewById(R.id.movie_card);
            itemview.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(10,0,ContextMenu.NONE,"收藏");
            contextMenu.add(10,1,contextMenu.NONE,"分享");
        }
    }
    private void startDoubanActivity(View view, final String id){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent=new Intent(context,DoubanMovieActivity.class);
                mintent.putExtra("id",id);
                context.startActivity(mintent);
            }
        });
    }
    /**
     * 打开menu
     * @param view
     */
    private void startContextMenu(View view,final int id){
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setIndex(id);
                return false;
            }
        });
    }
    /**
     * 获取长按item的信息
     * @return
     */
    public DoubanMovie.SubjectsBean getLongClickResult(){
        return doubanmovie_list.get(index);
    }
}
