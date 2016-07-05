package wxy.frame.finalframe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import wxy.frame.finalframe.fragment.article.ArticleListFragment;

/**
 * Created by xixi on 16/6/29.
 */

public class ArticleFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;

    public ArticleFragmentAdapter(FragmentManager fm, List<String> mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return ArticleListFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
