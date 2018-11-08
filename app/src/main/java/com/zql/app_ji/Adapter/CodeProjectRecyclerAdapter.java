package com.zql.app_ji.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.WanProject;
import com.zql.app_ji.Prestener.PrestenerCodeFragmentImp;
import com.zql.app_ji.R;
import com.zql.app_ji.View.Activitys.WanDetailActivity;

import java.util.List;

public class CodeProjectRecyclerAdapter extends RecyclerView.Adapter<CodeProjectRecyclerAdapter.Viewholder> {
    private Context context;
    private List<WanProject.DataBean.DatasBean>list_project;
    private PrestenerCodeFragmentImp prestenerCodeFragmentImp;
    private int index=0;//长按的item序号
    private int getIndex(){return this.index;}
    private void setIndex(int position){
        this.index=position;
    }

    public CodeProjectRecyclerAdapter(List<WanProject.DataBean.DatasBean>mlist,Context mcontext,PrestenerCodeFragmentImp prestenerCodeFragmentImp){
        this.context=mcontext;
        this.list_project=mlist;
        this.prestenerCodeFragmentImp=prestenerCodeFragmentImp;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_wanproject,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder  holder, int position) {
        InterfaceState interfaceState=prestenerCodeFragmentImp.getProjectInterfacestacefromUserseting();
        holder.cardView.setBackgroundColor(interfaceState.getItemcolor());
        holder.title_project.setTextColor(interfaceState.getTextcolor());
        holder.title_project.setText(list_project.get(position).getTitle());
        holder.dec_project.setText(list_project.get(position).getDesc());
        holder.dec_project.setTextColor(interfaceState.getTextcolor());
        Glide.with(context).load(list_project.get(position).getEnvelopePic()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                int imagewidth=resource.getWidth();
                int imageheight=resource.getHeight();
                int screenWidth=context.getResources().getDisplayMetrics().widthPixels;
                int show_width =(screenWidth-10)*1/4;
                int show_height= show_width*imageheight/imagewidth;
                ViewGroup.LayoutParams params=holder.image_project.getLayoutParams();
                params.height=show_height;
                params.width=show_width;
                holder.image_project.setImageBitmap(resource);
            }
        });
        startWandetailActivity(holder.cardView,list_project.get(position).getLink(),list_project.get(position).getTitle());
        startContextMenu(holder.cardView,position);
    }

    @Override
    public void onViewRecycled(Viewholder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return list_project==null ? 0 : list_project.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView title_project,dec_project;
        ImageView image_project;
        CardView cardView;
        public Viewholder(View view){
            super(view);
            title_project=(TextView)view.findViewById(R.id.title_codeproject);
            dec_project=(TextView)view.findViewById(R.id.dec_codeproject);
            image_project=(ImageView)view.findViewById(R.id.image_project);
            cardView=(CardView)view.findViewById(R.id.wan_project_card);
            view.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(21,0,ContextMenu.NONE,"收藏");
            contextMenu.add(21,1,contextMenu.NONE,"分享");
        }
    }
    /**
     * 打开详情界面
     */
    private void startWandetailActivity(View view, final String url,final String title){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent=new Intent(context,WanDetailActivity.class);
                mintent.putExtra("url",url);
                mintent.putExtra("title",title);
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
    public WanProject.DataBean.DatasBean getLongClickRseult(){
        return list_project.get(getIndex());
    }
}
