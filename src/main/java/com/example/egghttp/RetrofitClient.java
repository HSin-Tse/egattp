package com.example.egghttp;

import com.example.egghttp.retrofit.interceptor.CheckSign;
import com.example.egghttp.retrofit.interceptor.CommonHeader;
import com.example.egghttp.retrofit.interceptor.GeneraterSign;
import com.example.egghttp.retrofit.interceptor.Login;
import com.example.egghttp.retrofit.interceptor.Token;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
//    private static String domain = "https://api.sandan.store/";
    private static String domain = "http://api-test.sandan.store/";//test

    public static Retrofit getClient() {
        if (retrofit == null) {


            final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor( new CommonHeader());
            httpClient.addInterceptor(new Token());
            httpClient.addInterceptor(new GeneraterSign());
            httpClient.addInterceptor(new Login());

            CheckSign interceptor = new CheckSign();
            interceptor.setLevel(CheckSign.Level.BODY);
            httpClient.addInterceptor(interceptor).build();

            OkHttpClient client = httpClient.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(domain)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();


        }
        return retrofit;
    }


}
