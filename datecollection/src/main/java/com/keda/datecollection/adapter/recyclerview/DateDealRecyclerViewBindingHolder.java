package com.keda.datecollection.adapter.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kd on 2018/5/24.
 */

public class DateDealRecyclerViewBindingHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;
    private View nItemView;
    public DateDealRecyclerViewBindingHolder(View itemView) {
        super(itemView);
        nItemView = itemView;

    }

    public ViewDataBinding getBinding() {
        if(binding == null){
            binding = DataBindingUtil.bind(itemView);
        }
        return binding;
    }

    /**
     * 获取Item的View对象
     * @return
     */
    public View getItemView() {
        return nItemView;
    }
}

