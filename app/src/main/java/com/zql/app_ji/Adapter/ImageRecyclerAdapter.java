package com.zql.app_ji.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
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
        openViewOnDialog(holder.meizi_image,meiziimage_list.get(position).getUrl());
        Glide.with(context).load(meiziimage_list.get(position).getUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
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
    }

    @Override
    public int getItemCount() {
        return meiziimage_list==null? 0 :meiziimage_list.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView meizi_image;
        TextView meizi_text;
        public Viewholder(View itemview){
            super(itemview);
            meizi_image=(ImageView)itemview.findViewById(R.id.image_meizi_image);
            meizi_text=(TextView)itemview.findViewById(R.id.text_meizi_time);
            cardView=(CardView)itemview.findViewById(R.id.meizi_card);
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
    private void openImageviewOnDialog(String url) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View centerview=layoutInflater.inflate(R.layout.dialog_meizi,null);
        final ImageView dialogimageView=(ImageView)centerview.findViewById(R.id.dialog_meizi_image);
        //final TextView textView=(TextView)centerview.findViewById(R.id.dialog_meizi_text);
        final AlertDialog alertDialog=builder.setView(centerview).create();
        Glide.with(context).load(url).into(dialogimageView);
        // textView.setText(url);
        alertDialog.show();
    }

}
