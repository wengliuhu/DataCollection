package com.keda.datecollection.annotations.bean;

/**
 * author: created by wengliuhu
 * time: 2019/6/10 15
 */
public class IViewBean
{
    public String   key             = "";       //  默认左侧的字段名
    public String   keyJson         = "";       //  保存的json-key
    public String   value           = "";       //  右侧的默认值
    public boolean  must            = false;    //  必填项
    public int      minHeight       = -1;       //  最小高度
    public boolean  editAble        = true;     //  是否可编辑
    public boolean  editState       = true;     //  可编辑和不可编辑状态
}
