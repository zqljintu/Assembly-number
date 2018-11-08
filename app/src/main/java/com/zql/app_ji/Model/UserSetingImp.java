package com.zql.app_ji.Model;

import com.zql.app_ji.Bean.InterfaceState;

public interface UserSetingImp {
    public void putNightState(boolean night);//设置夜间模式
    public boolean getNightState();//读取夜间模式
    public InterfaceState getInterfaceState();//读取颜色
}
