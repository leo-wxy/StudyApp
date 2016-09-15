package wxy.frame.finalframe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wxy.frame.finalframe.R;

/**
 * Created by xixi on 16/6/29.
 */

public class ArticleListAdapter extends BaseRecycleAdapter<Integer, ArticleListAdapter.MViewHolder> {
    private List list;

    public ArticleListAdapter(Context context, List mList) {
        super(context, mList);
        this.list = list;
    }

    @Override
    protected void bindDataToItemView(MViewHolder mViewHolder, Integer item) {
        mViewHolder.tv_title.setText("nihao " + item);
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MViewHolder(inflateItemView(parent, R.layout.item_article));
    }

    public class MViewHolder extends BaseRecycleAdapter.SparseArrayViewHolder {
        public TextView tv_title;

        public MViewHolder(View itemView) {
            super(itemView);
            tv_title = getView(R.id.tv_title);
        }

    }
}
