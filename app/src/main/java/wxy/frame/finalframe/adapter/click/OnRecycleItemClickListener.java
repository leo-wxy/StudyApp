package wxy.frame.finalframe.adapter.click;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650820134&idx=1&sn=58103e352e5269159778d35dc36ed207&scene=1&srcid=0513bm4gS5UCvWDmAAbUALwh#wechat_redirect
 * Created by wxy on 2016/9/23.
 */

public abstract class OnRecycleItemClickListener implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat mGestureDetector;
    private RecyclerView mRecyclerView;

    public OnRecycleItemClickListener(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        this.mGestureDetector = new GestureDetectorCompat(mRecyclerView.getContext(), new ItemTouchHelperGestureListener());
    }

    public OnRecycleItemClickListener() {

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public abstract void onItemClick(int position);

//    public void onItemLongClick()

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder vh = mRecyclerView.getChildViewHolder(child);
                onItemClick(vh.getAdapterPosition());
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }
    }
}
