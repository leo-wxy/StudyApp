package wxy.frame.finalframe.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import wxy.frame.finalframe.R;
import wxy.frame.finalframe.adapter.ArticleFragmentAdapter;

/**
 * Created by xixi on 16/6/27.
 */

public class ArticleFragment extends BaseFragment {

    public boolean isAdded = false;
    //    RecyclerView recyclerView;
//    ArticleListAdapter listAdapter;
    List<Integer> list = new ArrayList<>();
    ViewPager vp_article;
    ArticleFragmentAdapter fragmentAdapter;

    public ArticleFragment() {
        super(R.layout.act_article);
    }

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public void getData() {

    }

    @Override
    protected void findView(View v) {
        isAdded = true;

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        vp_article = (ViewPager) v.findViewById(R.id.vp_article);
        List<String> titles = new ArrayList<>();
        titles.add("Android");
        titles.add("iOS");
        titles.add("前端");
        titles.add("瞎推荐");
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ArticleListFragment.newInstance(0));
        fragments.add(ArticleListFragment.newInstance(1));
        fragments.add(ArticleListFragment.newInstance(2));
        fragments.add(ArticleListFragment.newInstance(3));
        fragmentAdapter =
                new ArticleFragmentAdapter(mActivity.getSupportFragmentManager(), fragments, titles);
        vp_article.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(vp_article);
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);


//        recyclerView = (RecyclerView) v.findViewById(rv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//
//        });
//
//        for (int i = 0; i < 10; i++) {
//            list.add(i);
//        }
//        listAdapter = new ArticleListAdapter(mActivity, list);
//        recyclerView.setAdapter(listAdapter);

    }

    @Override
    protected void refreshView() {

    }
}
