package com.example.egghttp.retrofit.interceptor;

import com.example.eggframe.LoginEvent;
import com.example.egghttp.EggLog.L;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class Login implements Interceptor {

    private final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        RequestBody requestBody = request.body();

        String body = null;

        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            body = buffer.readString(charset);
        }


        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        String rBody = null;


//        if (bodyText.length() != 0) {
//            JSONObject body = new JSONObject(bodyText);
//            Iterator<String> sIterator = body.keys();
//            while (sIterator.hasNext()) {
//                // 获得key
//                String key = sIterator.next();
//                String value = body.getString(key);
//                stringA += key + '=' + value + '&';
//            }
//
//        }


        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                e.printStackTrace();
            }
        }
        rBody = buffer.clone().readString(charset);
//        L.d("hci","rBody:"+rBody);
        if (rBody.length() != 0) {
            try {

//                L.d("hci", "request:" + request.url());

                String url = request.url() + "";
                if (url.contains("listOnlineOrder")) {
                    L.d("tse", "\nrBody:\n" + rBody);
                    L.d("tse", "!!!!!!!!!!!!!!!!!!!!!!!tse!!!!!!!!!!!!!!!!!!!!!!!");
                } else {


                    L.d("hcib", "\nrBody:\n" + rBody + "\n <<<====received " + url);
                }


                JSONObject bodyresponse = new JSONObject(rBody);
                int code = bodyresponse.getInt("code");
                String msg = bodyresponse.getString("msg");
//                L.d("hcia", "code:" + code);

                EventBus.getDefault().post(new LoginEvent(code, msg));

            } catch (JSONException e) {
                e.printStackTrace();
                EventBus.getDefault().post(new LoginEvent(-1, e + ""));

                L.d("hcia", "e:" + e);
            }

        }

//        L.d("hcia", "rBody:" + rBody);


        return response;
    }

}
