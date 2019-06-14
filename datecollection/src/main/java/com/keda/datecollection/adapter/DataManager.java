package com.keda.datecollection.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.keda.datecollection.adapter.recyclerview.DataDealRecyclerViewAdapter;
import com.keda.datecollection.annotations.handler.AnnotationHandler;
import com.keda.datecollection.model.viewbean.BaseEditBean;
import com.keda.datecollection.util.Util;

import java.util.List;

import static com.keda.datecollection.annotations.handler.AnnotationHandler.TYPE_COLLECTION;
import static com.keda.datecollection.annotations.handler.AnnotationHandler.TYPE_MODIFY;
import static com.keda.datecollection.annotations.handler.AnnotationHandler.TYPE_SHOW;

/**
 * author: created by wengliuhu
 * time: 2019/6/11 17
 */
public class DataManager
{
    public static DataDealRecyclerViewAdapter getDataCollectionRecycleViewAdapter(Context context, Object note, @LayoutRes int defultLayoutId, int brVariableId, @AnnotationHandler.Type int type)
    {
        if (context == null) return null;
        List<BaseEditBean> datas = null;

        switch (type)
        {
            case TYPE_COLLECTION:
            {
                datas = AnnotationHandler.getCollectionEditBean(note, defultLayoutId);
                break;
            }
            case TYPE_MODIFY:
            {
                datas = AnnotationHandler.getModifyEditBean(note, defultLayoutId);
                break;
            }

            case TYPE_SHOW:
            {
                datas = AnnotationHandler.getShowEditBean(note, defultLayoutId);
                break;
            }

        }

        if (Util.isEmpty(datas)) return null;


        DataDealRecyclerViewAdapter adapter = new DataDealRecyclerViewAdapter(defultLayoutId, datas, brVariableId);

        return adapter;
    }
}
