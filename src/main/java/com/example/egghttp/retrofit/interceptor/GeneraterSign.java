package com.example.egghttp.retrofit.interceptor;

import com.example.egghttp.EggLog.L;
import com.example.egghttp.retrofit.encrypt.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okio.Buffer;

public class GeneraterSign implements Interceptor {
    private String gsign;

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        if (original.method().equals("POST")) {
            String stringA = "";

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = original.body().contentType();
            Buffer buffer = new Buffer();
            original.body().writeTo(buffer);
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String bodyText = buffer.readString(charset);


            try {

                if (bodyText.length() != 0) {

                    String url = original.url() + "";
                    if (url.contains("listOnlineOrder")) {
                        L.d("tse", "!!!!!!!!!!!!!!!!!!!!!!!se=========tse!!!!!!!!!!!!!!!!!!!!!!!>>>>" + original.url());
                        L.d("tse", "\noriginal.url():\n" + original.url());
                        L.d("tse", "\nbodyText:\n" + bodyText);
                    } else {
                        L.d("hcib", "Send:===>>>>>" + original.url() + "\nsend body:\n" + bodyText + "\n");
//                        L.d("hcib", "\noriginal.url():\n" + original.url());
//                        L.d("hcib", "\nsend body:\n" + bodyText + "\n");
                    }

                    JSONObject body = new JSONObject(bodyText);
                    Iterator<String> sIterator = body.keys();
                    while (sIterator.hasNext()) {
                        // 获得key
                        String key = sIterator.next();
                        String value = body.getString(key);
                        stringA += key + '=' + value + '&';
                    }

                }

//                        Map<String, String> resultMap = sortMapByKey(map);    //按Key进行排序

//                L.d("gsign","stringA:"+stringA);

                gsign = MD5.md5(stringA + original.headers().get("Timestamp") + "pos-secret").toUpperCase();
//                L.d("gsign","gsign:"+gsign);


            } catch (JSONException e) {
                L.d("hcia", "e:" + e);
                e.printStackTrace();
            }

        } else if (original.method().equals("GET")) {
            String stringA = "";

//            L.d("hcia", "======Start  GET====:");
//            L.d("hcia", "4.original query:" + original.url().query());

            stringA = original.url().query() + '&';
            gsign = MD5.md5(stringA + original.headers().get("Timestamp") + "pos-secret").toUpperCase();


        }

        Request request = original.newBuilder()
//                                                  .header("Content-Type", "application/json")
                .header("Sign", gsign)
//                .header("AppId", "pos")
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }
}
