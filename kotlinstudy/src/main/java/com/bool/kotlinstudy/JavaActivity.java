package com.bool.kotlinstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型
 * @author  TianMingming
 * @date  2020/1/7 15:57
 */

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        initData();
    }

    private void initData() {
        List<String> strs = new ArrayList<String>();
        strs.add("0");
        strs.add("1");
        /**通配符上界*/
        List<? extends Object> objs = strs;
        objs.get(0); // 可以获取
        //objs.add(1); // 但是添加的时候报错


        List<String> strs1 = new ArrayList<String>();
        strs1.add("0");
        /**t通配符下界*/
        List<? super String> objs1 = strs;
        objs1.add("1");
        objs1.set(0, "2");
        // 得到Object类型，如果想要String 还需要强转
        Object s = objs.get(0);

        
    }


}
