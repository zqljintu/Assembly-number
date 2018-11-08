package com.zql.app_ji.Service;
import com.zql.app_ji.Bean.DetailZhihuNote;
import com.zql.app_ji.Bean.ZhihuNote;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GetZhihuNote {
    @GET("/api/4/news/latest")
    Observable<ZhihuNote> getZhihuNotes();

    @GET("/api/4/news/before/{date}")
    Observable<ZhihuNote> getDateZhihuNotes(@Path("date") String beforedate);

    @GET("/api/4/news/{id}")
    Observable<DetailZhihuNote>getDetailZhhuNote(@Path("id") String id);
}
