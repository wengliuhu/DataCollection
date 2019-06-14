package com.keda.datecollection.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: created by wengliuhu
 * time: 2019/6/10 14
 *
 * 修改
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface OnModify
{
    String  key()           default "";
    int     layoutId()      default 0;
    boolean must()          default false;
    boolean editAble()      default true;
    boolean editState()     default true;
}
