package com.lewish.start.diffutildemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.lewish.start.diffutildemo.adapter.RecyAdapter;
import com.lewish.start.diffutildemo.databinding.ActivityMainBinding;
import com.lewish.start.diffutildemo.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ActivityMainBinding binding;
    private RecyAdapter mRecyAdapter;
    private List<Student> mDatas;
    private int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mDatas = getData();
        initRefreshLayout();
        initRecyView();
        binding.mBtnAdd.setOnClickListener(this);
        binding.mBtnRemove.setOnClickListener(this);
        binding.mBtnModifie.setOnClickListener(this);
        binding.mBtnModifie2.setOnClickListener(this);

    }

    private List<Student> getData() {
        List<Student> mDatas = new ArrayList<>();
        mDatas.add(new Student("111111","孙悟空",10,"18511231111", Student.stdGender.BOY));
        mDatas.add(new Student("222222","猪八戒",20,"18511232222", Student.stdGender.BOY));
        mDatas.add(new Student("333333","沙  僧",30,"18511233333", Student.stdGender.BOY));
        mDatas.add(new Student("444444","小白龙",40,"18511234444", Student.stdGender.BOY));
        mDatas.add(new Student("555555","唐玄奘",50,"18511235555", Student.stdGender.BOY));
        mDatas.add(new Student("666666","武  松",60,"18511236666", Student.stdGender.BOY));
        mDatas.add(new Student("777777","潘金莲",70,"18511237777", Student.stdGender.GIRL));
        mDatas.add(new Student("888888","范冰冰",80,"18511238888", Student.stdGender.GIRL));
        mDatas.add(new Student("999999","高小琴",90,"18511239999", Student.stdGender.GIRL));
        return mDatas;
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

    private void initRecyView() {
        mRecyAdapter = new RecyAdapter(this,R.layout.item_layout,mDatas);
        binding.mRecView.setAdapter(mRecyAdapter);
        binding.mRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtnAdd :
                testAdd();
                break;
            case R.id.mBtnRemove:
                testRemove();
                break;
            case R.id.mBtnModifie:
                testModifie();
                break;
            case R.id.mBtnModifie2:
                testModifie2();
                break;
        }
    }

    private void testModifie2() {
        List<Student> newDatas = new ArrayList<>();
        for (Student mData : mDatas) {
            try {
                newDatas.add(mData.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        Student student = newDatas.get(2);
        student.setStdName("222222222");
        student.setStdAge(22);
        student.setStdPhoneNum("22222222");

        upDateRecy(newDatas);
    }

    private void testModifie() {
        List<Student> newDatas = new ArrayList<>();
        for (Student mData : mDatas) {
            try {
                newDatas.add(mData.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        Student student = newDatas.get(0);
        student.setStdName("hahahaha");

        upDateRecy(newDatas);
    }

    private void testRemove() {
        List<Student> newDatas = new ArrayList<>();
        for (Student mData : mDatas) {
            try {
                newDatas.add(mData.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        newDatas.remove(newDatas.size()-1);
        upDateRecy(newDatas);
    }

    private void testAdd() {
        List<Student> newDatas = new ArrayList<>();
        for (Student mData : mDatas) {
            try {
                newDatas.add(mData.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        num++;
        newDatas.add(new Student(num+"","新添加"+num,num,""+(18500000000L+num), Student.stdGender.BOY));
        upDateRecy(newDatas);
    }

    private void upDateRecy(List<Student> newdatas){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RecyDiffCallback(mDatas, newdatas), false);
        diffResult.dispatchUpdatesTo(mRecyAdapter);
        mDatas = newdatas;
        mRecyAdapter.setDatas(mDatas);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                List<Student> newDatas = getData();
                upDateRecy(newDatas);

                binding.mRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "下拉刷新完成", Toast.LENGTH_SHORT).show();

            }
        }, 3000);
    }
}
