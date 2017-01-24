package com.ailtt.data;

import android.util.Log;

import com.ailtt.bean.Livesource;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/23.
 */

public class LivesourcePresenter {
    //对外暴露数据的接口
    private ILivesource iLivesource;

    public LivesourcePresenter(ILivesource iLivesource) {
        this.iLivesource = iLivesource;
    }

    public void getLivesourceList(){
        BmobQuery<Livesource> q=new BmobQuery<>();
       /* q.findObjects(new FindListener<Livesource>() {
            @Override
            public void done(List<Livesource> list, BmobException e) {
                for (int i = 0; i < list.size(); i++) {
                    Log.i("Main","视频链接是："+list.get(i).getName()+":"+list.get(i).getVideoUrl());
                }
            }
        });*/
        iLivesource.before();
        Observable<List<Livesource>> objectsObservable = q.findObjectsObservable(Livesource.class);

        objectsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Livesource>>() {
                    @Override
                    public void onCompleted() {
                        iLivesource.after();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (throwable instanceof BmobException){
                            BmobException exception= (BmobException) throwable;
                            iLivesource.getILivesourceError("失败："+exception.getMessage()+","+exception.getErrorCode());
                        }else {
                            iLivesource.getILivesourceError(throwable.getMessage());
                        }
                    }

                    @Override
                    public void onNext(List<Livesource> livesources) {
                        iLivesource.getILivesourceCompleted(livesources);
                    }
                });
    }
}
