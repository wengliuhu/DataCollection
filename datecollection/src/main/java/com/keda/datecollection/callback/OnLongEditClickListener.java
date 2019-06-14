package com.keda.datecollection.callback;

import android.view.View;

import com.keda.datecollection.model.viewbean.BaseEditBean;

import java.io.Serializable;

/**
 * author: created by wengliuhu
 * time: 2019/6/11 14
 */
public abstract class OnLongEditClickListener implements Serializable
{
    public abstract void onLongClick(View view, BaseEditBean baseEditBean, int position);
}
