package com.zhenmei.testrecycleradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * RecyclerView的万能ViewHolder
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    /**
     * 用来缓存view
     */
    private SparseArray<View> views;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<View>();

    }

    /**
     * 通过id获取控件，如果没有从布局中找，然后存到views中
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }


}
