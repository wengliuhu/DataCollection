package com.keda.datecollection.annotations.bean;

/**
 * author: created by wengliuhu
 * time: 2019/6/12 14
 */
public @interface DictItem
{
    String key() default "";
    String value() default "";
}
