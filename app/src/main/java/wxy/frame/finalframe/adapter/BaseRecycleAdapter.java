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


    @Override
    public void onBindViewHolder(VH vh, int position) {
        final T item = getItem(position);
        bindDataToItemView(vh, item);
        bindItemViewClickListener(vh, position);
    }

    protected abstract void bindDataToItemView(VH vh, T item);

    protected final void bindItemViewClickListener(VH vh, final int i) {
        if (mOnItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick( i);
                }
            });
        }
    }

    protected T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return (mList == null) ? 0 : mList.size();
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
        void onClick( int i);
    }
}
