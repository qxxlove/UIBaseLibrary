package com.bool.rajavaandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bool.rajavaandroid.bean.MobileAddress;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Rxjava进阶 https://www.jianshu.com/p/061f23ecc19a
 * @author  TianMingming
 * @date  2019/12/23 18:49
 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRxJavaMap();
    }



    private void initRxJavaMap() {
        Observable<MobileAddress> sdf = Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
                Request.Builder builder = new Request.Builder()
                        .url("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
                        .get();
                Request request = builder.build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                e.onNext(response);
            }
        })
                .map(new Function<Response, MobileAddress>() {
                    @Override
                    public MobileAddress apply(@NonNull Response response) throws Exception {
                        if (response.isSuccessful()) {
                            ResponseBody body = response.body();
                            if (body != null) {
                                Log.e("sdf", "map:转换前:" + response.body());
                                return new Gson().fromJson(body.string(), MobileAddress.class);
                            }
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(@NonNull MobileAddress s) throws Exception {
                        Log.e("", "doOnNext: 保存成功：" + s.toString() + "\n");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        sdf.subscribe(new Consumer<MobileAddress>() {
            @Override
            public void accept(MobileAddress mobileAddress) throws Exception {
                Log.e("", "成功:" + mobileAddress.toString() + "\n");
                Log.e("initRxJavaMap", "Observable thread is : " + Thread.currentThread().getName());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("", "失败：" + throwable.getMessage() + "\n");
            }
        });


    }



}