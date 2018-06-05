package com.example.egghttp.retrofit.interceptor;

import android.text.TextUtils;

import com.pixplicity.easyprefs.library.Prefs;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class Token implements Interceptor {


    public static String getToken() {


        return Prefs.getString("token", token);


    }

    public static void setToken(String token) {
        Prefs.putString("token", token);

//        L.d("hcia", "Prefs.getAll():" + Prefs.getAll());

        Token.token = token;
    }

    public static String token = "";
    public static String merchantId = "";

    public static boolean isLogin() {


        if (TextUtils.isEmpty(Token.token))
            return false;

        return Token.token.length() > 1;
    }


    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();


        Request request;
        if (getToken().length() > 1) {
            request = original.newBuilder()
//                .header("Content-Type", "application/json")
                    .header("Token", token)
                    .method(original.method(), original.body())
                    .build();
        } else {
            request = original.newBuilder()
//                .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();
        }

        return chain.proceed(request);
    }
}
