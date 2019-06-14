package com.keda.datecollection.view;

import net.minidev.json.JSONObject;

/**
 * author: created by wengliuhu
 * time: 2019/4/18 09
 */
public interface IBaseDcView<T>
{
//    /**
//     * 点击事件
//     */
//    void onClick();
//
//    /**
//     * 长按事件
//     */
//    void onLongClick();

    /**
     * 校验数据
     * @return
     */
    boolean checkData();

    /**
     * 保存数据
     * @return
     */
    boolean saveData(JSONObject jsonObject, T t);

}
