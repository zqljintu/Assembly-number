package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.DetailZhihuNote;
import com.zql.app_ji.Bean.Entity.ZhihuEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Bean.ZhihuNote;
import com.zql.app_ji.Model.DatabaseJi;
import com.zql.app_ji.Model.DatabasejiImp;
import com.zql.app_ji.Model.GetZhihugsonModel;
import com.zql.app_ji.Model.GetZhihugsonModelImp;
import com.zql.app_ji.Model.UserSeting;
import com.zql.app_ji.View.Activitys.ZhihuNoteActivityImp;
import com.zql.app_ji.View.Fragments.NoteFragmentImp;

public class PrestenerNoteFragment implements PrestenerNoteFragmentImp {
    private NoteFragmentImp noteFragmentImp;
    private GetZhihugsonModelImp getZhihugsonModelImp;
    private ZhihuNoteActivityImp zhihuNoteActivityImp;
    private DatabasejiImp databasejiImp;//数据库
    public PrestenerNoteFragment(NoteFragmentImp mnoteFragmentImp){
        this.noteFragmentImp=mnoteFragmentImp;
        getZhihugsonModelImp=new GetZhihugsonModel(this);
        this.databasejiImp=new DatabaseJi(noteFragmentImp.getContext());
    }
    public PrestenerNoteFragment(ZhihuNoteActivityImp mzhihuNoteActivityImp){
        this.zhihuNoteActivityImp=mzhihuNoteActivityImp;
        getZhihugsonModelImp=new GetZhihugsonModel(this);
        this.databasejiImp=new DatabaseJi(zhihuNoteActivityImp.getBasecontext());
    }

    /**
     * 获取知乎列表
     */

    @Override
    public void getZhihuNotefromZhihuAPI() {
        getZhihugsonModelImp.getZhihuNotefromZhihuAPI();
    }

    @Override
    public void getBeforeDateZhihufromZhihuAPI(String befordate) {
        getZhihugsonModelImp.getBeforeDateZhihuNotefromZhihuAPI(befordate);
    }

    @Override
    public void setZhihuNotetoRecyclerView(ZhihuNote zhihuNote) {
        noteFragmentImp.setRecyclerItemonPagefromNews(zhihuNote);
    }

    @Override
    public void setErrorMessagetoNoteFragment(String errMessage) {

    }
    /**
     * 获取详情知乎日报
     */
    @Override
    public void getDetailZhihuNotefromZhihuAPI(int id) {
        getZhihugsonModelImp.getDetailZhihuNotefromZhihuAPI(id);
    }

    @Override
    public void setDetailZhihuNotetoActivity(DetailZhihuNote detailZhihuNote) {
        zhihuNoteActivityImp.setDetailZhihuOnActivity(detailZhihuNote);
    }

    @Override
    public void setErrorMssagetoActivity(int errorMssage) {
        zhihuNoteActivityImp.setErrorMessageOnActivity(errorMssage);
    }
    /**
     * 添加至数据库
     */
    @Override
    public void addFavoratetoDataBase(ZhihuNote.StoriesBean storiesBean) {
        ZhihuEntity zhihuEntity=new ZhihuEntity();
        zhihuEntity.setTitle(storiesBean.getTitle());
        zhihuEntity.setNote_id(String.valueOf(storiesBean.getId()));
        databasejiImp.insertZhihuEntitytoDB(zhihuEntity);
    }

    @Override
    public void addFavoritetoDBfromZhihuDetailActivity(ZhihuEntity zhihuEntity) {
        databasejiImp.insertZhihuEntitytoDB(zhihuEntity);
    }

    @Override
    public void setTheNightStatefromUserseting() {
        UserSeting userSeting=(UserSeting)noteFragmentImp.getbaseapplication();
        noteFragmentImp.setInterfacestateonFragment(userSeting.getInterfaceState());
    }

    @Override
    public InterfaceState getIntegerfacefromUserseting() {
        UserSeting userSeting=(UserSeting)noteFragmentImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }

    @Override
    public InterfaceState getIntegerfacetoActivityfromUserseting() {
        UserSeting userSeting=(UserSeting)zhihuNoteActivityImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }
}
