package com.zql.app_ji.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.WanArticle;
import com.zql.app_ji.Prestener.PrestenerCodeFragment;
import com.zql.app_ji.Prestener.PrestenerCodeFragmentImp;
import com.zql.app_ji.R;
import com.zql.app_ji.View.Activitys.WanDetailActivity;

import java.util.ArrayList;
import java.util.List;

import me.next.tagview.TagCloudView;

public class CodeArticleRecyclerAdapter extends RecyclerView.Adapter<CodeArticleRecyclerAdapter.Viewholder> {
    private List<WanArticle.DataBean.DatasBean> list_data;
    private Context context;
    private int index=0;//长按的item序号
    private PrestenerCodeFragmentImp prestenerCodeFragmentImp;
    private int getIndex(){return this.index;}
    private void setIndex(int position){
        this.index=position;
    }

    public CodeArticleRecyclerAdapter(List<WanArticle.DataBean.DatasBean>mlist,Context mcontext,PrestenerCodeFragmentImp prestenerCodeFragmentImp){
        this.list_data=mlist;
        this.context=mcontext;
        this.prestenerCodeFragmentImp=prestenerCodeFragmentImp;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewitem=LayoutInflater.from(context).inflate(R.layout.item_wanarticle,parent,false);
        Viewholder viewholder=new Viewholder(viewitem);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, int position) {
        InterfaceState interfaceState=prestenerCodeFragmentImp.getArticleInterfacestatefromUserseting();
        holder.cardView.setBackgroundColor(interfaceState.getItemcolor());
        holder.title_text.setTextColor(interfaceState.getTextcolor());
        holder.title_text.setText(list_data.get(position).getTitle());
        holder.author_text.setTextColor(interfaceState.getTextcolor());
        holder.author_text.setText("作者："+list_data.get(position).getAuthor());
        holder.createtime_text.setText("时间："+list_data.get(position).getNiceDate());
        holder.createtime_text.setTextColor(interfaceState.getTextcolor());
        holder.class_text.setText("分类："+list_data.get(position).getSuperChapterName());
        holder.class_text.setTextColor(interfaceState.getTextcolor());
        List<String> tags=new ArrayList<>();
        for (int i=0;i<list_data.get(position).getTags().size();i++){
            tags.add(list_data.get(position).getTags().get(i).getName());
        }
        holder.tagCloudView.setTags(tags);
        startWandetailActivity(holder.cardView,list_data.get(position).getLink(),list_data.get(position).getTitle());
        startContextMenu(holder.cardView,position);
    }

    @Override
    public void onViewRecycled(Viewholder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return list_data==null? 0 :list_data.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView title_text,author_text,class_text,createtime_text;
        TagCloudView tagCloudView;
        CardView cardView;
        public Viewholder(View view){
            super(view);
            title_text=(TextView)view.findViewById(R.id.title_codearticle);
            author_text=(TextView)view.findViewById(R.id.text_author_article);
            class_text=(TextView)view.findViewById(R.id.text_class_wanarticle);
            createtime_text=(TextView)view.findViewById(R.id.text_createtime_wanarticle);
            tagCloudView=(TagCloudView)view.findViewById(R.id.tag_wanarticle);
            cardView=(CardView)view.findViewById(R.id.wan_article_card);
            view.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(20,0,ContextMenu.NONE,"收藏");
            contextMenu.add(20,1,contextMenu.NONE,"分享");
        }
    }

    /**
     * 打开详情界面
     * @param view
     * @param url
     * @param title
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
    private void startContextMenu(View view, final int id){
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
    public WanArticle.DataBean.DatasBean getLongClickResult(){
        return list_data.get(getIndex());
    }
}
