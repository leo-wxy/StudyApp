package wxy.frame.finalframe.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import wxy.frame.finalframe.BaseActivity;
import wxy.frame.finalframe.util.SnackBarUtils;

/**
 * Created by xixi on 16/6/27.
 */

public abstract class BaseFragment extends Fragment {
    protected int mLayoutId = 0;
    protected BaseActivity mActivity;
    protected View view;

    protected boolean isVisible;//是否可见
    private boolean isPrepared;
    private boolean isFirst = true;
    private boolean isNeedEvent = false;

    protected String TAG = getClass().getSimpleName();

    public BaseFragment(int mLayoutId) {
        this.mLayoutId = mLayoutId;
    }

    public BaseFragment(int mLayoutId, boolean isNeedEvent) {
        this.mLayoutId = mLayoutId;
        this.isNeedEvent = isNeedEvent;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(mLayoutId, null);
        this.view = v;
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
    }

    /**
     * 获取起始数据
     */
    public abstract void getData();

    /**
     * 初始化控件，并设置监听事件
     */
    protected abstract void findView(View v);

    /**
     * 页面数据更新，在开始的时候，需要判断对象是否为空，更安全；在页面刷新时，只需要调这个方法，减少冗余代码
     */
    protected abstract void refreshView();

    public void showSnack(String tip) {
        SnackBarUtils.showSnack(view, tip);
    }

    public void showSnack(String tip, String action, View.OnClickListener onClickListener) {
        SnackBarUtils.showSnack(view, tip, action, onClickListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    public void startActivity(Class<?> cls, Object data) {
        Intent intent = new Intent(mActivity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//        Intent aIntnne=(Intent)intent.clone();
        if (data != null) {
            intent.putExtra("data", (Parcelable) data);
        }
        mActivity.startActivity(intent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }

    /**
     * 获取fragment是否加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
//            onInvisible();
        }
    }

    /**
     * 懒加载
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        findView(getView());
        refreshView();
        isFirst = false;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (isNeedEvent)
            registerEventBus();
    }

    @Override
    public void onStop() {
        if (isNeedEvent)
            unRegisterEventBus();
        super.onStop();
    }

    protected void registerEventBus() {
        //子类如果需要注册eventbus，则重写此方法
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    protected void unRegisterEventBus() {
        //子类如果需要注销eventbus，则重写此方法
        EventBus.getDefault().unregister(this);
    }
}
