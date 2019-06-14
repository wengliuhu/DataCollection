package com.keda.datecollection.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keda.datecollection.R;

import net.minidev.json.JSONObject;

/**
 * author: created by wengliuhu
 * time: 2019/5/23 11
 */
public abstract class BaseDcView<T>  extends LinearLayout implements IBaseDcView<T>
{
    protected Context   mContext;
    protected int       mLayoutId   =   0;

    protected ViewGroup mRootView;
    protected TextView  mTextKey;
    protected EditText  mTextValue;
    protected TextView  mTextMust;

    public String   key             = "";       //  默认左侧的字段名
    public String   keyJson         = "";       //  保存的json-key
    public String   value           = "";       //  右侧的默认值
    public boolean  must            = false;    //  必填项
    public int      minHeight       = -1;       //  最小高度
    public boolean  editAble        = true;     //  是否可编辑
    public boolean  editState       = true;     //  可编辑和不可编辑状态

    public T    result = null;

    public boolean submit(JSONObject jsonObject)
    {
        if (checkData()) return saveData(jsonObject, result);
        return false;
    }

    public BaseDcView(Context context)
    {
        super(context);
        mContext = context;

        init(context);
    }

    public BaseDcView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;

        init(context);

    }

    public BaseDcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mContext = context;

        init(context);

    }

    public BaseDcView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;

        init(context);
    }

    protected   void  init(Context context)
    {
        View view = View.inflate(context, mLayoutId, null);
        if (!(view instanceof ViewGroup))
        {
            view = View.inflate(mContext, R.layout.view_base_datacollection_item, null);
        }

        mRootView = (ViewGroup) view;

        mTextKey = view.findViewWithTag("key");
        mTextValue = view.findViewWithTag("value");
        mTextMust = view.findViewWithTag("must");

        mTextKey.setText(TextUtils.isEmpty(key) ? "暂无" : key);
        mTextValue.setText(TextUtils.isEmpty(key) ? "" : key);
        mTextMust.setVisibility(must ? VISIBLE : GONE);

        initOtherView();
        addView(view);
    }

    protected  void build()
    {

    }

    public abstract void setLayoutId();

    protected abstract T getValue();


    /**
     * 初始化其他控件
     */
    protected void initOtherView()
    {

    }




    /**
     * 点击事件
     */
    protected void onClick(BaseDcView<T> baseDcView)
    {

    }

    /**
     * 长按事件
     */
    protected  void onLongClick(BaseDcView<T> baseDcView)
    {

    }


    @Override
    public boolean saveData(JSONObject jsonObject, T t)
    {
        jsonObject.put(keyJson, t);
        return true;
    }
}
