package wxy.frame.finalframe.fragment.article;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wxy.frame.finalframe.R;
import wxy.frame.finalframe.activity.PermissionActivity;
import wxy.frame.finalframe.adapter.ArticleListAdapter;
import wxy.frame.finalframe.adapter.BaseRecycleAdapter;
import wxy.frame.finalframe.adapter.wrapper.HeaderAndFooterWrapper;
import wxy.frame.finalframe.fragment.BaseFragment;
import wxy.frame.finalframe.util.LogUtils;
import wxy.frame.finalframe.widgets.AutoSwipeRefreshLayout;

/**
 * Created by xixi on 16/6/27.
 */

public class ArticleListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private int type;//0 android  1 ios  2 前端 3 瞎看
    private AutoSwipeRefreshLayout asr_article;
    private RecyclerView rv_article;
    ArticleListAdapter listAdapter;

    public ArticleListFragment() {
        super(R.layout.act_article_list);
    }

    public static ArticleListFragment newInstance(int type) {
        ArticleListFragment articleListFragment = new ArticleListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        articleListFragment.setArguments(bundle);
        return articleListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");//获取传递过来的类型值

    }

    @Override
    public void getData() {

    }

    @Override
    protected void findView(View v) {
        System.err.println("hahahahahah");
        asr_article = (AutoSwipeRefreshLayout) v.findViewById(R.id.asr_article);
        rv_article = (RecyclerView) v.findViewById(R.id.rv_article);
        rv_article.setLayoutManager(new LinearLayoutManager(mActivity));
        asr_article.setColorSchemeResources(R.color.colorAccent);
        asr_article.setOnRefreshListener(this);
        List<Integer> list = new ArrayList<>();
        switch (type) {
            case 0:
                for (int i = 0; i < 3; i++) {
                    list.add(i);
                }
                break;
            case 1:
                for (int i = 10; i < 20; i++) {
                    list.add(i);
                }
                break;
            case 2:
                for (int i = 20; i < 30; i++) {
                    list.add(i);
                }
                break;
            case 3:
                for (int i = 30; i < 40; i++) {
                    list.add(i);
                }
                break;
        }

        listAdapter = new ArticleListAdapter(mActivity, list);
        HeaderAndFooterWrapper mWrapper = new HeaderAndFooterWrapper(listAdapter);
        TextView tv = new TextView(mActivity);
        tv.setText("哈哈");
        mWrapper.addHeaderView(tv);

        rv_article.setAdapter(mWrapper);
        rv_article.addOnScrollListener(new RecyclerView.OnScrollListener() {//上滑事件监听
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        listAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(int i) {
                //TODO 添加跳转至文章详情的功能 暂定跳转activity和bottomsheet
                LogUtils.e("文章跳转");
//                showSnack("跳转至文章" + i);//用以调试snachbar的功能
                mActivity.startActivity(PermissionActivity.class);
            }
        });
    }

    @Override
    protected void refreshView() {
        asr_article.setRefreshing(true);

    }

    @Override
    public void onRefresh() {
        refreshView();
    }
}
