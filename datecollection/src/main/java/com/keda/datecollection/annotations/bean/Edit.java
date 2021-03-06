package com.keda.datecollection.annotations.bean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: created by wengliuhu
 * time: 2019/6/10 14
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Edit
{
//    String key() ;
    //  默认未属性名
    String keyJson() default "";
    String value() default "";
//    boolean must() default false;
//    boolean editAble() default true;
//    boolean editState() default true;

}
