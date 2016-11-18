package wxy.frame.finalframe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wxy.frame.finalframe.R;
import wxy.frame.finalframe.adapter.helper.ItemTouchHelperViewHolder;
import wxy.frame.finalframe.bean.ArticleBean;

/**
 * Created by xixi on 16/6/29.
 */

public class ArticleListAdapter extends BaseRecycleAdapter<ArticleBean, ArticleListAdapter.MViewHolder> {
    private List<ArticleBean> list = new ArrayList();

    public ArticleListAdapter(Context context, List<ArticleBean> mList) {
        super(context, mList);
        list.addAll(mList);
    }

    @Override
    protected void bindDataToItemView(MViewHolder mViewHolder, ArticleBean item) {
        mViewHolder.tv_title.setText(item.getName());
        mViewHolder.tv_collect.setText(item.isCollect() ? "收藏" : "未收藏");
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MViewHolder(inflateItemView(parent, R.layout.item_article));
    }

    public class MViewHolder extends BaseRecycleAdapter.SparseArrayViewHolder implements ItemTouchHelperViewHolder {
        public TextView tv_title;
        public TextView tv_collect;

        public MViewHolder(View itemView) {
            super(itemView);
            tv_title = getView(R.id.tv_title);
            tv_collect = getView(R.id.tv_collect);
        }

        @Override
        public void onItemSelected() {

        }

        @Override
        public void onItemClear() {

        }
    }
}
