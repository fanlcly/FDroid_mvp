package com.fancy.rx_android_mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fancy.androidutils.recyclerviewhelper.base.BaseQuickAdapter;
import com.fancy.androidutils.recyclerviewhelper.base.BaseViewHolder;
import com.fancy.androidutils.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import com.fancy.rx_android_mvp.widget.CustomEmptyView;
import com.fancy.rx_android_mvp.widget.CustomErrorView;
import com.fancy.rxmvp.mvp.XBaseActivity;
import com.fancy.rxmvp.net.HttpClient;
import com.fancy.rxmvp.net.RxBaseCallBack;
import io.reactivex.Observable;
import retrofit2.Response;

/**
 * 含列表的数据结构的base类
 *
 * @author fanlei
 * @version 1.0 2019/4/23 0023
 * @since JDK 1.8
 */
public abstract class BaseListActivity<T> extends XBaseActivity {
    private Context mContext;
    private RecyclerView rvTrainingList;
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
        rvTrainingList = findViewById(mRecyclerViewID);
        // refreshlayout
        refreshLayout = findViewById(mRefreshViewID);
        emptyView = findViewById(mEmptyViewID);
        errorView = findViewById(mErrorViewID);
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
        HttpClient.getInstance().getObservable(observable.compose(this.<Response<List<T>>>bindToLifecycle()))
                .subscribe(new RxBaseCallBack<List<T>>(mActivity) {
                    @Override
                    public void onSuc(List<T> response) {
                        if (refreshLayout != null) {
                            refreshLayout.setRefreshing(false);
                        }
                        if (currentPage == 0) {
                            items.clear();
                        }
                        // dialog.dismiss();
                        List<T> list = response;

                        if (list == null) {
                            return;
                        }
                        mBaseAdapter.addData(list);
                        if (list.size() < 10) {
                            mBaseAdapter.loadMoreEnd();
                        } else {
                            mBaseAdapter.loadMoreComplete();
                            currentPage++;
                        }

                        if (emptyView != null) {
                            if (items != null && items.size() == 0) {
                                mBaseAdapter.loadMoreEnd();
                                emptyView.setVisibility(View.VISIBLE);
                            } else {
                                emptyView.setVisibility(View.GONE);
                            }
                        }

                    }

                    @Override
                    public void onFail(String message, int failCode) {
                        if (refreshLayout != null) {
                            refreshLayout.setRefreshing(false);
                        }
//                dialog.dismiss();
                        if (currentPage == 0) {
                            if (errorView != null) {
                                errorView.setVisibility(View.VISIBLE);
                            } else {
                                ToastUtils.init(mActivity).show(message);
                            }
                        } else {
                            mBaseAdapter.loadMoreFail();
                            ToastUtils.init(mActivity).show(message);
                        }
                    }

                });

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
