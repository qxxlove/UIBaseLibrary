package com.bool.uibaselibrary.basics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bool.uibaselibrary.R;
import com.bool.uibaselibrary.bean.CommonBean;
import com.bool.uibaselibrary.weight.CustomViewPager;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.util.ArrayList;
import java.util.List;

/**
 *   ViewGroup的职能为：给childView计算出建议的宽和高和测量模式 ；决定childView的位置；
 *   为什么只是建议的宽和高，而不是直接确定呢，别忘了childView宽和高可以设置为wrap_content，
 *   这样只有childView才能计算出自己的宽和高。
 *
 *   View的职责，根据测量模式和ViewGroup给出的建议的宽和高，计算出自己的宽和高；
 *   同时还有个更重要的职责是：在ViewGroup为其指定的区域内绘制自己的形态。
 *
 *   LayoutParams
 *   布局参数的意义： 常用的layout_width,layout_height ,layout_gravity ，centerInParent ... 都是布局参数
 *
 *
 *
 */

public class CustomViewActivity extends AppCompatActivity {


    String v =  "{\"code\":\"500\",\"message\":\"操作正常\",\"success\":false}";
    String s  = "{\"code\":\"500\",\"message\":\"操作异常\",\"success\":false}";

    private CustomViewPager myviewpager;

    private LinearLayout llPointList;
    private List<Integer> mData = new ArrayList<>();
    private LinearLayout.LayoutParams params;
    private View viewDot;
    private int dotDistance = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        initGson();





        myviewpager = findViewById(R.id.myviewpager);
        viewDot = findViewById(R.id.view_dot);
        llPointList = findViewById(R.id.ll_point_list);
        initCirclePoint();

        myviewpager.setOnPageScrollListener(new CustomViewPager.OnPageScrollListener() {
            @Override
            public void onPageScrolled(float offsetPercent, int position) {
                //效果一：滑动页面过程中小圆点跟随移动
                //offsetPercent:0-0.5-1-1.5-...
                float leftMargin = offsetPercent * dotDistance;
                //如果使用系统的ViewPager也可以使用这种方法添加指示器，只需修改成如下即可：
                //float leftMargin = positionOffset * dotDistance + position * dotDistance;
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) viewDot.getLayoutParams();
                params.leftMargin = (int) leftMargin; //滑动后更新距离
//                Elog.e("Offset", "params.leftMargin=" + params.leftMargin);
                viewDot.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                //效果二：滑动页面过程中小圆点不跟随移动，到某个指定位置才切换小圆点
                Log.e("TAG", "position=" + position);
//                float leftMargin = position * dotDistance;
//                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) viewDot.getLayoutParams();
//                params.leftMargin = (int) leftMargin; //滑动后更新距离
////                Elog.e("Offset", "params.leftMargin=" + params.leftMargin);
//                viewDot.setLayoutParams(params);

            }
        });

    }

    private void initGson() {
        Log.e("onCreate","结果："+"\"");
        Log.e("onCreate","结果1："+v);
        Gson gson = new Gson();
        try {
            CommonBean commonBean = gson.fromJson(v, CommonBean.class);
            if (commonBean != null){
                Toast.makeText(this,commonBean.getMessage(),Toast.LENGTH_LONG).show();
            }
        }catch (JsonParseException j){
            Toast.makeText(this,"解析异常",Toast.LENGTH_LONG).show();
        }

    }


    private void initCirclePoint() {
        for (int i = 0; i < 5; i++) {
            mData.add(i);
        }
        for (int i = 0; i < mData.size(); i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.bg_point_selector);
            params = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                params.leftMargin = 10;
            }
            point.setEnabled(false);
            point.setLayoutParams(params);
            llPointList.addView(point);
        }
    }
}