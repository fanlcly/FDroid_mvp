package com.fancy.rx_android_mvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fancy.com.rx_android_mvp.R;


/**
 * class describe
 *
 * @author fanlei
 * @version 1.0 2017/7/24 17:06
 * @since JDK 1.8
 */
public class CustomErrorView extends LinearLayout implements View.OnClickListener{

    private BtnClickListener btnClickListener;
    private TextView tvErrorInfo;

    public CustomErrorView(Context context) {
        super(context);
    }

    public CustomErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_custom_error, this);
        ImageView ivErrorImage = findViewById(R.id.error_image);
        tvErrorInfo = findViewById(R.id.error_message_info);
        TextView tvRetry = findViewById(R.id.retry);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomErrorView);
        Drawable src = typedArray.getDrawable(R.styleable.CustomErrorView_error_src);
        String text = typedArray.getString(R.styleable.CustomErrorView_error_text);
        String btnText = typedArray.getString(R.styleable.CustomErrorView_btn_text);

        if (src == null) {
            ivErrorImage.setImageResource(R.mipmap.icon_default_net);
        } else {
            ivErrorImage.setImageDrawable(src);
        }

        if (TextUtils.isEmpty(btnText)) {
            tvRetry.setText("重新加载");
        } else {
            tvRetry.setText(btnText);
        }

        tvErrorInfo.setText(text);
        tvRetry.setOnClickListener(this);
        typedArray.recycle();

    }


    /**
     * 设置errorInfo
     * @param errorInfo
     */
    public void setErrorInfo(String errorInfo) {
        tvErrorInfo.setText(errorInfo);
    }


    @Override
    public void onClick(View v) {
        if (btnClickListener!=null ){
            btnClickListener.btnClick();
        }
    }


    public void setBtnClickListener(BtnClickListener btnClickListener) {
        this.btnClickListener = btnClickListener;
    }

    public interface BtnClickListener {
        void btnClick();
    }

}
