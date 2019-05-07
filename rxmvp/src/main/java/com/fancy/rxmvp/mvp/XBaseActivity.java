package com.fancy.rxmvp.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.Unbinder;
import com.fancy.rxmvp.MvpConfig;
import com.fancy.rxmvp.event.BusProvider;
import com.fancy.rxmvp.utils.KnifeHelper;

/**
 * activity基类
 *
 * @author fanlei
 * @version 1.0 2019/4/16 0016
 * @since JDK 1.8
 */
public abstract class XBaseActivity<P extends IPresent> extends RxAppCompatActivity implements IView<P> {
    private P p;
    protected Activity mActivity;
    private Unbinder unbinder;
    private VDelegate vDelegate;
    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        getP();

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(null);
            bindEvent();
        }

        initData(savedInstanceState);

    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeHelper.bind(this);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateImpl.create(mActivity);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
        }

        if (p != null) {
            if (!p.hasV()) {
                p.attachV(this);
            }
        }


        return p;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getvDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(MvpConfig.DEV);
        return rxPermissions;
    }


    @Override
    public void bindEvent() {

    }


}
