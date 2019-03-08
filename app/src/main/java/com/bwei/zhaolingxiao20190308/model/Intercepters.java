package com.bwei.zhaolingxiao20190308.model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class Intercepters implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.e("time","time"+System.nanoTime());
        Headers headers = request.headers();
        Response proceed = chain.proceed(request);
        return proceed;
    }
}
