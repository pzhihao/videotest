package com.ailtt.data;

import com.ailtt.bean.Livesource;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 */

public interface ILivesource {
    void after();
    void before();
    void getILivesourceCompleted(List<Livesource> livesources);
    void getILivesourceError(String msg);
}
