package com.bool.jetpackmvvm.viewmodel.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


/**
 *  如果你希望在VIewModel中使用Context对象 ( 例如，为了查找系统服务)，
 *  可以使用AndroidViewModel类，它继承自ViewMoel,并接收Application作为Context.。
 *  因为Application会扩展Context,代码如下
 */

public class AnViewModel extends AndroidViewModel {


    public AnViewModel(@NonNull Application application) {
        super(application);
    }


}
