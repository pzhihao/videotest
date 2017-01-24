package com.ailtt.videotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ailtt.bean.Livesource;
import com.ailtt.bean.Type;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class SimplePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpleplayer);

        JCVideoPlayerStandard jcVideoPlayerStandard= (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
     //


        Intent intent = getIntent();
        Livesource l=intent.getParcelableExtra("obj");

        Log.i("Main","序列化对象传递"+l.getVideoUrl());

        String name=l.getName();
        String url=l.getVideoUrl();

        if (name!=null||name!=""&&url!=null||url!=""){
            jcVideoPlayerStandard.setUp(l.getVideoUrl(),JCVideoPlayer.SCREEN_LAYOUT_NORMAL,l.getName());
        }else {
            jcVideoPlayerStandard.setUp("http://dlhls.cdn.zhanqi.tv/zqlive/5993_qvXCc.m3u8", JCVideoPlayer.SCREEN_LAYOUT_NORMAL,"CCTV综合HD");
        }

        

        BmobQuery<Type> query=new BmobQuery<>();
        query.findObjects(new FindListener<Type>() {
            @Override
            public void done(List<Type> list, BmobException e) {
                for (int i = 0; i < list.size(); i++) {
                    Log.i("Main",list.get(i).getDesc());
                }
            }
        });



    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
