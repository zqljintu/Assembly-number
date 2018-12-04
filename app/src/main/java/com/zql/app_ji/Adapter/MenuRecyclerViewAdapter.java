package com.zql.app_ji.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Prestener.PrestenerMainActivityImp;
import com.zql.app_ji.R;
import com.zql.app_ji.View.Activitys.FavoriteActivity;

import java.util.List;

import me.majiajie.pagerbottomtabstrip.internal.MaterialItemLayout;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.Viewholder>{
    private List<String>menus;
    private List<Integer>menusum;
    private Context context;
    private PrestenerMainActivityImp prestenerMainActivityImp;

    public MenuRecyclerViewAdapter(Context mcontext,List<String>list,List<Integer>list_sum,PrestenerMainActivityImp prestenerMainActivityImp){
        this.menus=list;
        this.menusum=list_sum;
        this.context=mcontext;
        this.prestenerMainActivityImp=prestenerMainActivityImp;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        InterfaceState interfaceState=prestenerMainActivityImp.getIntegerfacefromUserseting();
        holder.textView_menutitle.setTextColor(interfaceState.getTextcolor());
        holder.cardView_menu.setBackgroundColor(interfaceState.getItemcolor());
        holder.textView_sum.setTextColor(interfaceState.getTextcolor());
        holder.textView_menutitle.setText(menus.get(position));
        holder.textView_sum.setText(getMenuSum(menusum.get(position)));
        startActivity(holder.cardView_menu,position);
    }

    @Override
    public int getItemCount() {
        return menus == null ? 0 : menus.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        CardView cardView_menu;
        TextView textView_menutitle,textView_sum;
        public Viewholder(View view){
            super(view);
            cardView_menu=(CardView)view.findViewById(R.id.main_card);
            textView_menutitle=(TextView)view.findViewById(R.id.main_title_menu);
            textView_sum=(TextView)view.findViewById(R.id.main_menu_sum);
        }
    }
    /**
     * 打开收藏单表
     */
    private void startActivity(View view, final int position){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent=new Intent(context,FavoriteActivity.class);
                mintent.putExtra("type",position);
                context.startActivity(mintent);
                prestenerMainActivityImp.showDrawerLayoutfromAdapter();
            }
        });
    }

    private String getMenuSum(int sum){
        return sum>99?"99+":String.valueOf(sum);
    }
}
