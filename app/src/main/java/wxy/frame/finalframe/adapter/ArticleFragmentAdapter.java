package wxy.frame.finalframe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedHashMap;
import java.util.List;

import wxy.frame.finalframe.fragment.article.ArticleListFragment;

/**
 * Created by xixi on 16/6/29.
 */

public class ArticleFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;
    private LinkedHashMap<String, Fragment> fragmentMap = new LinkedHashMap<>();

    public ArticleFragmentAdapter(FragmentManager fm, List<String> mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        if (!fragmentMap.containsKey(mTitles.get(position)))
            fragmentMap.put(mTitles.get(position), ArticleListFragment.newInstance(position));
        return fragmentMap.get(mTitles.get(position));
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
