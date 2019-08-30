package com.zql.app_ji.Service;

import java.util.concurrent.TimeUnit;
import java.util.logging.LoggingPermission;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static String BASE_URL = "https://api.github.com";

    private static RetrofitManager sInstance;

    private Retrofit mRetrofit;

    public static RetrofitManager getInstance(String url){
        if (null == sInstance){
            synchronized (RetrofitManager.class){
                if (null == sInstance){
                    BASE_URL = url;
                    sInstance = new RetrofitManager();
                }
            }
        }
        return sInstance;
    }

    public void init(){
        if (mRetrofit == null){
            //初始化一个OKhttpClient
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(30000, TimeUnit.MILLISECONDS)
                    .readTimeout(30000,TimeUnit.MILLISECONDS)
                    .writeTimeout(3000,TimeUnit.MILLISECONDS);
            builder.addInterceptor(new LoggingInterceptor());
            OkHttpClient okHttpClient = builder.build();

            //使用该OkHttpClient创建一个Retrofit对象
            mRetrofit = new Retrofit.Builder()
                    //添加Gson数据格式转换器支持
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加RxJava语言支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //指定网络请求client
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .build();
        }
    }

    public Retrofit getmRetrofit(){
        if (null == mRetrofit){
            init();
        }
        return mRetrofit;
    }
}
