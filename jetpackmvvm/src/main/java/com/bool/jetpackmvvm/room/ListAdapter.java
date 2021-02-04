package com.bool.jetpackmvvm.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bool.jetpackmvvm.R;

import java.util.List;

public class ListAdapter  extends BaseAdapter {

    private Context context;
    private List<Student> mList;

    //声明构造函数
    public ListAdapter(Context context, List<Student> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setmList(List<Student> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_room,parent,false);
            viewHolder.tv_id = convertView.findViewById(R.id.tv_id);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_age = convertView.findViewById(R.id.tv_age);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_id.setText(mList.get(position).id+"");
        viewHolder.tv_name.setText(mList.get(position).name);
        viewHolder.tv_age.setText(mList.get(position).age);

        return convertView;
    }

    class ViewHolder{
        private TextView tv_id;
        private  TextView tv_name;
        private  TextView tv_age;
    }


}
