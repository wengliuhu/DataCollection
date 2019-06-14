package com.keda.datacollectionview.viewmodel;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

/**
 * author: created by wengliuhu
 * time: 2019/6/14 13
 */
public class BaseViewModel extends ViewModel implements Observable, LifecycleObserver
{
    private PropertyChangeRegistry nCallbacks = new PropertyChangeRegistry();

    public BaseViewModel() {
    }

    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        this.nCallbacks.add(callback);
    }

    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        this.nCallbacks.remove(callback);
    }

    protected void notifyChange() {
        this.nCallbacks.notifyCallbacks(this, 0, null);
    }

    protected void notifyPropertyChanged(int fieldId) {
        this.nCallbacks.notifyCallbacks(this, fieldId, null);
    }
}
