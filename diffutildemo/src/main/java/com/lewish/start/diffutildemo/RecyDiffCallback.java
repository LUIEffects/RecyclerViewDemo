package com.lewish.start.diffutildemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.lewish.start.diffutildemo.entity.Student;

import java.util.List;

/**
 * author: sundong
 * created at 2017/4/12 11:38
 */
public class RecyDiffCallback extends DiffUtil.Callback {
    private List<Student> mOldData;
    private List<Student> mNewData;
    public static final String KEY_STD_NAME = "KEY_STD_NAME";
    public static final String KEY_STD_AGE = "KEY_STD_AGE";
    public static final String KEY_STD_PHONENUM = "KEY_STD_PHONENUM";

    public RecyDiffCallback(List<Student> mOldData, List<Student> mNewData) {
        this.mOldData = mOldData;
        this.mNewData = mNewData;
    }

    @Override
    public int getOldListSize() {
        return mOldData==null?0:mOldData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData==null?0:mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldData.get(oldItemPosition).getStdId().equals(mNewData.get(newItemPosition).getStdId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Student oldData = mOldData.get(oldItemPosition);
        Student newData = mNewData.get(newItemPosition);

        if(!oldData.getStdName().equals(newData.getStdName())) {
            return false;
        }
        if(oldData.getStdAge() != newData.getStdAge()) {
            return false;
        }
        if(!oldData.getStdPhoneNum().equals(newData.getStdPhoneNum())) {
            return false;
        }
        if(oldData.getStdGender()!=newData.getStdGender()) {
            return false;
        }
        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Student oldData = mOldData.get(oldItemPosition);
        Student newData = mNewData.get(newItemPosition);
        Bundle payload = new Bundle();

        if(!oldData.getStdName().equals(newData.getStdName())) {
            payload.putString(KEY_STD_NAME,newData.getStdName());
        }
        if(oldData.getStdAge() != newData.getStdAge()) {
            payload.putInt(KEY_STD_AGE,newData.getStdAge());
        }
        if(!oldData.getStdPhoneNum().equals(newData.getStdPhoneNum())) {
            payload.putString(KEY_STD_PHONENUM,newData.getStdPhoneNum());
        }
        if(payload.size()==0) {
            return null;
        }else {
            return payload;
        }
    }
}
