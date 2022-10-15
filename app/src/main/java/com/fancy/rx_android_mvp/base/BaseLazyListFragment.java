package com.fancy.rx_android_mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fancy.androidutils.recyclerviewhelper.base.BaseQuickAdapter;
import com.fancy.androidutils.recyclerviewhelper.base.BaseViewHolder;
import com.fancy.androidutils.utils.ToastUtils;
import com.fancy.rx_android_mvp.widget.CustomEmptyView;
import com.fancy.rx_android_mvp.widget.CustomErrorView;
import com.fancy.rxmvp.mvp.XBaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;


/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/5/8 0008
 * @since JDK 1.8
 */
public abstract class BaseLazyListFragment<T> extends XBaseLazyFragment {
    protected RecyclerView rvTrainingList;
    @IdRes
    private int mRecyclerViewID;
    private int mItemLayout;

    private BaseAdapter mBaseAdapter;
    public int currentPage = 0;
    public List<T> items;
    //    private LoadingDialog dialog;
    private SwipeRefreshLayout refreshLayout;
    private int mRefreshViewID;
    private CustomEmptyView emptyView;
    private int mEmptyViewID;
    private int mErrorViewID;
    private CustomErrorView errorView;


    @Override
    public void initData(Bundle savedInstanceState) {
        mRecyclerViewID = getRecyclerViewID();
        mItemLayout = getItemLayoutID();
        mRefreshViewID = getRefreshLayoutID();
        mEmptyViewID = getEmptyViewID();
        mErrorViewID = getErrorViewID();

        // Recycler view
        rvTrainingList = getRootView().findViewById(mRecyclerViewID);
        // refreshlayout

        refreshLayout = getRootView().findViewById(mRefreshViewID);
        emptyView = getRootView().findViewById(mEmptyViewID);
        errorView = getRootView().findViewById(mErrorViewID);
        rvTrainingList.setLayoutManager(new LinearLayoutManager(mActivity));
        items = new ArrayList<>();
//        dialog = new LoadingDialog(mActivity);
        requestList(true);
        if (refreshLayout != null) {
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    currentPage = 0;
                    requestList(false);
                }
            });
        }

        if (errorView != null) {
            errorView.setBtnClickListener(new CustomErrorView.BtnClickListener() {
                @Override
                public void btnClick() {
                    errorView.setVisibility(View.GONE);
                    requestList(true);
                }
            });
        }

    }


    protected BaseAdapter getAdapter() {
        return mBaseAdapter;
    }

    protected void setAdapter(boolean isLoadMore) {
        mBaseAdapter = new BaseAdapter(items);
        rvTrainingList.setAdapter(mBaseAdapter);
        if (isLoadMore) {
            mBaseAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    requestList(false);
                }
            }, rvTrainingList);
        }
    }

    protected void requestList(boolean isShowDialog) {
//        if (isShowDialog) {
//            dialog.show();
//        }
        final Observable<Response<List<T>>> observable = getRequestMethod();

    }

    public abstract Observable<Response<List<T>>> getRequestMethod();

    protected abstract int getRecyclerViewID();

    protected abstract int getItemLayoutID();

    protected abstract int getRefreshLayoutID();

    protected abstract int getEmptyViewID();

    protected abstract int getErrorViewID();

    public class BaseAdapter extends BaseQuickAdapter<T, BaseViewHolder> {


        public BaseAdapter(@Nullable List data) {
            super(mItemLayout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, T item) {
            onBindData(helper, item);
        }
    }

    protected abstract void onBindData(BaseViewHolder helper, T item);


    @Override
    public Object newP() {
        return null;
    }
}
