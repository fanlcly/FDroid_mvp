package com.fancy.rx_android_mvp.ui;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fancy.rx_android_mvp.widget.X5Webview;
import com.fancy.rxmvp.mvp.XBaseActivity;
import com.tencent.smtt.sdk.WebChromeClient;

import fancy.com.rx_android_mvp.R;

public class BaseWebActivity  extends XBaseActivity implements View.OnClickListener {
    private static final String TAG = "BaseWebActivity";

    protected X5Webview mWebView;
    private ProgressBar progressBar;
    private TextView titleTextView;
    private ImageView backImg;
    private TextView backTextView;
    private TextView rightBackText;

    protected String title;
    protected String url;

    private static final boolean enableProgressBar = false;
    private boolean canBack = false;


    @Override
    public int getLayoutId() {
        return R.layout.base_web_activity;
    }

    @Override
    public ViewGroup getLayoutView() {
        return null;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        //启动硬件加速
        initHardwareAccelerate();

        mWebView = findViewById(R.id.base_web_webview);
        progressBar = findViewById(R.id.base_web_progress_bar);
        titleTextView = findViewById(R.id.base_web_title_tv);
        backImg = findViewById(R.id.base_web_left_back_img);
        backImg.setOnClickListener(this);
        backTextView = findViewById(R.id.base_web_title_back_tv);
        backTextView.setOnClickListener(this);
        rightBackText = findViewById(R.id.base_web_title_right_tv);
        rightBackText.setOnClickListener(this);

        title = getIntent().getStringExtra("title");

        if(!TextUtils.isEmpty(title)){
            titleTextView.setText(title);
        }
        url = getIntent().getStringExtra("url");
        Log.i(TAG,"url=" + url);

        mWebView.loadUrl(url);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient() {
            @Override
            public void onProgressChanged(com.tencent.smtt.sdk.WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }
        });


    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.base_web_left_back_img:
            case R.id.base_web_title_back_tv:
                goBack();
                break;

            default:
                break;
        }
    }

    private void goBack() {
        if(canBack){
            if(mWebView.canGoBack()){
                mWebView.goBack();
            }else {
                finish();
            }
        }else {
            finish();
        }

    }

    public boolean isCanBack() {
        return canBack;
    }

    public void setCanBack(boolean canBack1){
        canBack = canBack1;
    }


    @Override
    public Object newP() {
        return null;
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
    }

}
