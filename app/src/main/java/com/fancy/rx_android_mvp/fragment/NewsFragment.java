package com.fancy.rx_android_mvp.fragment;

import android.content.Entity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fancy.com.rx_android_mvp.R;

import com.fancy.androidutils.recyclerviewhelper.base.BaseQuickAdapter;
import com.fancy.androidutils.utils.ToastUtils;
import com.fancy.rx_android_mvp.adapter.GirlAdapter;
import com.fancy.rx_android_mvp.adapter.NewsAdapter;
import com.fancy.rx_android_mvp.entity.GirlEntity;
import com.fancy.rx_android_mvp.entity.NewsEntity;
import com.fancy.rx_android_mvp.entity.NewsModel;
import com.fancy.rx_android_mvp.net.Api;
import com.fancy.rx_android_mvp.net.BaseModle;
import com.fancy.rx_android_mvp.net.MyCallBack;
import com.fancy.rxmvp.mvp.XBaseLazyFragment;
import com.fancy.rxmvp.net.HttpClient;
import com.fancy.rxmvp.net.RxBaseCallBack;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * 新闻
 *
 * @author fanlei
 * @version 1.0 2019/4/18 0018
 * @since JDK 1.8
 */
public class NewsFragment extends XBaseLazyFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout refreshLayout;

    List<NewsEntity> items;
    private NewsAdapter newsAdapter;
    private int currentPage = 0;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        items = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        setAdapter();
        newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
        newsAdapter = new NewsAdapter(items);
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
        final Observable<Response<NewsModel>> observable = getRequestMethod();
        HttpClient.getInstance().getObservable(observable.compose(this.bindToLifecycle()))
                .subscribe(new RxBaseCallBack<NewsModel>(mActivity) {

                    @Override
                    public void onSuc(NewsModel newsModel) {
                        refreshLayout.setRefreshing(false);
                        if (currentPage == 0) {
                            items.clear();
                        }
                        newsAdapter.addData(newsModel.getResult().getData());
                        if (newsModel.getResult().getData().size() < 20) {
                            newsAdapter.loadMoreEnd();
                        } else {
                            newsAdapter.loadMoreComplete();
                            currentPage++;
                        }
                    }

                    @Override
                    public void onFail(String message, int failCode) {
                        refreshLayout.setRefreshing(false);
                        if (currentPage == 0) {
                            ToastUtils.init(mActivity).show(message);
                        } else {
                            newsAdapter.loadMoreFail();
                        }
                    }

                });

    }


    public Observable<Response<NewsModel>> getRequestMethod() {
        return HttpClient.getInstance().getObservable(Api.getApiService().getNews("top","3dc86b09a2ee2477a5baa80ee70fcdf5"));
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
