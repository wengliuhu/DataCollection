package com.keda.datacollectionview.annotation.handler;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.keda.datacollectionview.annotation.Mvvm;
import com.keda.datacollectionview.annotation.ViewModel;

/**
 * author: created by wengliuhu
 * time: 2019/6/14 14
 */
public class AnnotationHandler
{

    public static Class getViewModelClass(@NonNull Object object) {
        if (object != null && (object instanceof Activity || object instanceof Fragment)) {
            Class<?> objClass = object.getClass();
            Mvvm viewModel = (Mvvm)objClass.getAnnotation(Mvvm.class);
            if (viewModel != null) {
                return viewModel.viewModel();
            }
        }

        return null;
    }

    public static int getLayout(@NonNull Object object) {
        if (object != null && (object instanceof Activity || object instanceof Fragment)) {
            Class<?> objClass = object.getClass();
            Mvvm viewModel = objClass.getAnnotation(Mvvm.class);
            if (viewModel != null) {
                return viewModel.layoutId();
            }
        }

        return 0;
    }

    public static int getBindingVariable(@NonNull Object object) {
        if (object != null && (object instanceof Activity || object instanceof Fragment)) {
            Class<?> objClass = object.getClass();
            Mvvm viewModel = objClass.getAnnotation(Mvvm.class);
            if (viewModel != null) {
                return viewModel.bindingVariable();
            }
        }

        return 0;
    }
}
