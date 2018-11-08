package com.zql.app_ji.Model;

import android.app.Application;
import android.content.SharedPreferences;

import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.R;

public class UserSeting extends Application implements UserSetingImp {
    private SharedPreferences sharedPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        initSharedPreferences();
    }
    private void initSharedPreferences(){
        sharedPreferences=getSharedPreferences("seting_ji",MODE_PRIVATE);
    }

    @Override
    public InterfaceState getInterfaceState() {
        InterfaceState interfaceState=new InterfaceState();
        if (sharedPreferences.getBoolean("night",false)){
            interfaceState.setBackgroundcolor(getResources().getColor(R.color.colorNightmain));
            interfaceState.setItemcolor(getResources().getColor(R.color.colorNightItem));
            interfaceState.setTextcolor(getResources().getColor(R.color.colorNightText));
            interfaceState.setPagecolor(getResources().getColor(R.color.colorNightItem));
            interfaceState.setNight(true);
        }else {
            interfaceState.setBackgroundcolor(getResources().getColor(R.color.colorDaymain));
            interfaceState.setItemcolor(getResources().getColor(R.color.colorDayItem));
            interfaceState.setTextcolor(getResources().getColor(R.color.colorDatText));
            interfaceState.setPagecolor(getResources().getColor(R.color.color_feng));
            interfaceState.setNight(false);
        }
        return interfaceState;
    }

    @Override
    public void putNightState(boolean night) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("night",night);
        editor.commit();
        editor.apply();
    }

    @Override
    public boolean getNightState() {
        return sharedPreferences.getBoolean("night",false);
    }
}
