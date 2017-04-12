package com.lewish.start.recyclerviewdemo;

import android.content.Context;
import android.view.View;

import com.lewish.start.baseadapterlib.ViewHolder;
import com.lewish.start.baseadapterlib.recyclerview.CommonAdapter;

import java.util.List;

/**
 * author: sundong
 * created at 2017/4/12 10:28
 */

public class SimplifiedRecyViewdapter2 extends CommonAdapter<String> {
    private OnIconClickListener mOnIconClickListener;
    public SimplifiedRecyViewdapter2(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, String s, final int position) {
        holder.setText(R.id.tv_title,s);
        holder.setOnClickListener(R.id.iv_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnIconClickListener!=null) {
                    mOnIconClickListener.onItemClick(v,position);
                }
            }
        });
    }

    public interface OnIconClickListener{
        void onItemClick(View view,int position);
    }

    public void setmOnIconClickListener(OnIconClickListener mOnIconClickListener) {
        this.mOnIconClickListener = mOnIconClickListener;
    }
}
