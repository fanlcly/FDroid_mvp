package com.fancy.rx_android_mvp.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;

import fancy.com.rx_android_mvp.R;
import io.reactivex.Observable;
import retrofit2.Response;

import com.fancy.androidutils.recyclerviewhelper.base.BaseQuickAdapter;
import com.fancy.androidutils.recyclerviewhelper.base.BaseViewHolder;
import com.fancy.androidutils.recyclerviewhelper.decoration.LinearDividerItemDecoration;
import com.fancy.androidutils.utils.ToastUtils;
import com.fancy.rx_android_mvp.base.BaseLazyListFragment;
import com.fancy.rx_android_mvp.entity.ListEntity;
import com.fancy.rx_android_mvp.net.Api;

import java.util.List;

/**
 * 首页界面
 *
 * @author fanlei
 * @version 1.0 2019/4/18 0018
 * @since JDK 1.8
 */
public class JokeFragment extends BaseLazyListFragment<ListEntity> {

    public static JokeFragment newInstance() {
        return new JokeFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        rvTrainingList.addItemDecoration(new LinearDividerItemDecoration(mActivity, R.color.divide_c6c6c6, 2));
        setAdapter(true);
        BaseAdapter b = getAdapter();
        b.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ToastUtils.init(mActivity).show(i);
            }
        });
    }


    @Override
    public Observable<Response<List<ListEntity>>> getRequestMethod() {
        return null;
    }

    @Override
    protected int getRecyclerViewID() {
        return R.id.recycler_view;
    }

    @Override
    protected int getItemLayoutID() {
        return R.layout.item_home_view;
    }

    @Override
    protected int getRefreshLayoutID() {
        return 0;
    }

    @Override
    protected int getEmptyViewID() {
        return 0;
    }

    @Override
    protected int getErrorViewID() {
        return 0;
    }

    @Override
    protected void onBindData(BaseViewHolder helper, ListEntity item) {
        helper.setText(R.id.tv_name, item.getTitle());
        helper.setText(R.id.tv_content,  Html.fromHtml(item.getContent()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    public ViewGroup getLayoutView() {
        return null;
    }

}
