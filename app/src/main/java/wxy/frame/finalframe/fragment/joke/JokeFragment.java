package wxy.frame.finalframe.fragment.joke;

import android.view.View;
import android.widget.TextView;

import wxy.frame.finalframe.R;
import wxy.frame.finalframe.fragment.BaseFragment;
import wxy.frame.finalframe.widgets.MyDragViewLayout;

/**
 * Created by xixi on 16/6/27.
 */

public class JokeFragment extends BaseFragment {
    TextView tv_set;
    MyDragViewLayout mdv;

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
        tv_set = (TextView) v.findViewById(R.id.tv_set);
        mdv = (MyDragViewLayout) v.findViewById(R.id.mdv);
        tv_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.err.println("ja");
            }
        });

    }

    @Override
    protected void refreshView() {

    }
}
