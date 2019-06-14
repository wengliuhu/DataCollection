package com.keda.datecollection.adapter.recyclerview;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.keda.datecollection.model.viewbean.BaseEditBean;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class DataDealRecyclerViewAdapter<T extends BaseEditBean> extends RecyclerView.Adapter<DateDealRecyclerViewBindingHolder> {
    protected List<T> nData;
    protected int nLayoutId;
    protected int nBrVariableId = -100;

    OnBindViewListener nOnBindViewListener;
    //empty
    protected FrameLayout nEmptyLayout;
    protected boolean nIsUseEmpty;
    //header footer
    private LinearLayout mHeaderLayout;
    private LinearLayout mFooterLayout;

    private boolean mHeadAndEmptyEnable;
    private boolean mFootAndEmptyEnable;

    public static final int EMPTY_VIEW = -1;
    public static final int HEADER_VIEW = -2;
    public static final int FOOTER_VIEW = -3;

    /**
     * 初始化
     *
     * @param layoutId     item的layoutId
     * @param data         数据list
     * @param brVariableId item中数据对象的databinding的变量名
     */
    public DataDealRecyclerViewAdapter(@LayoutRes int layoutId, List<T> data, int brVariableId) {
        nLayoutId = layoutId;
        nData = data;
        nBrVariableId = brVariableId;

    }

    public DataDealRecyclerViewAdapter(@LayoutRes int layoutId, List<T> data) {
        nLayoutId = layoutId;
        nData = data;

    }

    //<editor-fold desc="数据操作接口">
    public void setData(List<T> data) {
        nData = data;
        notifyDataSetChanged();
    }

    /**
     * 获取数据列表
     *
     * @return
     */
    public List<T> getData() {
        return nData;
    }

    /**
     * 添加数据到末尾
     *
     * @param data
     */
    public void addItem(@NonNull T data) {
        addItem(getDataSize(), data);
    }

    //
//    /**
//     * 添加数据到指定位置
//     * @param position
//     * @param data
//     */
//    public void addItem(int position, T data) {
//        nData.add(position, data);
//        //通知演示插入动画
//        notifyItemInserted(position);
//        //通知数据与界面重新绑定
//        notifyItemRangeChanged(position,nData.size()-position);
//    }
    public void addItem(@IntRange(from = 0) int position, @NonNull T data) {
        nData.add(position, data);
        notifyItemInserted(position + getHeaderLayoutCount());
        compatibilityDataSizeChanged(1);
        //通知数据与界面重新绑定
        notifyItemRangeChanged(position + getHeaderLayoutCount(),
                getDataSize() - position);
    }

    public void addAll( @NonNull List<T> data) {
        nData.addAll(data);
        int position = getDataSize();
        notifyItemInserted(position + getHeaderLayoutCount());
        compatibilityDataSizeChanged(data.size());
        //通知数据与界面重新绑定
        notifyItemRangeChanged(position + getHeaderLayoutCount(),
                getDataSize() - position);
    }
    /**
     * compatible getLoadMoreViewCount and getEmptyViewCount may change
     *
     * @param size Need compatible data size
     */
    private void compatibilityDataSizeChanged(int size) {
        final int dataSize = getDataSize();
        if (dataSize == size) {
            notifyDataSetChanged();
        }
    }

    /**
     * 修改指定位置的数据
     *
     * @param position
     * @param data
     */
    public void changeItem(@IntRange(from = 0) int position, T data) {
        if (position < 0 || position > getDataSize() - 1) {
            return;
        }
        nData.set(position, data);
        notifyItemChanged(position + getHeaderLayoutCount());
    }

    /**
     * 删除指定位置的数据
     *
     * @param position
     */
    public void deleteItem(@IntRange(from = 0) int position) {
        if (position < 0 || position > getDataSize() - 1) {
            return;
        }
        nData.remove(position);
        int internalPosition = position + getHeaderLayoutCount();
        notifyItemRemoved(internalPosition);
        compatibilityDataSizeChanged(0);
        notifyItemRangeChanged(internalPosition, getDataSize() - position);

    }
    //</editor-fold>
    //<editor-fold desc="设置点击事件">

    /**
     * 设置绑定数据监听
     * @param listener
     */
    public void setOnBindViewListener(OnBindViewListener listener)
    {
        nOnBindViewListener = listener;
    }
    //</editor-fold>

    //<editor-fold desc="recyclerViewAdaper继承方法">
    @Override
    public DateDealRecyclerViewBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DateDealRecyclerViewBindingHolder holder;
        switch (viewType) {

            case HEADER_VIEW:
                holder = new DateDealRecyclerViewBindingHolder(mHeaderLayout);
                break;
            case EMPTY_VIEW:
                holder = new DateDealRecyclerViewBindingHolder(nEmptyLayout);
                break;
            case FOOTER_VIEW:
                holder = new DateDealRecyclerViewBindingHolder(mFooterLayout);
                break;
            case 0:
                View viewDefult = LayoutInflater.from(parent.getContext()).inflate(nLayoutId, parent, false);
                holder = new DateDealRecyclerViewBindingHolder(viewDefult);
                break;
            default:
//                View view = LayoutInflater.from(parent.getContext()).inflate(nLayoutId, parent, false);
                View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
                holder = new DateDealRecyclerViewBindingHolder(view);
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(DateDealRecyclerViewBindingHolder holder, final int position) {
        if (position == 0 && nIsUseEmpty && isNoData()) {
            return;
        }
        int viewType = holder.getItemViewType();
        int dataPosition = position - getHeaderLayoutCount();
        switch (viewType) {
            case 0:
                convert(holder, dataPosition);
                break;
//            case LOADING_VIEW:
//                mLoadMoreView.convert(holder);
//                break;
            case HEADER_VIEW:
                break;
            case EMPTY_VIEW:
                break;
            case FOOTER_VIEW:
                break;
            default:
                convert(holder, dataPosition);
                break;
        }


    }

    private void convert(DateDealRecyclerViewBindingHolder holder, final int dataPosition) {
        T item = nData.get(dataPosition);

        if (nOnBindViewListener != null)
        {
            nOnBindViewListener.bindView(item, dataPosition);
        }

        if (nBrVariableId != -100) {
            holder.getBinding().setVariable(nBrVariableId, item);
        }

        if (item.onEditClickListener != null) {
//            if (nOnItemClickListener != null) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T item = nData.get(dataPosition);
                    item.onEditClickListener.onClick(v, item, dataPosition);
                }
            });
        }
        if (item.onLongEditClickListener != null) {
//            if (nOnItemLongClickListener != null) {
            holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    T item = nData.get(dataPosition);
                    item.onLongEditClickListener.onLongClick(v, item, dataPosition);
                    return true;
                }
            });
        }


        // app自定义其他绑定对象
        onCustomBindItem(holder.getBinding(), dataPosition);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        int count;
        if (getEmptyViewCount() == 1) {
            count = 1;
            if (mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                count++;
            }
            if (mFootAndEmptyEnable && getFooterLayoutCount() != 0) {
                count++;
            }
        } else {
            count = getHeaderLayoutCount() + (getDataSize()) + getFooterLayoutCount();
        }
        return count;

    }

    @Override
    public int getItemViewType(int position) {
        if (getEmptyViewCount() == 1) {
            boolean header = mHeadAndEmptyEnable && getHeaderLayoutCount() != 0;
            switch (position) {
                case 0:
                    if (header) {
                        return HEADER_VIEW;
                    } else {
                        return EMPTY_VIEW;
                    }
                case 1:
                    if (header) {
                        return EMPTY_VIEW;
                    } else {
                        return FOOTER_VIEW;
                    }
                case 2:
                    return FOOTER_VIEW;
                default:
                    return EMPTY_VIEW;
            }
        }
        int numHeaders = getHeaderLayoutCount();
        if (position < numHeaders) {
            return HEADER_VIEW;
        } else {
            int adjPosition = position - numHeaders;
            int adapterCount = getDataSize();
            if (adjPosition < adapterCount) {
                BaseEditBean editBean = nData.get(adjPosition);
//                return getDefItemViewType(adjPosition);
                return editBean.layoutId;
            } else {
                adjPosition = adjPosition - adapterCount;
                int numFooters = getFooterLayoutCount();
                return FOOTER_VIEW;
//                if (adjPosition < numFooters) {
//                    return FOOTER_VIEW;
//                } else {
//                    return LOADING_VIEW;
//                }
            }
        }
    }

    protected int getDefItemViewType(int position) {
        return super.getItemViewType(position);
    }


    //</editor-fold>

    //<editor-fold desc="添加header和footer相关接口">

    /**
     * Return root layout of header
     */

    public LinearLayout getHeaderLayout() {
        return mHeaderLayout;
    }

    /**
     * Return root layout of footer
     */
    public LinearLayout getFooterLayout() {
        return mFooterLayout;
    }

    /**
     * Append header to the rear of the mHeaderLayout.
     *
     * @param header
     */
    public int addHeaderView(View header) {
        return addHeaderView(header, -1);
    }

    /**
     * Add header view to mHeaderLayout and set header view position in mHeaderLayout.
     * When index = -1 or index >= child count in mHeaderLayout,
     * the effect of this method is the same as that of {@link #addHeaderView(View)}.
     *
     * @param header
     * @param index  the position in mHeaderLayout of this header.
     *               When index = -1 or index >= child count in mHeaderLayout,
     *               the effect of this method is the same as that of {@link #addHeaderView(View)}.
     */
    public int addHeaderView(View header, int index) {
        return addHeaderView(header, index, LinearLayout.VERTICAL);
    }

    /**
     * @param header
     * @param index
     * @param orientation
     */
    public int addHeaderView(View header, int index, int orientation) {
        if (mHeaderLayout == null) {
            mHeaderLayout = new LinearLayout(header.getContext());
            if (orientation == LinearLayout.VERTICAL) {
                mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
                mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            } else {
                mHeaderLayout.setOrientation(LinearLayout.HORIZONTAL);
                mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
            }
        }
        final int childCount = mHeaderLayout.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }
        mHeaderLayout.addView(header, index);
        if (mHeaderLayout.getChildCount() == 1) {
            int position = getHeaderViewPosition();
            if (position != -1) {
                notifyItemInserted(position);
            }
        }
        return index;
    }

    public int setHeaderView(View header) {
        return setHeaderView(header, 0, LinearLayout.VERTICAL);
    }

    public int setHeaderView(View header, int index) {
        return setHeaderView(header, index, LinearLayout.VERTICAL);
    }

    public int setHeaderView(View header, int index, int orientation) {
        if (mHeaderLayout == null || mHeaderLayout.getChildCount() <= index) {
            return addHeaderView(header, index, orientation);
        } else {
            mHeaderLayout.removeViewAt(index);
            mHeaderLayout.addView(header, index);
            return index;
        }
    }

    /**
     * Append footer to the rear of the mFooterLayout.
     *
     * @param footer
     */
    public int addFooterView(View footer) {
        return addFooterView(footer, -1, LinearLayout.VERTICAL);
    }

    public int addFooterView(View footer, int index) {
        return addFooterView(footer, index, LinearLayout.VERTICAL);
    }

    /**
     * Add footer view to mFooterLayout and set footer view position in mFooterLayout.
     * When index = -1 or index >= child count in mFooterLayout,
     * the effect of this method is the same as that of {@link #addFooterView(View)}.
     *
     * @param footer
     * @param index  the position in mFooterLayout of this footer.
     *               When index = -1 or index >= child count in mFooterLayout,
     *               the effect of this method is the same as that of {@link #addFooterView(View)}.
     */
    public int addFooterView(View footer, int index, int orientation) {
        if (mFooterLayout == null) {
            mFooterLayout = new LinearLayout(footer.getContext());
            if (orientation == LinearLayout.VERTICAL) {
                mFooterLayout.setOrientation(LinearLayout.VERTICAL);
                mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            } else {
                mFooterLayout.setOrientation(LinearLayout.HORIZONTAL);
                mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
            }
        }
        final int childCount = mFooterLayout.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }
        mFooterLayout.addView(footer, index);
        if (mFooterLayout.getChildCount() == 1) {
            int position = getFooterViewPosition();
            if (position != -1) {
                notifyItemInserted(position);
            }
        }
        return index;
    }

    public int setFooterView(View header) {
        return setFooterView(header, 0, LinearLayout.VERTICAL);
    }

    public int setFooterView(View header, int index) {
        return setFooterView(header, index, LinearLayout.VERTICAL);
    }

    public int setFooterView(View header, int index, int orientation) {
        if (mFooterLayout == null || mFooterLayout.getChildCount() <= index) {
            return addFooterView(header, index, orientation);
        } else {
            mFooterLayout.removeViewAt(index);
            mFooterLayout.addView(header, index);
            return index;
        }
    }

    /**
     * remove header view from mHeaderLayout.
     * When the child count of mHeaderLayout is 0, mHeaderLayout will be set to null.
     *
     * @param header
     */
    public void removeHeaderView(View header) {
        if (getHeaderLayoutCount() == 0) return;

        mHeaderLayout.removeView(header);
        if (mHeaderLayout.getChildCount() == 0) {
            int position = getHeaderViewPosition();
            if (position != -1) {
                notifyItemRemoved(position);
            }
        }
    }

    /**
     * remove footer view from mFooterLayout,
     * When the child count of mFooterLayout is 0, mFooterLayout will be set to null.
     *
     * @param footer
     */
    public void removeFooterView(View footer) {
        if (getFooterLayoutCount() == 0) return;

        mFooterLayout.removeView(footer);
        if (mFooterLayout.getChildCount() == 0) {
            int position = getFooterViewPosition();
            if (position != -1) {
                notifyItemRemoved(position);
            }
        }
    }

    /**
     * remove all header view from mHeaderLayout and set null to mHeaderLayout
     */
    public void removeAllHeaderView() {
        if (getHeaderLayoutCount() == 0) return;

        mHeaderLayout.removeAllViews();
        int position = getHeaderViewPosition();
        if (position != -1) {
            notifyItemRemoved(position);
        }
    }

    /**
     * remove all footer view from mFooterLayout and set null to mFooterLayout
     */
    public void removeAllFooterView() {
        if (getFooterLayoutCount() == 0) return;

        mFooterLayout.removeAllViews();
        int position = getFooterViewPosition();
        if (position != -1) {
            notifyItemRemoved(position);
        }
    }

    private int getHeaderViewPosition() {
        //Return to header view notify position
        if (getEmptyViewCount() == 1) {
            if (mHeadAndEmptyEnable) {
                return 0;
            }
        } else {
            return 0;
        }
        return -1;
    }

    private int getFooterViewPosition() {
        //Return to footer view notify position
        if (getEmptyViewCount() == 1) {
            int position = 1;
            if (mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                position++;
            }
            if (mFootAndEmptyEnable) {
                return position;
            }
        } else {
            return getHeaderLayoutCount() + getDataSize();
        }
        return -1;
    }

    private int getDataSize() {
        return nData == null ? 0 : nData.size();
    }
    /**
     * Call before {@link RecyclerView#setAdapter(RecyclerView.Adapter)}
     *
     * @param isHeadAndEmpty false will not show headView if the data is empty true will show emptyView and headView
     */
    /**
     * 设置当无数据时，是否显示header，Call before {@link RecyclerView#setAdapter(RecyclerView.Adapter)}
     *
     * @param isShowHeaderWhenEmpty
     */
    public void setHeaderIsShowWhenEmpty(boolean isShowHeaderWhenEmpty) {
        setHeaderFooterIsShowWhenEmpty(isShowHeaderWhenEmpty, false);
    }

    /**
     * 设置当无数据时，是否显示header和footer，Call before {@link RecyclerView#setAdapter(RecyclerView.Adapter)}
     * Call before {@link RecyclerView#setAdapter(RecyclerView.Adapter)}
     *
     * @param isShowHeaderWhenEmpty true-显示
     * @param isShowfooterWhenEmpty
     */
    public void setHeaderFooterIsShowWhenEmpty(boolean isShowHeaderWhenEmpty, boolean isShowfooterWhenEmpty) {
        mHeadAndEmptyEnable = isShowHeaderWhenEmpty;
        mFootAndEmptyEnable = isShowfooterWhenEmpty;
    }

    //</editor-fold>

    //<editor-fold desc="设置空布局">

    /**
     * 设置空数据的时候显示的view
     *
     * @param context
     * @param emptyViewLayoutId
     */
    public void setEmptyView(Context context, @LayoutRes int emptyViewLayoutId) {
        setEmptyView(LayoutInflater.from(context).inflate(emptyViewLayoutId, null, false));

    }

    /**
     * 设置空数据的时候显示的view
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        boolean insert = false;
        if (nEmptyLayout == null) {
            nEmptyLayout = new FrameLayout(emptyView.getContext());
            final RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT);
            final ViewGroup.LayoutParams lp = emptyView.getLayoutParams();
            if (lp != null) {
                layoutParams.width = lp.width;
                layoutParams.height = lp.height;
            }
            nEmptyLayout.setLayoutParams(layoutParams);
            insert = true;
        }
        nEmptyLayout.removeAllViews();
        nEmptyLayout.addView(emptyView);

        nIsUseEmpty = true;
        if (insert) {
            if (getEmptyViewCount() == 1) {
                int position = 0;
                notifyItemInserted(position);
            }
        }
    }

    public int getEmptyViewCount() {
        if (nEmptyLayout == null || nEmptyLayout.getChildCount() == 0) {
            return 0;
        }
        if (!nIsUseEmpty) {
            return 0;
        }
        if (!isNoData()) {
            return 0;
        }
        return 1;
    }
    //</editor-fold>

    /**
     * if addHeaderView will be return 1, if not will be return 0
     */
    public int getHeaderLayoutCount() {
        if (mHeaderLayout == null || mHeaderLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * if addFooterView will be return 1, if not will be return 0
     */
    public int getFooterLayoutCount() {
        if (mFooterLayout == null || mFooterLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * 用户自定义扩展需要数据绑定其他数据，如：再添加item上其他view的点击事件
     *
     * @param binding  item对应的ViewDataBinding
     * @param position item的位置
     */
    protected void onCustomBindItem(ViewDataBinding binding, int position) {
        //        binding.setVariable(BR.username，value)
    }

    /**
     * 列表单行点击事件
     *
     * @param <T>
     */
    public interface OnItemClickListener<T> {
        public void onClick(View view, T object, int position);
    }


    public interface OnItemLongClickListener<T> {
        public void onLongClick(View view, T object, int position);
    }

    public interface OnBindViewListener<T>{
        public void bindView(T object, int position);
    }

    protected boolean isNoData() {
        return nData == null || nData.size() == 0;
    }

}