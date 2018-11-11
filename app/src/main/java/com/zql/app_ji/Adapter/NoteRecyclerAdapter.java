package com.zql.app_ji.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.ZhihuNote;
import com.zql.app_ji.Prestener.PrestenerNoteFragmentImp;
import com.zql.app_ji.R;
import com.zql.app_ji.View.Activitys.ZhihuNoteActivity;
import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.Viewholder>{
    private List<ZhihuNote.StoriesBean>list_zhihu_storiesbean;
    private Context mContex;
    private PrestenerNoteFragmentImp prestenerNoteFragmentImp;
    private int index=0;//长按的item序号
    private int getContextIndex(){return this.index;}
    private void setContextMenuIndex(int position){
        this.index=position;
    }

    public NoteRecyclerAdapter(List<ZhihuNote.StoriesBean> mlist, Context context,PrestenerNoteFragmentImp prestenerNoteFragmentImp){
        this.list_zhihu_storiesbean=mlist;
        this.mContex=context;
        this.prestenerNoteFragmentImp=prestenerNoteFragmentImp;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_recycler=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        Viewholder viewholder=new Viewholder(item_recycler);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, int position) {
        InterfaceState interfaceState=prestenerNoteFragmentImp.getIntegerfacefromUserseting();
        holder.cardView.setBackgroundColor(interfaceState.getItemcolor());
        holder.mText0.setTextColor(interfaceState.getTextcolor());
        holder.mText0.setText(list_zhihu_storiesbean.get(position).getTitle());
        startContextMenu(holder.cardView,position);
        startZhihunoteActivity(holder.cardView,list_zhihu_storiesbean.get(position).getId(),list_zhihu_storiesbean.get(position).getTitle());
        Glide.with(mContex).load(list_zhihu_storiesbean.get(position).getImages().get(0)) .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE) .into(holder.mImView);
    }

    @Override
    public int getItemCount() {
        return list_zhihu_storiesbean==null ? 0 : list_zhihu_storiesbean.size();
    }

    @Override
    public void onViewRecycled(Viewholder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    public  class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView mText0;
        ImageView mImView;
        CardView cardView;
        public Viewholder(View itemView){
            super(itemView);
            mText0=(TextView)itemView.findViewById(R.id.question_title);
            mImView=(ImageView)itemView.findViewById(R.id.thumbnail_image);
            cardView=(CardView)itemView.findViewById(R.id.zhihu_card);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(0,0,ContextMenu.NONE,"收藏");
            contextMenu.add(0,1,contextMenu.NONE,"分享");
        }

    }

    /**
     * 打开详情界面
     */
    private void startZhihunoteActivity(View view, final int id,final String title){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintet=new Intent(mContex,ZhihuNoteActivity.class);
                mintet.putExtra("id",id);
                mintet.putExtra("title",title);
                mContex.startActivity(mintet);
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
                setContextMenuIndex(id);
                return false;
            }
        });
    }

    /**
     * 获取长按item的信息
     * @return
     */
    public ZhihuNote.StoriesBean getLongClickResult(){
        return list_zhihu_storiesbean.get(getContextIndex());
    }
}
