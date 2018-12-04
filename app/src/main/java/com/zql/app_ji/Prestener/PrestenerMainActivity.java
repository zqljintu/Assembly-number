package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Model.DatabaseJi;
import com.zql.app_ji.Model.DatabasejiImp;
import com.zql.app_ji.Model.UserSeting;
import com.zql.app_ji.View.Activitys.MainActivityImp;

import java.util.List;

public class PrestenerMainActivity implements PrestenerMainActivityImp{
  private MainActivityImp mainActivityImp;
  private DatabasejiImp databasejiImp;

  public PrestenerMainActivity(MainActivityImp mainActivityImp){
    this.mainActivityImp=mainActivityImp;
    this.databasejiImp=new DatabaseJi(mainActivityImp.getbasecontext());
  }

  @Override
  public void setTheNightStatefromUserseting() {
    UserSeting userSeting=(UserSeting)mainActivityImp.getBaseapplication();
    mainActivityImp.setInterfacestateonActivity(userSeting.getInterfaceState());
  }

  @Override
  public void showDrawerLayoutfromAdapter() {
      mainActivityImp.setDrawerMenusandSum(databasejiImp.queryAllMenuSum());
      mainActivityImp.showDrawerLayout();
  }



  @Override
  public InterfaceState getIntegerfacefromUserseting() {
    UserSeting userSeting=(UserSeting)mainActivityImp.getBaseapplication();
    return userSeting.getInterfaceState();
  }

    @Override
    public void showDrawerMenuandSum() {
        mainActivityImp.setDrawerMenusandSum(databasejiImp.queryAllMenuSum());
    }
}