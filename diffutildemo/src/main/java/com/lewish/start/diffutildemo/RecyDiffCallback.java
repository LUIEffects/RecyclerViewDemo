package com.lewish.start.diffutildemo;

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
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
