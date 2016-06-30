package wxy.frame.finalframe.fragment;

import android.view.View;

import wxy.frame.finalframe.R;

/**
 * Created by xixi on 16/6/27.
 */

public class JokeFragment extends BaseFragment {
    public boolean isAdded = false;

    public JokeFragment() {
        super(R.layout.act_joke);
    }

    public static JokeFragment newInstance() {
        return new JokeFragment();
    }

    @Override
    public void getData() {

    }

    @Override
    protected void findView(View v) {
        isAdded = true;

    }

    @Override
    protected void refreshView() {

    }
}
