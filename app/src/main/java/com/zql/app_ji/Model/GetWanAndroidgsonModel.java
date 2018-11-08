package com.zql.app_ji.Model;

import com.zql.app_ji.Bean.WanArticle;
import com.zql.app_ji.Bean.WanProject;
import com.zql.app_ji.Prestener.PrestenerCodeFragmentImp;
import com.zql.app_ji.Service.GetWanAndroid;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetWanAndroidgsonModel implements GetWanAndroidgsonModelImp{
    public PrestenerCodeFragmentImp prestenerCodeFragmentImp;
    String baseUrl="http://www.wanandroid.com";

    public GetWanAndroidgsonModel(PrestenerCodeFragmentImp mprestenerCodeFragmentImp){
        this.prestenerCodeFragmentImp=mprestenerCodeFragmentImp;
    }

    @Override
    public void getArticlesfromWanAPI(int page) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetWanAndroid getWanAndroid=retrofit.create(GetWanAndroid.class);
        getWanAndroid.getWanArticle(String.valueOf(page))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<WanArticle>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WanArticle wanArticle) {
                        prestenerCodeFragmentImp.setWanArticleonArticleRecyclerView(wanArticle);
                    }
                });
    }

    @Override
    public void getProjectsfromWanAPI(int page) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetWanAndroid getWanAndroid=retrofit.create(GetWanAndroid.class);
        getWanAndroid.getWanProject(String.valueOf(page))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<WanProject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WanProject wanProject) {
                        prestenerCodeFragmentImp.setWanProjectonArticleRecyclerView(wanProject);
                    }
                });
    }

    @Override
    public void getErrorMessagefromWanAPI() {

    }
}
