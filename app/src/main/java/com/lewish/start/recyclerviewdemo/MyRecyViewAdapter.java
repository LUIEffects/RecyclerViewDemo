package com.lewish.start.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * author: sundong
 * created at 2017/4/11 15:12
 * 最原始的写法
 */
public class MyRecyViewAdapter extends RecyclerView.Adapter<MyRecyViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private int mLayoutRes;

    public MyRecyViewAdapter(Context mContext, int layoutRes,List<String> datas) {
        this.mContext = mContext;
        this.mLayoutRes = layoutRes;
        this.mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutRes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivIcon;
        private TextView tvTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(this);
            ivIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.list_item:
                    if(mOnItemClickListener!=null) {
                        mOnItemClickListener.onItemClick(v,getLayoutPosition());
                    }
                    break;
                case R.id.iv_icon:
                    if(mOnItemClickListener!=null) {
                        mOnItemClickListener.onItemClick(v,getLayoutPosition());
                    }
                    break;
            }
        }
    }

    public void addDataByPosition(int position, String data){
        if(data!=null) {
            mDatas.add(position,data);
            notifyItemInserted(position);
        }
    }

    public void delDataByPosition(int position){
        if(position>=0) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onOnItemLongClick(View view,int position);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
