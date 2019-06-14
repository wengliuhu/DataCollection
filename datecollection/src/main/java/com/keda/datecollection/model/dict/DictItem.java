package com.keda.datecollection.model.dict;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.Serializable;

public class DictItem implements Serializable, Comparable<DictItem>
{
    public String mc = "";
    public String dm = "";

    @Override
    public int compareTo(@NonNull DictItem o)
    {
        return this.getRealInteger(dm) > o.getRealInteger(o.dm) ? 1 : -1 ;
    }


    private int getRealInteger(String dm)
    {
        int num = 0;

        if (TextUtils.isEmpty(dm)) return 0;

        String newStr = dm.replaceAll("^(0+)", "");

        try
        {
            num = Integer.valueOf(newStr);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        return num;
    }


}
