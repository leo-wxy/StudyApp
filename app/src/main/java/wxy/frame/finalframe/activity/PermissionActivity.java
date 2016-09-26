package wxy.frame.finalframe.activity;

import android.view.View;
import android.widget.TextView;

import wxy.frame.finalframe.BaseActivity;
import wxy.frame.finalframe.R;

/**
 * 权限申请界面 负责显示权限相关信息
 * Created by xixi on 16/7/7.
 */

public class PermissionActivity extends BaseActivity {

    public PermissionActivity() {
        super(R.layout.act_per);
    }

    @Override
    public void findIds() {
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }

    public void click() {
        finishAll();
    }

    @Override
    public void initViews() {

    }
}
