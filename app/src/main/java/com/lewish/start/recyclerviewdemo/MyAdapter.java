package com.lewish.start.recyclerviewdemo;

import android.content.Context;

import com.lewish.start.baseadapterlib.ViewHolder;
import com.lewish.start.baseadapterlib.recyclerview.CommonAdapter;

import java.util.List;

/**
 * author: sundong
 * created at 2017/4/11 16:02
 * 简写
 */
public class MyAdapter extends CommonAdapter<String> {
    public MyAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_title,s);
    }
}
