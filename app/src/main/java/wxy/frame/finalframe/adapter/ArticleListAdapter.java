package wxy.frame.finalframe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wxy.frame.finalframe.R;
import wxy.frame.finalframe.adapter.helper.ItemTouchHelperViewHolder;

/**
 * Created by xixi on 16/6/29.
 */

public class ArticleListAdapter extends BaseRecycleAdapter<Integer, ArticleListAdapter.MViewHolder> {
    private List list = new ArrayList();

    public ArticleListAdapter(Context context, List mList) {
        super(context, mList);
        list.addAll(mList);
    }

    @Override
    protected void bindDataToItemView(MViewHolder mViewHolder, Integer item) {
        mViewHolder.tv_title.setText("nihao " + item);
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MViewHolder(inflateItemView(parent, R.layout.item_article));
    }

    public class MViewHolder extends BaseRecycleAdapter.SparseArrayViewHolder implements ItemTouchHelperViewHolder {
        public TextView tv_title;

        public MViewHolder(View itemView) {
            super(itemView);
            tv_title = getView(R.id.tv_title);
        }

        @Override
        public void onItemSelected() {

        }

        @Override
        public void onItemClear() {

        }
    }
}
