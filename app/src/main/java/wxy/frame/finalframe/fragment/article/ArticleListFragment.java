package wxy.frame.finalframe.fragment.article;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wxy.frame.finalframe.R;
import wxy.frame.finalframe.activity.ArticleDetailActivity;
import wxy.frame.finalframe.adapter.ArticleListAdapter;
import wxy.frame.finalframe.adapter.BaseRecycleAdapter;
import wxy.frame.finalframe.adapter.click.OnRecycleItemClickListener;
import wxy.frame.finalframe.adapter.helper.SimpleItemTouchHelperCallback;
import wxy.frame.finalframe.adapter.wrapper.HeaderAndFooterWrapper;
import wxy.frame.finalframe.bean.ArticleBean;
import wxy.frame.finalframe.fragment.BaseFragment;
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
        asr_article = (AutoSwipeRefreshLayout) v.findViewById(R.id.asr_article);
        rv_article = (RecyclerView) v.findViewById(R.id.rv_article);
        rv_article.setLayoutManager(new LinearLayoutManager(mActivity));
        asr_article.autoRefresh();
        asr_article.setColorSchemeResources(R.color.colorAccent);
        asr_article.setOnRefreshListener(this);
        final List<ArticleBean> list = new ArrayList<>();
        ArticleBean articleBean = new ArticleBean();
        switch (type) {
            case 0:
                for (int i = 0; i < 3; i++) {
                    articleBean = articleBean.clone();
                    articleBean.setName("nihao" + i);
                    list.add(articleBean);
                }
                break;
            case 1:
                for (int i = 10; i < 20; i++) {
                    articleBean = articleBean.clone();
                    articleBean.setName("nihao" + i);
                    list.add(articleBean);
                }
                break;
            case 2:
                for (int i = 20; i < 30; i++) {
                    articleBean = articleBean.clone();
                    articleBean.setName("nihao" + i);
                    list.add(articleBean);
                }
                break;
            case 3:
                for (int i = 30; i < 40; i++) {
                    articleBean = articleBean.clone();
                    articleBean.setName("nihao" + i);
                    list.add(articleBean);
                }
                break;
        }

        listAdapter = new ArticleListAdapter(mActivity, list);
        final HeaderAndFooterWrapper mWrapper = new HeaderAndFooterWrapper(listAdapter);
        TextView tv = new TextView(mActivity);
        tv.setText("哈哈");
        mWrapper.addHeaderView(tv);

        final int headCount = mWrapper.getHeadersCount();
        rv_article.setAdapter(mWrapper);

        listAdapter.setOnDragListener(new BaseRecycleAdapter.OnDragListener() {
            @Override
            public void onItemMove(int fromPosition, int toPosition) {
                Collections.swap(list, fromPosition - headCount, toPosition - headCount);
                mWrapper.notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onItemDismiss(int position) {
                list.remove(position - headCount);
                mWrapper.notifyItemRemoved(position);
                mWrapper.notifyItemRangeChanged(position, list.size() - position);
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(listAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rv_article);

        rv_article.addOnItemTouchListener(new OnRecycleItemClickListener(rv_article) {
            @Override
            public void onItemClick(int position) {
                startActivity(ArticleDetailActivity.class, list.get(position - headCount));
            }
        });

    }

    @Override
    protected void refreshView() {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                asr_article.setRefreshing(false);
            }
        }, 3000);
    }

    @Override
    public void onRefresh() {
        refreshView();
    }
}
