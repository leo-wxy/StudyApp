package wxy.frame.finalframe.fragment.article;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import wxy.frame.finalframe.R;
import wxy.frame.finalframe.adapter.ArticleFragmentAdapter;
import wxy.frame.finalframe.fragment.BaseFragment;

/**
 * Created by xixi on 16/6/27.
 */

public class ArticleFragment extends BaseFragment {

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

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        vp_article = (ViewPager) v.findViewById(R.id.vp_article);
        vp_article.setOffscreenPageLimit(4);
        List<String> titles = new ArrayList<>();
        titles.add("Android");
        titles.add("iOS");
        titles.add("前端");
        titles.add("瞎推荐");
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));

        fragmentAdapter =
                new ArticleFragmentAdapter(mActivity.getSupportFragmentManager(), titles);
        vp_article.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(vp_article);

    }

    @Override
    protected void refreshView() {

    }
}
