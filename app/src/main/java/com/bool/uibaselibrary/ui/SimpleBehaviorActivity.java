package com.bool.uibaselibrary.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bool.uibaselibrary.R;
import com.bool.uibaselibrary.adapter.SimpleAdapter;
import com.bool.uibaselibrary.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *  Toolbar之所以可以滚动隐藏\显示，是通过如下属性实现的：app:layout_scrollFlags="scroll|enterAlways"
 *   属性： scroll：需要滚动出屏幕的view需要设置该flag， 没有设置则view将被固定在屏幕顶部。
 *         enterAlways: 使用该flag，则向下的滚动会使view变为可见状态。
 *
 *   折叠效果：
 *         exitUntilCollapsed: 滚动退出屏幕，最后折叠在顶端
 *
 *         折叠后Toolbar可以固定在顶部是因为使用了app:layout_collapseMode="pin"属性，
 *         属性值除了pin还有一个parallax：
 *             pin：固定模式，在折叠的时候最后固定在顶端
 *             parallax：视差模式，在折叠时会有个视差折叠的效果
 *
 */



public class SimpleBehaviorActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_behavior);
        initView();
        iniData();
        getBaseContext();
        getApplication().getApplicationContext();
        getApplicationContext();


    }

    private void iniData() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));
        list.add(new Student("哈哈哈",23));

        simpleAdapter = new SimpleAdapter(R.layout.item_recyclerview_simple,list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(simpleAdapter);

        mRecyclerView.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                Log.e("onCreate","哈哈哈");
            }
        });
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.my_list);
    }
}