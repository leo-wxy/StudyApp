package wxy.frame.finalframe.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import wxy.frame.finalframe.BaseActivity;
import wxy.frame.finalframe.R;
import wxy.frame.finalframe.util.LogUtils;
import wxy.frame.finalframe.view.MyButton;
import wxy.frame.finalframe.view.MyLayout;

/**
 * Created by xixi on 16/7/7.
 */
//@BindView(R.layout.act_article_detail)
public class ArticleDetailActivity extends BaseActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout coll_tb;
    public static final String TAG = "ArticleDetailActivity";
    MyButton mbt;
    MyLayout mll;

    public ArticleDetailActivity() {
        super(R.layout.act_article_detail);
    }

    @Override
    public void findIds() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示左上角的返回键
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        coll_tb = (CollapsingToolbarLayout) findViewById(R.id.coll_tb);
        coll_tb.setTitle("文章");

        mbt = (MyButton) findViewById(R.id.mbt);
        mbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("MyButton", "onClick");
            }
        });

        mbt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.e("MyButton", "onTouch--ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.e("MyButton", "onTouch--ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.e("MyButton", "onTouch--ACTION_UP");
                        break;
                }
                return false;
            }
        });

        mll= (MyLayout) findViewById(R.id.mll);
        mll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("MyLayout", "onClick");
            }
        });
        mll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.e("MyLayout", "onTouch--ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.e("MyLayout", "onTouch--ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.e("MyLayout", "onTouch--ACTION_UP");
                        break;
                }
                return false;
            }
        });

    }


    /**
     * 优先执行，return true后事件被消费，不会向下传递
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e(TAG, "dispatchTouchEvent--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e(TAG, "dispatchTouchEvent--ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e(TAG, "dispatchTouchEvent--ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * activity dispatchTouchEvent优先获取事件，然后传递到onTouchEvent，在
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e(TAG, "onTouchEvent--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e(TAG, "onTouchEvent--ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e(TAG, "onTouchEvent--ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void initViews() {

        /**
         拓展  正确删除list中元素的方法
         Iterator<Integer> iterator = list.iterator();
         while(iterator.hasNext()){
         int i = iterator.next();
         if(i == 1){
         iterator.remove();
         }
         }
         */

    }
}
