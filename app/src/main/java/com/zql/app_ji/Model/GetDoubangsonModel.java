package com.zql.app_ji.Model;

import com.zql.app_ji.Bean.DetailDoubanMovie;
import com.zql.app_ji.Bean.DoubanMovie;
import com.zql.app_ji.Prestener.PrestenerCodeFragment;
import com.zql.app_ji.Prestener.PrestenerMovieFragmentImp;
import com.zql.app_ji.Service.GetDoubanMovie;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetDoubangsonModel implements GetDoubangsonModelImp{
    private PrestenerMovieFragmentImp prestenerMovieFragmentImp;
    private String baseUrl="http://api.douban.com";
    public GetDoubangsonModel(PrestenerMovieFragmentImp mprestenerMovieFragmentImp){
        this.prestenerMovieFragmentImp=mprestenerMovieFragmentImp;
    }

    @Override
    public void getMoviefromDoubanAPI() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetDoubanMovie getDoubanMovie=retrofit.create(GetDoubanMovie.class);
        getDoubanMovie.getDoubanMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DoubanMovie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoubanMovie mdoubanMovie) {
                        prestenerMovieFragmentImp.setDoubanMovietoRecyclerView(mdoubanMovie);
                    }
                });
    }

    @Override
    public void getMovie_top_fromDoubanAPI() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetDoubanMovie getDoubanMovie=retrofit.create(GetDoubanMovie.class);
        getDoubanMovie.getTopDoubanMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DoubanMovie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoubanMovie mdoubanMovie) {
                        prestenerMovieFragmentImp.setDoubantopMovietoRecyclerView(mdoubanMovie);
                    }
                });
    }

    @Override
    public void getMoreMovie_top_fromDoubanAPI(int start, int count) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetDoubanMovie getDoubanMovie=retrofit.create(GetDoubanMovie.class);
        getDoubanMovie.getMoreTopDoubanMovie(String.valueOf(start),String.valueOf(count))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DoubanMovie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoubanMovie mdoubanMovie) {
                        prestenerMovieFragmentImp.setDoubantopMovietoRecyclerView(mdoubanMovie);
                    }
                });
    }

    @Override
    public void getDetailDoubanMoviefromAPI(String id) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetDoubanMovie getDoubanMovie=retrofit.create(GetDoubanMovie.class);
        getDoubanMovie.getDetailDoubanMovie(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DetailDoubanMovie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetailDoubanMovie detailDoubanMovie) {
                        prestenerMovieFragmentImp.setDetailDoubanMovietoActivity(detailDoubanMovie);
                    }
                });
    }

    @Override
    public void getErrorMessage() {
        prestenerMovieFragmentImp.setErrorMessageOnFragment();
    }
}
