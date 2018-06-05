package com.example.egghttp.retrofit.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class CommonHeader implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();


        Request request = original.newBuilder()
//                .header("Content-Type", "application/json")
                .header("AppId", "pos")
                .header("Timestamp", System.currentTimeMillis() / 1000 + "")
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }
}
