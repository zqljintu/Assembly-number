package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Model.UserSeting;
import com.zql.app_ji.View.Activitys.MainActivityImp;

public class PrestenerMainActivity implements PrestenerMainActivityImp{
  private MainActivityImp mainActivityImp;

  public PrestenerMainActivity(MainActivityImp mainActivityImp){
    this.mainActivityImp=mainActivityImp;
  }

  @Override
  public void setTheNightStatefromUserseting() {
    UserSeting userSeting=(UserSeting)mainActivityImp.getBaseapplication();
    mainActivityImp.setInterfacestateonActivity(userSeting.getInterfaceState());
  }

  @Override
  public void showDrawerLayoutfromAdapter() {
    mainActivityImp.showDrawerLayout();
  }

  @Override
  public InterfaceState getIntegerfacefromUserseting() {
    UserSeting userSeting=(UserSeting)mainActivityImp.getBaseapplication();
    return userSeting.getInterfaceState();
  }
}