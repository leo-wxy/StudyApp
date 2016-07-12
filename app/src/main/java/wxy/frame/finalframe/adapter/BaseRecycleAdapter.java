package wxy.frame.finalframe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xixi on 16/6/27.
 */

/**
 * RecycleView的基本继承adapter
 *
 * @param <T>
 * @param <VH>
 */
public abstract class BaseRecycleAdapter<T, VH extends BaseRecycleAdapter.SparseArrayViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> mList;// 列表List
    protected LayoutInflater mInflater;// 布局管理
    protected OnCustomListener listener;//单独点击事件
    public Context context;
    private static final int BASE_ITEM_TYPE_HEADER = 100000;//头布局 itemType基数
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;//尾布局 itemType基数

    private SparseArray<View> mHeaderViews = new SparseArray<>();//头布局view
    private SparseArray<View> mFootViews = new SparseArray<>();//尾布局view

    /**
     * click listener
     */
    protected OnItemClickListener mOnItemClickListener;

    public BaseRecycleAdapter(Context context, List<T> mList) {
        this.mList = mList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    protected View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return mInflater.inflate(layoutId, viewGroup, attach);
    }

    /**
     * 创建viewholder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {//有头布局
            SparseArrayViewHolder holder = new SparseArrayViewHolder(mHeaderViews.get(viewType));
            return (VH) holder;
        } else if (mFootViews.get(viewType) != null) {//有尾布局
            SparseArrayViewHolder holder = new SparseArrayViewHolder(mFootViews.get(viewType));
            return (VH) holder;
        }
        return createView(parent, viewType);//普通布局
    }

    /**
     * 绑定viewholder
     *
     * @param vh
     * @param position
     */
    @Override
    public void onBindViewHolder(VH vh, int position) {
        if (isHeaderViewPos(position))//头布局坐标
            return;
        if (isFooterViewPos(position))//尾布局坐标
            return;
        final T item = getItem(position - getHeadersCount());
        bindDataToItemView(vh, item);
        bindItemViewClickListener(vh, position - getHeadersCount());
    }

    protected abstract VH createView(ViewGroup parent, int viewType);

    protected abstract void bindDataToItemView(VH vh, T item);

    /**
     * 绑定点击事件
     *
     * @param vh
     * @param i
     */
    protected final void bindItemViewClickListener(VH vh, final int i) {
        if (mOnItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(i);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - this.getItemCount());
        }
        return super.getItemViewType(position - getHeadersCount());
    }

    protected T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return (mList == null) ? getHeadersCount() + getFootersCount() :
                getHeadersCount() + getFootersCount() + mList.size();
    }

    public static class SparseArrayViewHolder extends RecyclerView.ViewHolder {
        private final SparseArray<View> views;

        public SparseArrayViewHolder(View itemView) {
            super(itemView);
            views = new SparseArray<>();
        }

        public <T extends View> T getView(int id) {
            View view = views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.put(id, view);
            }
            return (T) view;
        }
    }

    /**
     * 设置某个控件的点击事件
     *
     * @param listener
     */
    public void setOnCustomListener(OnCustomListener listener) {
        this.listener = listener;
    }

    public interface OnCustomListener {
        void onCustomerListener(View v, int position);
    }

    /**
     * 设置item的点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int i);
    }

    /**
     * 添加头布局
     *
     * @param view
     */
    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    /**
     * 添加尾布局
     *
     * @param view
     */
    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    /**
     * 获取头布局个数
     *
     * @return
     */
    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    /**
     * 获取尾布局个数
     *
     * @return
     */
    public int getFootersCount() {
        return mFootViews.size();
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + this.getItemCount();
    }
}
