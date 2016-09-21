package wxy.frame.finalframe;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import wxy.frame.finalframe.bean.ResultBean;
import wxy.frame.finalframe.util.SnackBarUtils;

/**
 * Created by xixi on 16/6/29.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private int mLayoutId;
    private Context context;
    public LayoutInflater mLayoutInflater;
    public Bundle savedInstanceState;

    public BaseActivity(int mLayoutId) {
        this.mLayoutId = mLayoutId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mLayoutId);
        context = this;
        mLayoutInflater = LayoutInflater.from(this);
        this.savedInstanceState = savedInstanceState;
        findIds();
        initViews();
    }

    public abstract void findIds();

    public abstract void initViews();

    public void showSnack(View view, String tip) {
        SnackBarUtils.showSnack(view, tip);
    }

    public void showSnack(View view, String tip, String action, View.OnClickListener onClickListener) {
        SnackBarUtils.showSnack(view, tip, action, onClickListener);
    }

    public void startActivity(Class<?> cls, Object data) {
        Intent intent = new Intent(this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//        Intent aIntnne=(Intent)intent.clone();
        if (data != null) {
            intent.putExtra("data", (Parcelable) data);
            startActivity(intent);
        }
    }

//获取parcel 数据实例
//    public void getIntentData(Intent intent, String name) {
//        byte[] datas = intent.getByteArrayExtra(name);
//        if (datas != null) {
//            Parcel in = Parcel.obtain();
//            in.unmarshall(datas, 0, datas.length);
//            in.setDataPosition(0);
//            ResultBean resultBean = ResultBean.CREATOR.createFromParcel(in);
//            in.recycle();
//        }
//    }

    /**
     * 申请权限检测
     * 目前只需要 存储空间权限 后续需添加 位置权限
     */
    public boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

}
