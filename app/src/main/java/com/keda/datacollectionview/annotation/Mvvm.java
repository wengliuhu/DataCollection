package com.keda.datacollectionview.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: created by wengliuhu
 * time: 2019/6/14 14
 */


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Mvvm
{
    @LayoutRes int layoutId();
    Class viewModel();
    int bindingVariable();
}
