package com.lewish.start.diffutildemo.adapter;

import android.content.Context;
import android.os.Bundle;

import com.lewish.start.baseadapterlib.ViewHolder;
import com.lewish.start.baseadapterlib.recyclerview.CommonAdapter;
import com.lewish.start.diffutildemo.R;
import com.lewish.start.diffutildemo.RecyDiffCallback;
import com.lewish.start.diffutildemo.entity.Student;

import java.util.List;

/**
 * author: sundong
 * created at 2017/4/12 14:12
 */
public class RecyAdapter extends CommonAdapter<Student> {
    public RecyAdapter(Context context, int layoutId, List<Student> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Student student, int position) {
        holder.setText(R.id.mTvStdId, student.getStdId());
        holder.setText(R.id.mTvStdName, student.getStdName());
        holder.setText(R.id.mTvStdAge, student.getStdAge() + "");
        holder.setText(R.id.mTvPhoneNum, student.getStdPhoneNum());
        holder.setText(R.id.mTvGender, student.getStdGender().toString());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle payload = (Bundle) payloads.get(0);
            Student student = mDatas.get(position);
            for (String key : payload.keySet()) {
                switch (key) {
                    case RecyDiffCallback.KEY_STD_AGE:
                        holder.setText(R.id.mTvStdAge, payload.getString(RecyDiffCallback.KEY_STD_AGE));
                        break;
                    case RecyDiffCallback.KEY_STD_NAME:
                        holder.setText(R.id.mTvStdName, payload.getString(RecyDiffCallback.KEY_STD_NAME));
                        break;
                    case RecyDiffCallback.KEY_STD_PHONENUM:
                        holder.setText(R.id.mTvPhoneNum, payload.getString(RecyDiffCallback.KEY_STD_PHONENUM));
                        break;
                }
            }
        }
    }
}
