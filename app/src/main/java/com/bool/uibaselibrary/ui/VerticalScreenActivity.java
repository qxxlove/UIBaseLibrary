package com.bool.uibaselibrary.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bool.uibaselibrary.R;


/***
 * 竖屏
 *
 *
 * 横竖屏切换时会先销毁相应activity，后在创建横屏或竖屏的activity，
 * 因此生命周期为onPause-> onStop-> onDestory-> onCreate->onStart->onResume;
 */


public class VerticalScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate","VerticalScreenActivity  onCreate");
        setContentView(R.layout.activity_vertical_screen);
        findViewById(R.id.tv_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerticalScreenActivity.this,
                        HorizontalScreenActivity.class));

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
         Log.e("onStart","VerticalScreenActivity  onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume","VerticalScreenActivity  onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause","VerticalScreenActivity  onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop","VerticalScreenActivity  onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","VerticalScreenActivity  onDestroy");
    }
}