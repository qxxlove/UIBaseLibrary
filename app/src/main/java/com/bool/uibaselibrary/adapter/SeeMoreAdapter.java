package com.bool.uibaselibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bool.uibaselibrary.R;

import java.util.List;

/**
 * @Description: 作用描述
 * @Author: TianMingming
 * @CreateDate: 2020/6/18 22:44
 */
public class SeeMoreAdapter  extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final static int TYPE_NORMAL = 0;//正常条目
    private final static int TYPE_SEE_MORE = 1;//查看更多
    private final static int TYPE_HIDE = 2;//收起
    private List<String> mList;
    private boolean mOpen = false;//是否是展开状态

    public SeeMoreAdapter(List<String> mList) {
        this.mList = mList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        switch (i){
            case TYPE_NORMAL:
            case TYPE_HIDE:
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_recyclerview, viewGroup, false);
                return new SeeMoreViewHolder(view);
            case TYPE_SEE_MORE:
                View view1 = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_recyclerview_more, viewGroup, false);
                return new SeeMoreExpendViewHolder(view1);
            default:
        }

        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder seeMoreViewHolder, final int position) {
//        TextView textView = (TextView) seeMoreViewHolder.textView;
        if (getItemViewType(position) == TYPE_HIDE) {
            SeeMoreViewHolder seeMoreExpendViewHolder =
                    (SeeMoreViewHolder) seeMoreViewHolder;
            seeMoreExpendViewHolder.textView.setText("收起");
            seeMoreExpendViewHolder.rl_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOpen = false;
                    notifyDataSetChanged();
                }
            });
        } else if (getItemViewType(position) == TYPE_SEE_MORE) {
            SeeMoreExpendViewHolder seeMoreExpendViewHolder =
                    (SeeMoreExpendViewHolder) seeMoreViewHolder;
            seeMoreExpendViewHolder.textView.setText("查看更多");
            seeMoreExpendViewHolder.rl_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOpen = true;
                    notifyDataSetChanged();
                }
            });
        } else {
            SeeMoreViewHolder seeMoreExpendViewHolder =
                    (SeeMoreViewHolder) seeMoreViewHolder;
            seeMoreExpendViewHolder.textView.setText(mList.get(position));
            seeMoreExpendViewHolder.rl_item.setClickable(false);
            if (onItemClick!=null){
                seeMoreExpendViewHolder.rl_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick.onItemClick(position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
    /*    if (mList.size() <= 4) {
            return TYPE_NORMAL;
        }
        if (mOpen) {
            if (position == mList.size()) {
                return TYPE_HIDE;
            } else {
                return TYPE_NORMAL;
            }
        } else {
            if (position == 3) {
                return TYPE_SEE_MORE;
            } else {
                return TYPE_NORMAL;
            }
        }
*/

        if (mList.size() <= 15) {
            return TYPE_NORMAL;
        }
        if (mOpen) {
            if (position == mList.size()) {
                return TYPE_HIDE;
            } else {
                return TYPE_NORMAL;
            }
        } else {
            if (position == 14) {
                return TYPE_SEE_MORE;
            } else {
                return TYPE_NORMAL;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null || mList.size() == 0) {
            return 0;
        }
        if (mList.size() > 15) {
            //若现在是展开状态 条目数量需要+1 "收起"条目
            if (mOpen) {
                return mList.size() + 1;
            } else {
                return 15;
            }
        } else {
            return mList.size();
        }
    }

    class SeeMoreViewHolder extends RecyclerView.ViewHolder {
        TextView textView ;
        ImageView iv_item ;
        RelativeLayout rl_item ;


        public SeeMoreViewHolder( View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemView);
            iv_item = itemView.findViewById(R.id.iv_item);
            rl_item = itemView.findViewById(R.id.rl_item);
        }
    }


    class SeeMoreExpendViewHolder extends RecyclerView.ViewHolder {
        TextView textView ;
        RelativeLayout rl_item ;


        public SeeMoreExpendViewHolder( View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemView);
            rl_item = itemView.findViewById(R.id.rl_item);
        }
    }


    public interface OnItemClick{
        void onItemClick(int position);
    }
    private OnItemClick onItemClick;
    public void setOnITEMClickListener(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

}
