package com.bool.uibaselibrary.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;


/**
 * 测量模式
 *
 * 在布局文件中
 * 1. 设置自定义view的宽高为100dp*100dp
 * 2. 设置自定义view的宽高为match_parent
 * 3. 设置自定义view的宽高为wrap_content（实际根据我们的测量方法设置的是宽高为200dp*300dp）
 * 4. 如果没有重写自定义view中的onMeasure方法，并且设置宽高为wrap_content的时候，也是填充父布局，
 *    原因是这种情况下view的specSize是parentSize，而parentSize是父容器中目前可以使用的大小，
 *    也就是父容器当前剩余的空间大小，具体可以查看ViewGroup中的getChildMeasureSpec方法
 *
 *
 * */

public class CustomView extends View {

    private  Context mContext;


    public CustomView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       /* int resultWidth;
        int resultHeight;

        int specWMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWSize = MeasureSpec.getSize(widthMeasureSpec);
        resultWidth=myMeasure(specWMode,specWSize, Dp2PxUtil.dip2px(mContext,200));

        int specHMode = MeasureSpec.getMode(heightMeasureSpec);
        int specHSize = MeasureSpec.getSize(heightMeasureSpec);

        resultHeight=myMeasure(specHMode,specHSize,Dp2PxUtil.dip2px(mContext,300));
        setMeasuredDimension(resultWidth,resultHeight);
*/
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        Log.e("onMeasure", "300dp:" + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300,
                getResources().getDisplayMetrics()));
        Log.e("onMeasure", "AT_MOST:" + MeasureSpec.AT_MOST);
        Log.e("onMeasure", "size:" + size);
        Log.e("onMeasure", "mode:" + mode);

        /**外层的ViewGroup的高度是300dp，里面的View高度是Wrap_content，
         * 那么对应的是表格中的第四行、第二列，那么传给里面的view的size=300dp，mode= AT_MOST，是不是这样呢，
         * 咋们输出log来验证下:     但是600 是做什么的？   600是转换后的px。
         * 2020-07-09 16:49:00.585 13836-13836/com.bool.uibaselibrary E/onMeasure: 300dp:600.0
         * 2020-07-09 16:49:00.585 13836-13836/com.bool.uibaselibrary E/onMeasure: AT_MOST:-2147483648
         * 2020-07-09 16:49:00.585 13836-13836/com.bool.uibaselibrary E/onMeasure: size:600
         * 2020-07-09 16:49:00.585 13836-13836/com.bool.uibaselibrary E/onMeasure: mode:-2147483648
         * 2020-07-09 16:49:00.606 13836-13836/com.bool.uibaselibrary E/onMeasure: 300dp:600.0
         * 2020-07-09 16:49:00.606 13836-13836/com.bool.uibaselibrary E/onMeasure: AT_MOST:-2147483648
         * 2020-07-09 16:49:00.606 13836-13836/com.bool.uibaselibrary E/onMeasure: size:600
         * 2020-07-09 16:49:00.606 13836-13836/com.bool.uibaselibrary E/onMeasure: mode:-2147483648
         *
         */

        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        Log.e("onMeasure", "size:" + sizeW);
        Log.e("onMeasure", "AT_MOST:" + MeasureSpec.AT_MOST);
        Log.e("onMeasure", "size:" + sizeW);
        Log.e("onMeasure", "mode:" + modeW);

    }

    /**
     *
     * @param specMode 测量模式
     * @param specSize 测量大小
     * @param result  在非精确测量模式中用来约束的大小
     * @return
     */
    private  int myMeasure(int specMode,int specSize,int result){
        if(specMode==MeasureSpec.EXACTLY){
            result=specSize;
        }else if(specMode==MeasureSpec.AT_MOST){
            result=Math.min(specSize,result);
        }else {

        }
        return result;
    }
}
