package com.zql.app_ji.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.chrisbanes.photoview.PhotoView;
import com.zql.app_ji.Bean.GankImage;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Prestener.PrestenerHappyFragment;
import com.zql.app_ji.Prestener.PrestenerHappyFragmentImp;
import com.zql.app_ji.R;

import java.util.List;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.Viewholder> {
    private Context context;
    private List<GankImage.ResultsBean>meiziimage_list;
    private PrestenerHappyFragmentImp prestenerHappyFragmentImp;
    private int index=0;//长按的item序号
    private int getIndex(){return this.index;}
    private void setIndex(int position){
        this.index=position;
    }

    public ImageRecyclerAdapter(List<GankImage.ResultsBean>mlist, Context mcontext, PrestenerHappyFragmentImp mprestenerHappyFragmentImp){
        this.meiziimage_list=mlist;
        this.context=mcontext;
        this.prestenerHappyFragmentImp=mprestenerHappyFragmentImp;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);
        Viewholder viewholder=new Viewholder(item);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, int position) {
        InterfaceState interfaceState=prestenerHappyFragmentImp.getIntegerfacefromSeting();
        holder.meizi_text.setTextColor(interfaceState.getTextcolor());
        holder.meizi_text.setText(meiziimage_list.get(position).getDesc());
        holder.cardView.setBackgroundColor(interfaceState.getItemcolor());
        openViewOnDialog(holder.cardView,meiziimage_list.get(position).getUrl());
        Glide.with(context).load(meiziimage_list.get(position).getUrl()).asBitmap() .into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                int imagewidth=resource.getWidth();
                int imageheight=resource.getHeight();
                int screenWidth=context.getResources().getDisplayMetrics().widthPixels;
                int show_width = (screenWidth-20)/2;
                int show_height= show_width*imageheight/imagewidth;
                ViewGroup.LayoutParams params=holder.meizi_image.getLayoutParams();
                params.height=show_height;
                params.width=show_width;
                holder.meizi_image.setImageBitmap(resource);
            }
        });
        startContextMenu(holder.cardView,position);
    }
    @Override
    public void onViewRecycled(Viewholder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }
    @Override
    public int getItemCount() {
        return meiziimage_list==null? 0 :meiziimage_list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        CardView cardView;
        ImageView meizi_image;
        TextView meizi_text;
        public Viewholder(View itemview){
            super(itemview);
            meizi_image=(ImageView)itemview.findViewById(R.id.image_meizi_image);
            meizi_text=(TextView)itemview.findViewById(R.id.text_meizi_time);
            cardView=(CardView)itemview.findViewById(R.id.meizi_card);
            itemview.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(4,0,ContextMenu.NONE,"保存到本地");
            menu.add(4,1,ContextMenu.NONE,"分享给好友");
        }
    }
    private void openViewOnDialog(View view, final String url){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageviewOnDialog(url);
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    private void openImageviewOnDialog(final String url) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View centerview=layoutInflater.inflate(R.layout.dialog_meizi,null);
       // final ImageView dialogimageView=(ImageView)centerview.findViewById(R.id.dialog_meizi_image);
        final PhotoView photoView=(PhotoView)centerview.findViewById(R.id.dialog_meizi_photoview);
        final ImageView image_download=(ImageView)centerview.findViewById(R.id.dialog_meizi_download);
        final ImageView image_close=(ImageView)centerview.findViewById(R.id.dialog_meizi_close);
        final AlertDialog alertDialog=builder.setView(centerview).create();
        Glide.with(context).load(url).into(photoView);
        image_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prestenerHappyFragmentImp.downImagetophone(url);
            }
        });
        image_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
       /* photoView.setOnTouchListener(new View.OnTouchListener() {
            float pos_x,pos_y,m_x,m_y;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        pos_x=event.getX();
                        pos_y=event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        m_x=event.getX();
                        m_y=event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (m_y-pos_y>0&&(Math.abs(m_y-pos_y)>25)){
                            alertDialog.dismiss();
                        }else if (m_y-pos_y<0&&(Math.abs(m_y-pos_y)>25)){
                            alertDialog.dismiss();
                        }
                        break;
                        default:
                            break;
                }
                return true;
            }
        });*/
        alertDialog.show();
    }
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
    public GankImage.ResultsBean getLongClickRseult(){
        return meiziimage_list.get(getIndex());
    }

}
