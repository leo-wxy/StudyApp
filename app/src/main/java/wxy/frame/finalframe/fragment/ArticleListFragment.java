package wxy.frame.finalframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import wxy.frame.finalframe.R;
import wxy.frame.finalframe.adapter.ArticleListAdapter;
import wxy.frame.finalframe.widgets.AutoSwipeRefreshLayout;

/**
 * Created by xixi on 16/6/27.
 */

public class ArticleListFragment extends BaseFragment {
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
        List<Integer> list = new ArrayList<>();
        switch (type) {
            case 0:
                for (int i = 0; i < 10; i++) {
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
        rv_article.setAdapter(listAdapter);
    }

    @Override
    protected void refreshView() {

    }
}
