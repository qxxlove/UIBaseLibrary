package com.bool.jetpackmvvm.viewmodel.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bool.jetpackmvvm.R;


/**
 * 这两个Fragment都会检索包含它们的Activity.这样，
 * 当这两个Fragment各自获取ViewModelProvider时，
 * 它们都会收到相同的SharedViewModel实例(其范围限定为该Activity)
 */


public class LeftAndRightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_and_right);
    }
}