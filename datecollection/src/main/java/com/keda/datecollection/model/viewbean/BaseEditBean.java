package com.keda.datecollection.model.viewbean;

import com.keda.datecollection.callback.OnEditClickListener;
import com.keda.datecollection.callback.OnLongEditClickListener;

/**
 * author: created by wengliuhu
 * time: 2019/6/11 14
 */
public abstract class BaseEditBean
{
    public String   key             = "";       //  左侧的字段名
    public String   keyJson         = "";       //  保存的json-key
    public String   value           = "";       //  右侧的值
    public boolean  must            = false;    //  必填项
//    public int      minHeight       = -1;       //  最小高度
    public boolean  editAble        = true;     //  是否可编辑
    public boolean  editState       = true;     //  可编辑和不可编辑状态

    public int      layoutId        = 0;        //  布局文件ID

    public OnEditClickListener      onEditClickListener;
    public OnLongEditClickListener  onLongEditClickListener;

    abstract void initClickListener();

}
