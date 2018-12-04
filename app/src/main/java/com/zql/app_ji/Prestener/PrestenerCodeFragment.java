package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.Entity.WanEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.WanArticle;
import com.zql.app_ji.Bean.WanProject;
import com.zql.app_ji.Model.DatabaseJi;
import com.zql.app_ji.Model.DatabasejiImp;
import com.zql.app_ji.Model.GetWanAndroidgsonModel;
import com.zql.app_ji.Model.GetWanAndroidgsonModelImp;
import com.zql.app_ji.Model.UserSeting;
import com.zql.app_ji.View.Activitys.WanDetailActivity;
import com.zql.app_ji.View.Activitys.WanDetailActivityImp;
import com.zql.app_ji.View.Fragments.CodeFragmentImp;
import com.zql.app_ji.View.Fragments.Code_articleFragmentImp;
import com.zql.app_ji.View.Fragments.Code_projectFragmentImp;

public class PrestenerCodeFragment implements PrestenerCodeFragmentImp{
    private CodeFragmentImp codeFragmentImp;
    private Code_articleFragmentImp code_articleFragmentImp;
    private Code_projectFragmentImp code_projectFragmentImp;
    private GetWanAndroidgsonModelImp getWanAndroidgsonModelImp;
    private WanDetailActivityImp wanDetailActivityImp;
    private DatabasejiImp databasejiImp;
    private int type,type_project;

    public PrestenerCodeFragment(CodeFragmentImp codeFragmentImp){
        this.codeFragmentImp=codeFragmentImp;
        this.getWanAndroidgsonModelImp=new GetWanAndroidgsonModel(this);
        databasejiImp=new DatabaseJi(codeFragmentImp.getBasecontext());
    }
    public PrestenerCodeFragment(Code_articleFragmentImp mcode_articleFragmentImp){
        this.code_articleFragmentImp=mcode_articleFragmentImp;
        this.getWanAndroidgsonModelImp=new GetWanAndroidgsonModel(this);
        databasejiImp=new DatabaseJi(code_articleFragmentImp.getBasecontext());
    }
    public PrestenerCodeFragment(Code_projectFragmentImp mcode_projectFragmentImp){
        this.code_projectFragmentImp=mcode_projectFragmentImp;
        this.getWanAndroidgsonModelImp=new GetWanAndroidgsonModel(this);
        databasejiImp=new DatabaseJi(code_projectFragmentImp.getBasecontext());
    }
    public PrestenerCodeFragment(WanDetailActivityImp wanDetailActivityImp){
        this.wanDetailActivityImp=wanDetailActivityImp;
        databasejiImp=new DatabaseJi(wanDetailActivityImp.getBasecontext());
    }

    /**
     * 文章接口实现
     * @param page
     */
    @Override
    public void getWanArticlefromWanAPI(int page,int type) {
        getWanAndroidgsonModelImp.getArticlesfromWanAPI(page);
        this.type=type;
    }

    @Override
    public void setWanArticleonArticleRecyclerView(WanArticle wanArticle) {
        code_articleFragmentImp.setWanAricleOnRecyclerView(wanArticle,this.type);
    }

    @Override
    public void setNightstateBackgroundtoArticle() {
        UserSeting userSeting=(UserSeting)code_articleFragmentImp.getbaseapplication();

    }

    @Override
    public InterfaceState getArticleInterfacestatefromUserseting() {
        UserSeting userSeting=(UserSeting)code_articleFragmentImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }

    /**
     * 项目接口实现
     * @param page
     */

    @Override
    public void getWanProjectfromWanAPI(int page,int type) {
        getWanAndroidgsonModelImp.getProjectsfromWanAPI(page);
        this.type_project=type;
    }

    @Override
    public void setWanProjectonArticleRecyclerView(WanProject wanProject) {
        code_projectFragmentImp.setWanProjectsOnRecyclerview(wanProject,this.type_project);
    }

    @Override
    public void setErrorMessageOnArticleFragment(int ErrorMessage) {

    }

    @Override
    public void setErrorMessageOnProjectFragment(int ErrorMessage) {

    }

    @Override
    public void setNightstateBackgroundtoProject() {
        UserSeting userSeting=(UserSeting)code_projectFragmentImp.getbaseapplication();

    }

    @Override
    public InterfaceState getProjectInterfacestacefromUserseting() {
        UserSeting userSeting=(UserSeting)code_projectFragmentImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }

    /**
     * 加载数据库接口实现
     */
    @Override
    public void addFavoritetoDatabase(WanEntity wanEntity) {
        databasejiImp.insertWanEntitytoDB(wanEntity);
    }

    @Override
    public void setNightstateBackgroundtoFragment() {
        UserSeting userSeting=(UserSeting)codeFragmentImp.getbaseapplication();
        codeFragmentImp.setTheNightstatefromUserseting(userSeting.getInterfaceState());
    }

    @Override
    public InterfaceState getActivityInterfacestatefromUserSting() {
        UserSeting userSeting=(UserSeting)wanDetailActivityImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }
}
