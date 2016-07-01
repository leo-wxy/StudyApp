package wxy.frame.finalframe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import wxy.frame.finalframe.util.SnachBarUtils;

/**
 * Created by xixi on 16/6/29.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private int mLayoutId;
    private Context context;
    public LayoutInflater mLayoutInflater;
    private View view;

    public BaseActivity(int mLayoutId) {
        this.mLayoutId = mLayoutId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mLayoutId);
        context = this;
        mLayoutInflater = LayoutInflater.from(this);
        view = mLayoutInflater.inflate(mLayoutId, null);
        findIds();
        initViews();
    }

    public abstract void findIds();

    public abstract void initViews();

    public void showSnack(String tip) {
        SnachBarUtils.showSnack(view, tip);
    }

    public void showSnack(String tip, String action, View.OnClickListener onClickListener) {
        SnachBarUtils.showSnack(view, tip, action, onClickListener);
    }

    public void startActivity(Class<?> cls, Object data) {
        Intent intent = new Intent(this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (data != null) {
            intent.putExtra("data", (Parcelable) data);
            startActivity(intent);
        }
    }
}
