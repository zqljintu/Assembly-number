package com.zql.app_ji.Service;

import com.zql.app_ji.Bean.WanArticle;
import com.zql.app_ji.Bean.WanProject;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GetWanAndroid {
    @GET("/article/list/{page}/json")
    Observable<WanArticle>getWanArticle(@Path("page") String page);

    @GET("/article/listproject/{page}/json")
    Observable<WanProject>getWanProject(@Path("page") String page);
}
