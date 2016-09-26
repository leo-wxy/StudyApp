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

import wxy.frame.finalframe.BaseActivity;
import wxy.frame.finalframe.util.SnackBarUtils;

/**
 * Created by xixi on 16/6/27.
 */

public abstract class BaseFragment extends Fragment {
    protected int mLayoutId = 0;
    protected BaseActivity mActivity;
    protected View view;

    public BaseFragment(int mLayoutId) {
        this.mLayoutId = mLayoutId;
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
        findView(getView());
        refreshView();
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
}
