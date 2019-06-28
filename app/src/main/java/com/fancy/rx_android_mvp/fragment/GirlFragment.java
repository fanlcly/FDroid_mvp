package com.fancy.rx_android_mvp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.view.View;

import butterknife.BindView;
import fancy.com.rx_android_mvp.R;
import io.reactivex.Observable;
import retrofit2.Response;

import com.fancy.androidutils.recyclerviewhelper.base.BaseQuickAdapter;
import com.fancy.androidutils.recyclerviewhelper.base.BaseViewHolder;
import com.fancy.androidutils.recyclerviewhelper.decoration.LinearDividerItemDecoration;
import com.fancy.androidutils.utils.ToastUtils;
import com.fancy.rx_android_mvp.adapter.GirlAdapter;
import com.fancy.rx_android_mvp.base.BaseLazyListFragment;
import com.fancy.rx_android_mvp.entity.GirlEntity;
import com.fancy.rx_android_mvp.entity.ListEntity;
import com.fancy.rx_android_mvp.net.Api;
import com.fancy.rx_android_mvp.net.BaseModle;
import com.fancy.rx_android_mvp.net.MyCallBack;
import com.fancy.rxmvp.mvp.XBaseLazyFragment;
import com.fancy.rxmvp.net.HttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/4/18 0018
 * @since JDK 1.8
 */
public class GirlFragment extends XBaseLazyFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout refreshLayout;

    List<GirlEntity> items;
    private GirlAdapter girlAdapter;
    private int currentPage = 0;

    public static GirlFragment newInstance() {
        return new GirlFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        items = new ArrayList<>();
        StaggeredGridLayoutManager recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        setAdapter();
        girlAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ToastUtils.init(mActivity).show(i);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                requestData();
            }
        });
        requestData();
    }

    private void setAdapter() {
        girlAdapter = new GirlAdapter(items);
        recyclerView.setAdapter(girlAdapter);
        girlAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                requestData();
            }
        }, recyclerView);
    }

    /**
     * 请求数据方法
     */
    private void requestData() {
        final Observable<Response<BaseModle<List<GirlEntity>>>> observable = getRequestMethod();
        HttpClient.getInstance().getObservable(observable.compose(this.bindToLifecycle()))
                .subscribe(new MyCallBack<List<GirlEntity>>(mActivity) {
                    @Override
                    protected void onSuccess(List<GirlEntity> girlEntities) {
                        refreshLayout.setRefreshing(false);
                        if (currentPage == 0) {
                            items.clear();
                        }
                        girlAdapter.addData(girlEntities);
                        if (girlEntities.size() < 20) {
                            girlAdapter.loadMoreEnd();
                        } else {
                            girlAdapter.loadMoreComplete();
                            currentPage++;
                        }
                    }


                    @Override
                    public void onFail(String message, int failCode) {
                        refreshLayout.setRefreshing(false);
                        if (currentPage == 0) {
                            ToastUtils.init(mActivity).show(message);
                        } else {
                            girlAdapter.loadMoreFail();
                        }
                    }

                });

    }


    public Observable<Response<BaseModle<List<GirlEntity>>>> getRequestMethod() {
        return HttpClient.getInstance().getObservable(Api.getApiService().getGirlImage("baiduimage","性感"));
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_recycler;
    }

    @Override
    public Object newP() {
        return null;
    }

}
