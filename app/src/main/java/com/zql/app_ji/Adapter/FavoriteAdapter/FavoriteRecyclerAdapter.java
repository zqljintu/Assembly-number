package com.zql.app_ji.Adapter.FavoriteAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zql.app_ji.Bean.Entity.ZhihuEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Prestener.PrestenerFavoriteActivityImp;
import com.zql.app_ji.R;
import com.zql.app_ji.View.Activitys.ZhihuNoteActivity;

import java.util.List;

public class FavoriteRecyclerAdapter extends RecyclerView.Adapter<FavoriteRecyclerAdapter.Viewholder>{
    private Context context;
    private List<ZhihuEntity>favoriteEntyList;
    private PrestenerFavoriteActivityImp prestenerFavoriteActivityImp;
    private int index;
    private int getIndex(){
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public FavoriteRecyclerAdapter(Context context, List<ZhihuEntity> favoriteEnties,PrestenerFavoriteActivityImp prestenerFavoriteActivityImp){
        this.context=context;
        this.favoriteEntyList=favoriteEnties;
        this.prestenerFavoriteActivityImp=prestenerFavoriteActivityImp;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        InterfaceState interfaceState=prestenerFavoriteActivityImp.getTheNightstatefromUserseting();
        holder.cardView.setBackgroundColor(interfaceState.getItemcolor());
        holder.titletext.setTextColor(interfaceState.getTextcolor());
        holder.titletext.setText(favoriteEntyList.get(position).getTitle());
        startZhihudetailActivity(holder.cardView,Integer.parseInt(favoriteEntyList.get(position).getNote_id()));
        startContextMenu(holder.cardView,position);
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_favorite,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public int getItemCount() {
        return favoriteEntyList==null ? 0 : favoriteEntyList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView titletext;
        private CardView cardView;
        public Viewholder(View view){
            super(view);
            titletext=(TextView)view.findViewById(R.id.favorite_title_text);
            cardView=(CardView)view.findViewById(R.id.favorite_card);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(101,0,ContextMenu.NONE,"删除");
            menu.add(101,1,ContextMenu.NONE,"分享");
        }
    }

    /**
     * 打开详情界面
     * @param view
     * @param id
     */
    private void startZhihudetailActivity(View view,final int id){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent=new Intent(context,ZhihuNoteActivity.class);
                mintent.putExtra("id",id);
                context.startActivity(mintent);
            }
        });
    }

    /**
     * 打开contextMenu
     * @param view
     * @param position
     */
    private void startContextMenu(View view,final int position){
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setIndex(position);
                return false;
            }
        });
    }
    /**
     * 获取长按item的信息
     * @return
     */
    public ZhihuEntity getLongClickResult(){
        return favoriteEntyList.get(getIndex());
    }
}
