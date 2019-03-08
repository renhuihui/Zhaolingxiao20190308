package com.bwei.zhaolingxiao20190308.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils<T> {
    private HttpUtils (){}
    public static class HttpUtilsIns{
        private static HttpUtils httpUtils = new HttpUtils();
    }
    public static HttpUtils getInstance(){
        return HttpUtilsIns.httpUtils;
    }

    //handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            T t = (T) msg.obj;
            mCallBackData.Success(t);
        }
    };
    //get请求
    private CallBackData mCallBackData;
    public void getData(String url, final Class<T> tClass, CallBackData callBackData){
        this.mCallBackData = callBackData;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(new Intercepters())
                .build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                T t = gson.fromJson(string, tClass);
                Message message = handler.obtainMessage();
                message.obj = t;
                handler.sendMessage(message);
            }
        });
    }
    //post请求
    public void postData(String url, final Class<T> tClass, CallBackData callBackData){
        this.mCallBackData = callBackData;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(new Intercepters())
                .build();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody build = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(build)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                T t = gson.fromJson(string, tClass);
                Message message = handler.obtainMessage();
                message.obj = t;
                handler.sendMessage(message);
            }
        });
    }

    public interface CallBackData<D>{
        public void Success(D d);
        public void Fail(String msg);
    }
}
