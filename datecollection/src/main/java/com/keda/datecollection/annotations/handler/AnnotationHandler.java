package com.keda.datecollection.annotations.handler;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.keda.datecollection.annotations.LayoutId;
import com.keda.datecollection.annotations.OnCollection;
import com.keda.datecollection.annotations.OnModify;
import com.keda.datecollection.annotations.OnShow;
import com.keda.datecollection.annotations.bean.Dict;
import com.keda.datecollection.annotations.bean.Edit;
import com.keda.datecollection.annotations.bean.Spinner;
import com.keda.datecollection.model.AbstractData;
import com.keda.datecollection.model.dict.DictItem;
import com.keda.datecollection.model.viewbean.BaseEditBean;
import com.keda.datecollection.model.viewbean.DictBean;
import com.keda.datecollection.model.viewbean.EditBean;
import com.keda.datecollection.util.Util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * author: created by wengliuhu
 * time: 2019/6/10 14
 */

public class AnnotationHandler
{
    public static final int TYPE_COLLECTION = 0;
    public static final int TYPE_MODIFY     = 1;
    public static final int TYPE_SHOW       = 2;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_COLLECTION, TYPE_MODIFY, TYPE_SHOW})
    public @interface Type {
    }
    /**
     * 根据属性获得该对象的 BaseEditBean
     * @param object
     * @param defultLayoutId 默认的布局
     * @return
     */
    public static List<BaseEditBean> getCollectionEditBean(@NonNull Object object, int defultLayoutId)
    {
        List<BaseEditBean> list = new ArrayList<>();

        Field[] fields = object.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {

            for (Field field : fields) {

                OnCollection collection = field.getAnnotation(OnCollection.class);
                if (collection == null) continue;

                field.setAccessible(true);

                BaseEditBean baseEditBean = getEditBean(object, field);
                if (baseEditBean == null) continue;
                int layoutId = collection.layoutId();
                layoutId = layoutId == 0 ? defultLayoutId : layoutId;
                baseEditBean.layoutId = layoutId;

                try
                {
                    baseEditBean.value = String.valueOf(field.get(object));
                    baseEditBean.editAble = collection.editAble();
                    baseEditBean.editState = collection.editState();
                    baseEditBean.must = collection.must();
                    baseEditBean.key = collection.key();
                    baseEditBean.keyJson = TextUtils.isEmpty(baseEditBean.keyJson) ? field.getName() : baseEditBean.keyJson;

                } catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }

                list.add(baseEditBean);
            }
        }

        return list;
    }

    /**
     * 根据属性获得该对象的 BaseEditBean
     * @param object
     * @param defultLayoutId 默认的布局
     * @return
     */
    public static List<BaseEditBean> getModifyEditBean(@NonNull Object object, int defultLayoutId)
    {
        List<BaseEditBean> list = new ArrayList<>();
        if (!(object instanceof AbstractData)) return list;

        Field[] fields = object.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {

            for (Field field : fields) {

                OnModify collection = field.getAnnotation(OnModify.class);
                if (collection == null) continue;

                field.setAccessible(true);

                BaseEditBean baseEditBean = getEditBean(object, field);
                if (baseEditBean == null) continue;
                int layoutId = collection.layoutId();
                layoutId = layoutId == 0 ? defultLayoutId : layoutId;
                baseEditBean.layoutId = layoutId;

                try
                {
                    baseEditBean.value = String.valueOf(field.get(object));
                    baseEditBean.editAble = collection.editAble();
                    baseEditBean.editState = collection.editState();
                    baseEditBean.must = collection.must();
                    baseEditBean.key = collection.key();
                    baseEditBean.keyJson = TextUtils.isEmpty(baseEditBean.keyJson) ? field.getName() : baseEditBean.keyJson;

                } catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }

                list.add(baseEditBean);
            }
        }

        return list;
    }

    /**
     * 根据属性获得该对象的 BaseEditBean
     * @param object
     * @param defultLayoutId 默认的布局
     * @return
     */
    public static List<BaseEditBean> getShowEditBean(@NonNull Object object, int defultLayoutId)
    {
        List<BaseEditBean> list = new ArrayList<>();
        if (!(object instanceof AbstractData)) return list;

        Field[] fields = object.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {

            for (Field field : fields) {

                OnShow collection = field.getAnnotation(OnShow.class);
                if (collection == null) continue;

                field.setAccessible(true);

                BaseEditBean baseEditBean = getEditBean(object, field);
                if (baseEditBean == null) continue;
                int layoutId = collection.layoutId();
                layoutId = layoutId == 0 ? defultLayoutId : layoutId;
                baseEditBean.layoutId = layoutId;

                try
                {
                    baseEditBean.value = String.valueOf(field.get(object));
//                    baseEditBean.editAble = collection.editAble();
//                    baseEditBean.editState = collection.editState();
                    baseEditBean.must = collection.must();
                    baseEditBean.key = collection.key();
                    baseEditBean.keyJson = TextUtils.isEmpty(baseEditBean.keyJson) ? field.getName() : baseEditBean.keyJson;

                } catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }

                list.add(baseEditBean);
            }
        }

        return list;
    }


    private static BaseEditBean getEditBean(Object o, Field field)
    {
        BaseEditBean baseEditBean = null;
        Dict dict = field.getAnnotation(Dict.class);
        if (dict != null)
        {
            baseEditBean = parseDictBean(dict);
        }

        Edit edit  = field.getAnnotation(Edit.class);
        if (edit != null)
        {
            baseEditBean = parseEditBean(edit);
        }

        if (baseEditBean == null)
        {
            baseEditBean = new EditBean();
        }

        return baseEditBean;
    }

    private static DictBean parseDictBean(Dict dict)
    {
        DictBean dictBean = new DictBean();
        dictBean.dictName = dict.dictName();
        dictBean.dictItem = new DictItem();
        dictBean.dictItem.dm = dict.dm();
        dictBean.dictItem.mc = dict.mc();
        dictBean.keyJson = dict.dmJson();
        dictBean.valueJson = dict.mcJson();

        return dictBean;
    }

    private static EditBean parseEditBean(Edit edit)
    {
        EditBean editBean = new EditBean();
        editBean.keyJson = edit.keyJson();
        editBean.value = edit.value();

        return editBean;
    }


}
