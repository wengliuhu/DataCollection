//package com.keda.datecollection.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.keda.datecollection.view.BaseDcView;
//
//import net.minidev.json.JSONObject;
//
//import java.Util.List;
//import java.Util.Map;
//
///**
// * author: created by wengliuhu
// * time: 2019/5/23 10
// */
//public  class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseDcHolder>
//{
//    // 数据
//    protected List<BaseDcView> mDatas;
//
//    // 数据保存的json
//    protected JSONObject mResultJson;
//
//    protected Map<Integer, View> mInterruptViews;
//
//    protected int   mLayoutId;
//
//
//    @Override
//    public BaseDcHolder onCreateViewHolder(ViewGroup parent, int viewType)
//    {
//        LinearLayout layout = new LinearLayout(parent.getContext());
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setTag("root");
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, null, false);
//        layout.addView(view);
////        parent.addView(layout,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        return new BaseDcHolder(layout);
//    }
//
//    @Override
//    public void onBindViewHolder(BaseDcHolder holder, int position)
//    {
//        holder.bindView(position);
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        if(mDatas != null) return mDatas.size();
//        return 0;
//    }
//
//
//
//    /**
//     * 绑定数据时，截断
//     */
//    protected void onBindViewInterrupt()
//    {
//
//    }
//
//    public void addInterrupt()
//    {
//
//    }
//
//    public boolean submit()
//    {
//        if (mDatas == null) return true;
//        for (BaseDcView b : mDatas)
//        {
//            if (!b.submit(mResultJson))
//            {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//
//    public class BaseDcHolder extends RecyclerView.ViewHolder
//    {
//        private LinearLayout mItemRootView;
//        private TextView    mTextKey;
//        private EditText    mTextValue;
////        private ImageView
//        public BaseDcHolder(LinearLayout itemView)
//        {
//            super(itemView);
//            mItemRootView = itemView;
//
//        }
//
//        public void bindView(int position)
//        {
//            final BaseDcView baseDcView = mDatas.get(position);
//            if (baseDcView.headview != null)
//            {
//                mItemRootView.addView(baseDcView.headview, 0);
//            }
//
//            mItemRootView.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v)
//                {
//                    baseDcView.onClick(baseDcView);
//                }
//            });
//
//            mItemRootView.setOnLongClickListener(new View.OnLongClickListener()
//            {
//                @Override
//                public boolean onLongClick(View v)
//                {
//                    baseDcView.onLongClick(baseDcView);
//                    return false;
//                }
//            });
//
//
//        }
//    }
//
//
//
//
//}
