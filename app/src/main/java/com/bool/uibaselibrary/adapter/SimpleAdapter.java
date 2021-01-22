package com.bool.uibaselibrary.adapter;

import androidx.annotation.Nullable;

import com.bool.uibaselibrary.R;
import com.bool.uibaselibrary.bean.Student;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author xiaohui
 * @date 2020/07/20
 * @description
 */
public class SimpleAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {


    public SimpleAdapter(int layoutResId, @Nullable List<Student> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Student item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_age, item.getAge()+"");

    }
}
