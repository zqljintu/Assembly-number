package com.zql.app_ji.Model;

import com.zql.app_ji.Bean.GankImage;
import com.zql.app_ji.Prestener.PrestenerHappyFragmentImp;
import com.zql.app_ji.Service.GetGankImage;
import com.zql.app_ji.Service.RetrofitManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetGankgsonModel implements GetGankgsonModelImp{
    private PrestenerHappyFragmentImp prestenerHappyFragmentImp;
    private String baseUrl="http://gank.io";

    public GetGankgsonModel(PrestenerHappyFragmentImp mprestenerHappyFragmentImp){
        this.prestenerHappyFragmentImp=mprestenerHappyFragmentImp;
    }

    @Override
    public void getGankImagesfremGankAPI(int page) {
        Retrofit retrofit= RetrofitManager.getInstance(baseUrl).getmRetrofit();
        GetGankImage getGankImage=retrofit.create(GetGankImage.class);
        getGankImage.getGankImages(String.valueOf(page))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GankImage>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GankImage gankImage) {
                        prestenerHappyFragmentImp.setGankImagesOnRecyclerView(gankImage);
                    }
                });
    }

    @Override
    public void getTestGank() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetGankImage getGankImage=retrofit.create(GetGankImage.class);
        getGankImage.getTestGank()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GankImage>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GankImage gankImage) {
                        prestenerHappyFragmentImp.setGankImagesOnRecyclerView(gankImage);
                    }
                });
    }

    @Override
    public void getErrorMessage(int ErrorMessage) {

    }
}
