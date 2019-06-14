package com.keda.datecollection.adapter.recyclerview;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keda.datecollection.model.viewbean.BaseEditBean;

import java.util.List;

/**
 * Created by wangqian on 2018/5/25.
 */

public abstract class DataDealMultiItemRecyclerViewAdapter<T extends BaseEditBean> extends DataDealRecyclerViewAdapter<T>
{

    /**
     * 多item布局的adapter的构造方法
     * @param data 数据
     * @param brVariableId layout中制定的BR的ID
     */
    public DataDealMultiItemRecyclerViewAdapter(List data, int brVariableId) {
        super(-1,data, brVariableId);
        nData = data;
        nBrVariableId = brVariableId;

    }

    /**
     * 多item布局的adapter的构造方法
     * @param data 数据
     */
    public DataDealMultiItemRecyclerViewAdapter(List data) {
        super(-1,data);
        nData = data;
    }
    @Override
    public DateDealRecyclerViewBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == EMPTY_VIEW){
            DateDealRecyclerViewBindingHolder holder = new DateDealRecyclerViewBindingHolder(nEmptyLayout);
            return holder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        DateDealRecyclerViewBindingHolder holder = new DateDealRecyclerViewBindingHolder(view);
        return holder;
    }

    public abstract @LayoutRes int getItemViewLayoutId(int position) ;

    @Override
    final public int getItemViewType(int position) {
      return super.getItemViewType(position);
    }
    @Override
    final protected int getDefItemViewType(int position) {
        return getItemViewLayoutId(position);
    }
}
