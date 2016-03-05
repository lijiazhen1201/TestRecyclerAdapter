package com.zhenmei.testrecycleradapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView的万能适配器
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据源
     */
    private List<T> list;

    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;

    /**
     * item的布局id
     */
    private int layoutId;

    /**
     * 构造器
     *
     * @param mContext
     * @param list
     * @param layoutId
     */
    public BaseRecyclerAdapter(Context mContext, List<T> list, int layoutId) {
        super();
        this.mContext = mContext;
        this.list = list;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 设置数据源
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 加载item布局，生成ViewHolder并返回
         */
        View itemView = mInflater.inflate(layoutId, parent, false);
        BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(itemView);
        return holder;
    }

    /**
     * 绑定ViewHolder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final BaseRecyclerViewHolder holder, int position) {
        init(holder, list.get(position));

        /**
         * item单击事件监听
         */
        if (onItemClickLinster != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLinster.onItemClick(
                            holder.itemView, holder.getLayoutPosition());
                }
            });
        }

        /**
         * item长按事件监听
         */
        if (onItemLongClickLinster != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickLinster.onItemLongClick(
                            holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }

    /**
     * item的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 添加item
     *
     * @param t
     * @param position
     */
    public void addItem(T t, int position) {
        list.add(position, t);
        notifyItemInserted(position);
    }

    /**
     * 删除item
     *
     * @param position
     */
    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 重写此方法，在里面初始化item控件和设置item控件中的值
     *
     * @param baseRecyclerViewHolder
     * @param t
     */
    public abstract void init(BaseRecyclerViewHolder baseRecyclerViewHolder, T t);

    /**
     * 单击事件监听的回调接口
     */
    public interface OnItemClickLinster {
        void onItemClick(View view, int position);
    }

    private OnItemClickLinster onItemClickLinster;

    public void setOnItemClickLinster(OnItemClickLinster onItemClickLinster) {
        this.onItemClickLinster = onItemClickLinster;
    }

    /**
     * 长按事件监听的回调接口
     */
    public interface OnItemLongClickLinster {
        void onItemLongClick(View view, int position);
    }

    private OnItemLongClickLinster onItemLongClickLinster;

    public void setOnItemLongClickLinster(OnItemLongClickLinster onItemLongClickLinster) {
        this.onItemLongClickLinster = onItemLongClickLinster;
    }


}
