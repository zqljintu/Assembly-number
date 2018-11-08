package com.zql.app_ji.Service;

import com.zql.app_ji.Bean.DetailDoubanMovie;
import com.zql.app_ji.Bean.DoubanMovie;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface GetDoubanMovie {
    @GET("/v2/movie/in_theaters")
    Observable<DoubanMovie>getDoubanMovies();

    @GET("/v2/movie/top250")
    Observable<DoubanMovie>getTopDoubanMovies();

    //http://api.douban.com/v2/movie/top250?start=0&count=25
    @GET("/v2/movie/top250")
    Observable<DoubanMovie>getMoreTopDoubanMovie(@Query("start") String start,@Query("count") String count);
    //http://api.douban.com/v2/movie/subject/26425063
    @GET("/v2/movie/subject/{id}")
    Observable<DetailDoubanMovie>getDetailDoubanMovie(@Path("id") String id);
}
