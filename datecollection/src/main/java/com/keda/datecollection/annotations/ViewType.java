package com.keda.datecollection.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author: created by wengliuhu
 * time: 2019/6/10 14
 */
public class ViewType
{
    public static final int EDIT = 0;
    public static final int DICT = 1;
    public static final int SPINNER = 2;
    public static final int TIME = 3;
    public static final int DATE = 4;

//    public final int viewType;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({EDIT, DICT, SPINNER, TIME, DATE})
    public @interface Type {
    }

//    public ViewType(@Type int viewType) {
//        this.viewType = viewType;
//    }
}
