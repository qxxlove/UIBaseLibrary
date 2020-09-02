package com.bool.uibaselibrary.utils;

import android.content.Context;

public class Dp2PxUtil  {


    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
