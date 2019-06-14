package com.keda.datecollection.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.TextView;

import com.keda.datecollection.R;

import java.io.Serializable;

/**
 * author: created by wengliuhu
 * time: 2019/5/23 11
 */
public class EditDcView extends BaseDcView
{
    public String   hint            = "";       //  hint
    public boolean  showNum         = false;    //  是否显示输入数量
    public int      maxlength       = 100;      //  最大输入长度
    public String   mDigits         = "";
    public TextWatcher textWatcher;

    private TextView  mTextNum;

    public EditDcView(Context context, String key, String keyJson, String value, String hint)
    {
        super(context);
        this.key = key;
        this.value = value;
        this.keyJson = keyJson;
        this.hint = hint;
    }

    public void setShowNum(boolean showNum)
    {
        this.showNum = showNum;
    }

    public void setMaxlength(int maxlength)
    {
        this.maxlength = maxlength;
    }

    public void setDigits(String mDigits)
    {
        this.mDigits = mDigits;
    }

    public void setMinHeight(int minHeight)
    {
        this.minHeight = minHeight;
    }

    public void setTextWatcher(TextWatcher textWatcher)
    {
        this.textWatcher = textWatcher;
    }

    @Override
    public boolean checkData()
    {
        return getValue().length() <= maxlength;
    }


    @Override
    protected void initOtherView()
    {
        mTextNum = mRootView.findViewById(R.id.item_num);

    }

    @Override
    protected void build()
    {
        mTextNum.setVisibility(showNum ? VISIBLE : GONE);

        // 设置默认内容
        mTextValue.setText(TextUtils.isEmpty(value) ? hint : value);
        // 设置最大输入
        mTextValue.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxlength)});

        // 设置过滤
        if (!TextUtils.isEmpty(mDigits))
        {
            mTextValue.setKeyListener(DigitsKeyListener.getInstance(mDigits));
        }

        // 设置显示字数
        if (showNum)
        {
            mTextNum.setVisibility(View.VISIBLE);
            mTextNum.setText("(" + value.length() + "/" + maxlength + ")");
        }

        addWatcher(textWatcher);

        // 设置是否可编辑
        if (!editAble)
        {
            mTextValue.setFocusable(false);
            mTextValue.setFocusableInTouchMode(false);
//            mTextValue.setTextColor();
        }

        mRootView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditDcView.this.onClick(EditDcView.this);
            }
        });

        mRootView.setOnLongClickListener(new OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                EditDcView.this.onLongClick(EditDcView.this);

                return false;
            }
        });
    }

    private void addWatcher(final TextWatcher textWatcher)
    {
        if (textWatcher == null && !showNum) return;
        mTextValue.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                if (textWatcher != null)
                    textWatcher.beforeTextChanged(s, start, count, after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (textWatcher != null)
                    textWatcher.onTextChanged(s, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (showNum)
                    mTextNum.setText("(" + s.toString().length() + "/" + maxlength + ")");

                if (textWatcher != null)
                    textWatcher.afterTextChanged(s);
            }
        });

    }

    @Override
    public void setLayoutId()
    {
        mLayoutId = R.layout.view_base_datacollection_item;
    }

    @Override
    protected String getValue()
    {
        return mTextValue.getText().toString();
    }

    public   class OnTextChanged implements TextWatcher, Serializable
    {
        private OnTextChangeListener listener;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            if(listener != null)
            {
                listener.onTextChange(s.toString());
            }
        }

        public void addTextChangeListener(OnTextChangeListener listener)
        {
            this.listener =listener;
        }
    }

    public interface OnTextChangeListener
    {
        void onTextChange(String changed);
    }

}
