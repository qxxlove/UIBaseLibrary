package com.bool.uibaselibrary.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bool.uibaselibrary.R;
import com.bool.uibaselibrary.adapter.SimpleAdapter;
import com.bool.uibaselibrary.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 该布局由RecylerView列表和一个TextView组成，其中RecylerView实现了NestedScrollingChild接口，
 * 所以TextView监听RecylerView的滑动状态。
 *   开始向上滑动列表时TextView和列表整体上移，直到TextView全部隐藏停止，再次上滑则列表内容上移。
 *   之后连续下滑列表当其第一个item全部显示时列表滑动停止，再次下滑列表时TextView跟随列表整体下移，
 *   直到TextView全部显示
 *
  */


public class SimpleBehaviorTwoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_behavior_two);
        initView();
        iniData();
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
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.my_list);
    }


}