package com.lewish.start.recyclerviewdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lewish.start.baseadapterlib.recyclerview.OnItemClickListener;
import com.lewish.start.recyclerviewdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
/**
 * author: sundong
 * created at 2017/4/11 15:56
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ActivityMainBinding binding;
    private SimplifiedRecyViewdapter2 myRecyViewAdapter;
    private List<String> mDatas;
    private static int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        initData();
        initListener();
        initRefreshLayout();
        initRecyView();
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for(int i = 0; i < 30; i++) {
            mDatas.add("我是第"+(i+1)+"机器人，么么哒~");
        }
    }

    private void initRecyView() {
        myRecyViewAdapter = new SimplifiedRecyViewdapter2(this,R.layout.item_layout,mDatas);
        myRecyViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Toast.makeText(MainActivity.this, "你点击了第"+(position+1)+"条Item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        myRecyViewAdapter.setmOnIconClickListener(new SimplifiedRecyViewdapter2.OnIconClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "你点击了第"+(position+1)+"条机器人的头像", Toast.LENGTH_SHORT).show();
            }
        });
        binding.mRecView.setAdapter(myRecyViewAdapter);
        binding.mRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private void initRefreshLayout() {
        SwipeRefreshLayout mRefreshLayout = binding.mRefreshLayout;
        mRefreshLayout.setOnRefreshListener(this);
        //设置滑动多少距离有效果
        mRefreshLayout.setDistanceToTriggerSync(100);
        //设置小圈圈颜色的颜色，最多可以设置四种颜色
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,android.R.color.holo_blue_light,android.R.color.holo_orange_light,android.R.color.holo_red_light);
        //设置下拉刷新控件背景的颜色
//        mRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.background_light));
        //设置下拉刷新的类型
        mRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
    }

    private void initListener() {
        binding.mBtnAdd.setOnClickListener(this);
        binding.mBtnRemove.setOnClickListener(this);
        binding.mBtnListview.setOnClickListener(this);
        binding.mBtnGridview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtnAdd :
                myRecyViewAdapter.addDataByPosition(myRecyViewAdapter.getItemCount(),"我是新添加的第"+(++num)+"个机器人，么么哒~");
                binding.mRecView.scrollToPosition(myRecyViewAdapter.getItemCount()-1);
                break;
            case R.id.mBtnRemove :
                myRecyViewAdapter.delDataByPosition(myRecyViewAdapter.getItemCount()-1);
                binding.mRecView.scrollToPosition(myRecyViewAdapter.getItemCount()-1);
                break;
            case R.id.mBtnListview :
                binding.mRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.mBtnGridview :
                binding.mRecView.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
                break;
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                //设置刷新状态还原-或者说结束
                myRecyViewAdapter.notifyItemRangeChanged(0,mDatas.size());
                binding.mRecView.scrollToPosition(0);
                //定位到某条位置
                binding.mRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "下拉刷新完成", Toast.LENGTH_SHORT).show();

            }
        }, 3000);
    }
}
