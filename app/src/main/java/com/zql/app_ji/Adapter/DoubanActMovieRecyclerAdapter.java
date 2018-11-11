package com.zql.app_ji.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zql.app_ji.Bean.DetailDoubanMovie;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Prestener.PrestenerMovieFragmentImp;
import com.zql.app_ji.R;

import java.util.List;

public class DoubanActMovieRecyclerAdapter extends RecyclerView.Adapter<DoubanActMovieRecyclerAdapter.Viewholder> {
    private Context context;
    private List<DetailDoubanMovie.CastsBean>list_doubanact;
    private PrestenerMovieFragmentImp prestenerMovieFragmentImp;
    public DoubanActMovieRecyclerAdapter(Context mcontext,List<DetailDoubanMovie.CastsBean>mlist,PrestenerMovieFragmentImp prestenerMovieFragmentImp){
        this.context=mcontext;
        this.list_doubanact=mlist;
        this.prestenerMovieFragmentImp=prestenerMovieFragmentImp;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_doubanmovieact,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, int position) {
        InterfaceState interfaceState=prestenerMovieFragmentImp.getActivityInterfacefromUserting();
        Glide.with(context).load(list_doubanact.get(position).getAvatars().getMedium()) .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE) .into(holder.douban_act_image);
        holder.douban_act_name.setTextColor(interfaceState.getTextcolor());
        holder.douban_act_name.setText(list_doubanact.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list_doubanact==null ? 0: list_doubanact.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        ImageView douban_act_image;
        TextView douban_act_name;
        public Viewholder(View view){
            super(view);
            douban_act_image=(ImageView)view.findViewById(R.id.douban_act_image);
            douban_act_name=(TextView)view.findViewById(R.id.douban_act_name);
        }
    }
}
