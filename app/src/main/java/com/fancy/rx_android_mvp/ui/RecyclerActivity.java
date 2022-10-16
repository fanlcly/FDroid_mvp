package com.fancy.rx_android_mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.fancy.androidutils.recyclerviewhelper.base.BaseQuickAdapter;
import com.fancy.androidutils.recyclerviewhelper.base.BaseViewHolder;
import com.fancy.androidutils.utils.ToastUtils;

import java.util.List;

import fancy.com.rx_android_mvp.R;
import com.fancy.rx_android_mvp.base.BaseListActivity;
import com.fancy.rx_android_mvp.entity.ListEntity;
import io.reactivex.Observable;
import retrofit2.Response;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/4/23 0023
 * @since JDK 1.8
 */
public class RecyclerActivity extends BaseListActivity<ListEntity> {

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
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
        return R.layout.item_single_array;
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
        helper.setText(R.id.text_view, item.getTitle());
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
