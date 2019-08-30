package com.zql.app_ji.Model;


import com.zql.app_ji.Bean.DetailZhihuNote;
import com.zql.app_ji.Bean.ZhihuNote;
import com.zql.app_ji.Prestener.PrestenerNoteFragment;
import com.zql.app_ji.Prestener.PrestenerNoteFragmentImp;
import com.zql.app_ji.Service.GetZhihuNote;
import com.zql.app_ji.Service.RetrofitManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetZhihugsonModel implements GetZhihugsonModelImp {
    private String baseUrl="http://news-at.zhihu.com";
    private PrestenerNoteFragmentImp prestenerNoteFragmentImp;
    private GetZhihuNote getZhihuNote;

    public GetZhihugsonModel(PrestenerNoteFragment mprestenerNoteFragmentImp){
        this.prestenerNoteFragmentImp=mprestenerNoteFragmentImp;
        this.getZhihuNote = RetrofitManager.getInstance(baseUrl)
                .getmRetrofit()
                .create(GetZhihuNote.class);
    }

    /**
     * 利用Rx_Android和Retrofit进行网路Get请求
     * 注释起来的是只利用Retrofit进行网络Get请求
     */
    @Override
    public void getZhihuNotefromZhihuAPI() {
        getZhihuNote.getZhihuNotes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ZhihuNote>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhihuNote mzhihuNote) {
                        prestenerNoteFragmentImp.setZhihuNotetoRecyclerView(mzhihuNote);
                    }
                });
       /* Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//设计到json到实体的转变
                .build();
        GetZhihuNote getZhihuNote=retrofit.create(GetZhihuNote.class);
        Call<ZhihuNote> call=getZhihuNote.getZhihuNotes();
        call.enqueue(new Callback<ZhihuNote>() {
            @Override
            public void onResponse(Call<ZhihuNote> call, Response<ZhihuNote> response) {
                prestenerNoteFragmentImp.setZhihuNotetoRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<ZhihuNote> call, Throwable t) {

            }
        });*/
    }

    @Override
    public void getBeforeDateZhihuNotefromZhihuAPI(String beforedate) {
        getZhihuNote.getDateZhihuNotes(beforedate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ZhihuNote>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhihuNote mzhihuNote) {
                        prestenerNoteFragmentImp.setZhihuNotetoRecyclerView(mzhihuNote);
                    }
                });
    }

    @Override
    public void getDetailZhihuNotefromZhihuAPI(int id) {
        getZhihuNote.getDetailZhhuNote(String.valueOf(id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DetailZhihuNote>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetailZhihuNote detailZhihuNote) {
                        prestenerNoteFragmentImp.setDetailZhihuNotetoActivity(detailZhihuNote);
                    }
                });
    }
}
