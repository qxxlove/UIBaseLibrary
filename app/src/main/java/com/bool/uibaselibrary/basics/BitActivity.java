package com.bool.uibaselibrary.basics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bool.uibaselibrary.R;

/**
 * 位运算
 * https://mp.weixin.qq.com/s/0haD2epzqBXRuMbXMKP_xQ
 * @author  TianMingming
 * @date  2019/12/24 11:40
 */

public class BitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit);
        initBit();
    }

    /**
     * 位运算
     */
    private void initBit() {
        Log.e("initBit", Integer.toHexString(0x7fffffff >> 4));
        Log.e("initBit", Integer.toHexString(0x8fffffff >> 4));
        Log.e("initBit", Integer.toHexString(0xffffffff >> 4));
        Log.e("initBit", Integer.toHexString(0x0fffffff >> 4));

        Log.e("initBit", Integer.toHexString(0x7fffffff << 4));
        Log.e("initBit", Integer.toHexString(0x8fffffff << 4));
        Log.e("initBit", Integer.toHexString(0xffffffff << 4));
        Log.e("initBit", Integer.toHexString(0x0fffffff << 4));

        Log.e("initBit", Integer.toHexString(0x7fffffff >>> 4));
        Log.e("initBit", Integer.toHexString(0x8fffffff >>> 4));
        Log.e("initBit", Integer.toHexString(0xffffffff >>> 4));
        Log.e("initBit", Integer.toHexString(0x0fffffff >>> 4));


    }
}
