package wxy.frame.finalframe.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import wxy.frame.finalframe.BaseActivity;
import wxy.frame.finalframe.R;

/**
 * Created by xixi on 16/7/7.
 */

public class ArticleDetailActivity extends BaseActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout coll_tb;

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
