package com.ailtt.videotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ailtt.TvListAdapter;
import com.ailtt.bean.Livesource;
import com.ailtt.data.ILivesource;
import com.ailtt.data.LivesourcePresenter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ILivesource {

    ListView lv;
    List<Livesource> list=new ArrayList<>();
    LivesourcePresenter livesourcePresenter;
    TvListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化后端云
        Bmob.initialize(this,"fab2f9737001acdd816fe21b55bf833d");


        initData();
        initView();

    }

    private void initData() {
        livesourcePresenter=new LivesourcePresenter(this);
        livesourcePresenter.getLivesourceList();

        adapter =new TvListAdapter(list,this);

    }





    private void initView() {
        lv= (ListView) findViewById(R.id.tvlistview);
        lv.setAdapter(adapter);
    }



    @Override
    public void after() {
        Log.i("Main","调用之后");
    }

    @Override
    public void before() {
        Log.i("Main","调用之前");
    }

    @Override
    public void getILivesourceCompleted(List<Livesource> livesources) {
        for (int i = 0; i < livesources.size(); i++) {
            Log.i("Mina",livesources.get(i).getVideoUrl());
        }
        list.addAll(livesources);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getILivesourceError(String msg) {
        Log.i("Main","错误"+msg);
    }
}
