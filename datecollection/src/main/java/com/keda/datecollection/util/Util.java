package com.keda.datecollection.util;

import android.content.Context;
import android.database.Cursor;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * author: created by wengliuhu
 * time: 2019/5/23 13
 */
public class Util
{

    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int dp2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public static boolean ensureFilePathExist(String filePath)
    {
        try
        {
            File file = new File(filePath);
            boolean exists = file.exists();
            if (!exists)
            {
                return file.mkdirs();
            }
            return true;
        } catch (final Exception e)
        {
            return false;
        }
    }

    /**
     * 判断目录或文件是否存在
     */
    public static boolean fileExists(final String strFile)
    {
        try
        {
            final File filePath = new File(strFile);
            return filePath.exists();
        } catch (final Exception e)
        {
            return false;
        }
    }

    public static void closeCursor(Cursor cursor)
    {
        try
        {
            if (cursor == null) return;
            cursor.close();
        } catch (Exception e)
        {
        }
    }

    public static boolean isEmpty(List list)
    {
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty(Map map)
    {
        return map == null || map.size() == 0;
    }

    public static boolean isEmpty(Object[] array)
    {
        return array == null || array.length == 0;
    }


}
