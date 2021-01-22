package com.bool.jetpackmvvm.navigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bool.jetpackmvvm.R;

/**
 *
 * 参考 https://blog.csdn.net/qq_43404873/article/details/109506512
 *
 *
 *
 */


public class NavHostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_host);
    }
}