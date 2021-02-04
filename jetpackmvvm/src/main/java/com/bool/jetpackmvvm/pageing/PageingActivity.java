package com.bool.jetpackmvvm.pageing;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bool.jetpackmvvm.R;


/**
 *
 * Page 工作原理
 * 1.在RecyclerView的滑动过程中，会触发PagedListAdapter类中的onBindViewHolder()方法。数据与RecycleView中Item布局的UI控件正是在该方法中进行绑定的。
 *
 * 2.当RecyclerView滑动到底部时，在onBindViewHolder()方法中所调用的getItem()方法会通知PagedList,当前需要载入更多数据。
 *
 * 3.接着，PagedList会根据PageList.Config中的配置通知DataSource执行具体的数据获取工作。
 *
 * 4.DataSource从网络/本地数据库取得数据后，交给PagedList,PagedList将持有这些数据。
 *
 * 5.PagedList将数据交给PagedListAdapter中的DiffUtil进行比对和处理。
 *
 * 6.数据在经过处理后，交由RecyclerView进行展示.
 *
 *
 *  核心类
 *  1.PagedListAdapter:
 *
 *     适配器，RecyclerView的适配器，通过分析数据是否发生了变化，负责处理UI展示的逻辑(增加/删除/替换等)
 *
 *  2.PageList:
 *
 *     负责通知DataSource何时获取数据，以及如何获取数据。
 *
 *    例如，何时加载第一页/下一页、第一页加载的数量、提前多少条数据开始执行预加载等。需要注意的是，从DataSource获取的数据将存储在PagedList中
 *
 *  3.DataSource
 *
 *     数据源，执行具体的数据载入工作。注意：数据的载入需要在工作线程中执行。
 *     数据可以来自网络，也可以来自本地数据库，如Room.根据分页机制的不同，Paging为我们提供了3种DataSource。
 *
 *
 */


public class PageingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageing);
    }
}