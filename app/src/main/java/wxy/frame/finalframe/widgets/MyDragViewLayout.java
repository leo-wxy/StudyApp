package wxy.frame.finalframe.widgets;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import wxy.frame.finalframe.util.LogUtils;

/**
 * Created by xixi on 16/8/29.
 */

public class MyDragViewLayout extends ViewGroup {
    public ViewDragHelper mViewDragHelper;
    private View mMenuView;
    private View mContentView;
    private int mCurrentTop = 0;
    private boolean isOpen = true;

    public MyDragViewLayout(Context context) {
        super(context);
        init();
    }

    public MyDragViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyDragViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallBack());
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);
    }

    private class ViewDragHelperCallBack extends ViewDragHelper.Callback {

        /**
         * @param child     决定哪个view可以被捕获
         * @param pointerId touch id
         * @return
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mContentView;
        }

        /**
         * 在边界拖动时回调
         *
         * @param edgeFlags
         * @param pointerId
         */
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {

            super.onEdgeDragStarted(edgeFlags, pointerId);
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            LogUtils.e("endflags" + edgeFlags);
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        /**
         * 限制被拖拽view的竖直滑动
         *
         * @param child 控制的view
         * @param top   上下平移的量
         * @param dy    增量
         * @return
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return Math.max(Math.min(top, MyDragViewLayout.this.getMeasuredHeight()), 0);
        }

        /**
         * 获取竖直滑动范围
         *
         * @param child
         * @return
         */
        @Override
        public int getViewVerticalDragRange(View child) {
            if (mMenuView == null) return 0;
            return (mContentView == child) ? MyDragViewLayout.this.getMeasuredHeight() : 0;
        }

        /**
         * 拖动释放后调用
         *
         * @param releasedChild
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int finalTop = mMenuView.getHeight();
            if (yvel <= 0) {
                LogUtils.e("向上");
                if (releasedChild.getTop() < mMenuView.getHeight() / 2) {
                    finalTop = 0;
                } else {
                    finalTop = mMenuView.getHeight();
                }
            } else {
                LogUtils.e("向下");
                if (releasedChild.getTop() > mMenuView.getHeight() / 2) {
                    if (releasedChild.getTop() > mMenuView.getHeight()) {
                        finalTop = MyDragViewLayout.this.getMeasuredHeight();
                    } else {
                        finalTop = mMenuView.getHeight();
                    }
                } else {
                    finalTop = 0;
                }
            }
            mViewDragHelper.settleCapturedViewAt(releasedChild.getLeft(), finalTop);
            postInvalidateOnAnimation();
        }

        /**
         * 当view位置改变时
         *
         * @param changedView
         * @param left
         * @param top
         * @param dx
         * @param dy
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mMenuView.setVisibility((changedView.getHeight() - top == getHeight()) ? View.GONE : View.VISIBLE);
            mMenuView.setAlpha(1 - (changedView.getHeight() - top) * 1.00f / getHeight());
            mCurrentTop += dy;
            requestLayout();
        }

        /**
         * view 拖动状态改变
         *
         * @param state
         */
        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }

    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true))
            ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mContentView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mMenuView.layout(0, 0,
                mMenuView.getMeasuredWidth(),
                mMenuView.getMeasuredHeight());
        //0 900 1080  1900
        mContentView.layout(0, mCurrentTop + mMenuView.getHeight(),
                mContentView.getMeasuredWidth(),
                this.getMeasuredHeight());
    }

    public void showView() {
        mCurrentTop = 0;
        mContentView.layout(0, mMenuView.getHeight(),
                mContentView.getMeasuredWidth(),
                this.getMeasuredHeight());
        mContentView.animate().y(-1000).setDuration(500).start();
    }

}
