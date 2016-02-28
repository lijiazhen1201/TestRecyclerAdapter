package com.zhenmei.testrecycleradapter.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhenmei.testrecycleradapter.R;
import com.zhenmei.testrecycleradapter.adapter.BaseRecyclerAdapter;
import com.zhenmei.testrecycleradapter.adapter.BaseRecyclerViewHolder;
import com.zhenmei.testrecycleradapter.bean.User;

import java.util.ArrayList;
import java.util.List;

import view.DividerItemDecoration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private RecyclerView mRecyclerView;
    private List<User> list;
    private MyAdapter adapter;

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        /**
         * 设置布局管理者
         */
        /**
         * LinearLayoutManager竖直或横向的ListView
         * LinearLayoutManager有两个构造器
         * 1.传入上下文直接创建，默认创建竖直的ListView
         * public LinearLayoutManager(Context context) {this(context, VERTICAL, false);}
         * 之后可以调用LinearLayoutManager的setOrientation方法修改滑动方向
         * 竖直LinearLayoutManager.VERTICAL
         * 横向LinearLayoutManager.HORIZONTAL
         * 2.传入三个参数，第一个参数是上下文，第二个参数是滑动方向，第三个参数是数据的顺序，如果设置true，数据将反过来设置
         * public LinearLayoutManager(Context context, int orientation, boolean reverseLayout)
         * {setOrientation(orientation);setReverseLayout(reverseLayout);}
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        /**
         * GridLayoutManager竖直或横向的GridView
         * GridLayoutManager有两个构造器
         * 1.传入两个参数，第一个参数是上下文，第二个参数是行或列的数量
         * public GridLayoutManager(Context context, int spanCount) {super(context);setSpanCount(spanCount);}
         * 2.传入四个参数，第一个参数是上下文，第二个参数是行或列的数量，第三个参数是滑动方向，第四个参数是数据的顺序，如果设置true，数据将反过来设置
         * public GridLayoutManager(Context context, int spanCount, int orientation,boolean reverseLayout)
         * {super(context, orientation, reverseLayout);setSpanCount(spanCount);}
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);


        mRecyclerView.setLayoutManager(linearLayoutManager);

        /**
         * 初始化分割线，设置竖直
         */
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(divider);

        /**
         * 设置动画
         */
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        /**
         * 初始化数据源
         */
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            User user = new User("张三" + i, i);
            list.add(user);
        }

        /**
         * 设置适配器
         */
        adapter = new MyAdapter(this, list, R.layout.item_layout);
        mRecyclerView.setAdapter(adapter);

        /**
         * item单击监听
         */
        adapter.setOnItemClickLinster(new BaseRecyclerAdapter.OnItemClickLinster() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * item长按监听
         */
        adapter.setOnItemLongClickLinster(new BaseRecyclerAdapter.OnItemLongClickLinster() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "" + list.get(position).getAge(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 自定义适配器
     */
    public class MyAdapter extends BaseRecyclerAdapter<User> {

        public MyAdapter(Context mContext, List<User> list, int layoutId) {
            super(mContext, list, layoutId);
        }

        @Override
        public void init(BaseRecyclerViewHolder baseRecyclerViewHolder, User user) {
            TextView tvName = baseRecyclerViewHolder.getView(R.id.tv_name);
            TextView tvAge = baseRecyclerViewHolder.getView(R.id.tv_age);
            tvName.setText(user.getName());
            tvAge.setText(user.getAge() + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.shu_listview_zheng:
                LinearLayoutManager m1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(m1);
                break;
            case R.id.shu_listview_fan:
                LinearLayoutManager m2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
                mRecyclerView.setLayoutManager(m2);
                break;
            case R.id.heng_listview_zheng:
                LinearLayoutManager m3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(m3);
                break;
            case R.id.heng_listview_fan:
                LinearLayoutManager m4 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
                mRecyclerView.setLayoutManager(m4);
                break;
            case R.id.shu_gridview_zheng:
                GridLayoutManager m5 = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(m5);
                break;
            case R.id.shu_gridview_fan:
                GridLayoutManager m6 = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, true);
                mRecyclerView.setLayoutManager(m6);
                break;
            case R.id.heng_girdview_zheng:
                GridLayoutManager m7 = new GridLayoutManager(this, 5, GridLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(m7);
                break;
            case R.id.heng_girdview_fan:
                GridLayoutManager m8 = new GridLayoutManager(this, 5, GridLayoutManager.HORIZONTAL, true);
                mRecyclerView.setLayoutManager(m8);
                break;
//            case R.id.shu_pubu:
//                StaggeredGridLayoutManager m9 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
//                mRecyclerView.setLayoutManager(m9);
//                break;
//            case R.id.heng_pubu:
//                StaggeredGridLayoutManager m10 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
//                mRecyclerView.setLayoutManager(m10);
//                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
