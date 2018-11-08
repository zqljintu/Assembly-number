package com.zql.app_ji.Service;

import com.zql.app_ji.Bean.GankImage;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GetGankImage {
    @GET("/api/data/福利/20/{page}")
    Observable<GankImage>getGankImages(@Path("page") String page);

    @GET("/api/data/%E7%A6%8F%E5%88%A9/30/1")
    Observable<GankImage>getTestGank();
}
