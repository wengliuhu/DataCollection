package com.keda.datacollectionview.model;

import com.keda.datacollectionview.R;
import com.keda.datecollection.annotations.OnCollection;
import com.keda.datecollection.annotations.ViewType;
import com.keda.datecollection.annotations.bean.Dict;
import com.keda.datecollection.annotations.bean.DictItem;
import com.keda.datecollection.annotations.bean.Edit;
import com.keda.datecollection.model.viewbean.DictBean;

/**
 * author: created by wengliuhu
 * time: 2019/6/11 16
 */
public class User
{
//    @OnCollection(dictName = "", values = , type = , json = )
    @Edit(value = "科达科技")
    @OnCollection(key = "公司名称")
    public String org = "";

    @Edit(value = "安卓")
    @OnCollection(key = "所属部门")
    public String partment = "";

    @Edit(value = "王小刚")
    @OnCollection(key = "姓名", layoutId = R.layout.item_view2)
    public String userName = "";
    

}
