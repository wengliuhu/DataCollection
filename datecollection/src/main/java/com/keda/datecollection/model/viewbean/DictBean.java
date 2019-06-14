package com.keda.datecollection.model.viewbean;

import android.view.View;

import com.keda.datecollection.callback.OnEditClickListener;
import com.keda.datecollection.callback.OnLongEditClickListener;
import com.keda.datecollection.model.dict.DictItem;

/**
 * author: created by wengliuhu
 * time: 2019/6/11 14
 */
public class DictBean extends BaseEditBean
{
    // 字典名
    public String   dictName = "";
    // 是否显示代码
    public boolean showDm = true;
    // mc_json
    public String   valueJson       = "";       //  右侧的默认值
    public DictItem dictItem = new DictItem();

    @Override
    void initClickListener()
    {
        onEditClickListener = new OnEditClickListener()
        {
            @Override
            public void onClick(View view, BaseEditBean baseEditBean, int position)
            {

            }

        };

        onLongEditClickListener = new OnLongEditClickListener()
        {
            @Override
            public void onLongClick(View view, BaseEditBean baseEditBean, int position)
            {

            }

        };
    }

    public static DictBean getBean()
    {
        return null;
    }
}
