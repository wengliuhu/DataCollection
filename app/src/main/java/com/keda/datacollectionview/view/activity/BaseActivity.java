package com.keda.datacollectionview.view.activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.keda.datacollectionview.annotation.handler.AnnotationHandler;
import com.keda.datacollectionview.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author: created by wengliuhu
 * time: 2019/6/14 13
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements LifecycleOwner
{
    private LifecycleRegistry nLifecycleRegistry;

    protected V mDataBinding;
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        Lifecycle lifecycle = this.getLifecycle();
        if (lifecycle instanceof LifecycleRegistry) {
            this.nLifecycleRegistry = (LifecycleRegistry)lifecycle;
        } else {
            this.nLifecycleRegistry = new LifecycleRegistry(this);
        }

        this.nLifecycleRegistry.markState(Lifecycle.State.INITIALIZED);
        super.onCreate(savedInstanceState);
        initDataBinding();
        this.nLifecycleRegistry.markState(Lifecycle.State.CREATED);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        this.nLifecycleRegistry.markState(Lifecycle.State.RESUMED);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        this.nLifecycleRegistry.markState(Lifecycle.State.STARTED);

    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        this.nLifecycleRegistry.markState(Lifecycle.State.DESTROYED);

    }

    //    @Override
//    public void setContentView(int layoutResID)
//    {
//        if (mDataBinding == null)
//            mDataBinding = DataBindingUtil.setContentView(this, layoutResID);
//        getViewModel();
//        if (mViewModel == null)
//        {
//            throw new NullPointerException("未设置viewmodel");
//        }
//    }

    private void initDataBinding()
    {
        int layout = AnnotationHandler.getLayout(this);
        if (layout == 0) throw new NullPointerException("layoutId must not null");

        if (mDataBinding == null)
            mDataBinding = DataBindingUtil.setContentView(this, layout);

        getViewModel();
        if (mViewModel == null)  throw new NullPointerException("未设置viewmodel");

        int variable = AnnotationHandler.getBindingVariable(this);
        mDataBinding.setVariable(variable, mViewModel);

    }

    public VM getViewModel() {
        if (this.mViewModel == null) {
            Class<VM> viewModelClass = this.getViewModelClass(this);
            if (viewModelClass != null) {
                this.mViewModel = ViewModelProviders.of(this).get(viewModelClass);
            }
        }

        return this.mViewModel;
    }
    private <T extends BaseViewModel> Class<T> getViewModelClass(@NonNull Object activity) {
        Class<T> viewModelClass = AnnotationHandler.getViewModelClass(activity);
        if (viewModelClass == null) {
            Type type = activity.getClass().getGenericSuperclass();
            ParameterizedType paramType = null;

            try {
                paramType = (ParameterizedType)type;
            } catch (ClassCastException var6) {
                return null;
            }

            Type[] typeArguments = paramType.getActualTypeArguments();
            if (typeArguments != null && typeArguments.length > 1) {
                viewModelClass = (Class)typeArguments[1];
            }
        }

        return viewModelClass;
    }


}
