package com.fancy.rx_android_mvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fancy.com.rx_android_mvp.R;


/**
 * 自定义空布局
 *
 * @author fanlei
 * @version 1.0 2017/7/21 14:26
 * @since JDK 1.8
 */
public class CustomEmptyView extends LinearLayout {


    private TextView tvEmptyInfo;

    public CustomEmptyView(Context context) {
        super(context);
    }

    public CustomEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_custom_empty, this);
        ImageView ivEmptyImage = findViewById(R.id.empty_image);
        tvEmptyInfo = findViewById(R.id.empty_message_info);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomEmptyView);
        Drawable src = typedArray.getDrawable(R.styleable.CustomEmptyView_src);
        String text = typedArray.getString(R.styleable.CustomEmptyView_text);
        if (src == null) {
            ivEmptyImage.setImageResource(R.mipmap.empty);
        } else {
            ivEmptyImage.setImageDrawable(src);
        }

        if (TextUtils.isEmpty(text)) {
            tvEmptyInfo.setText("暂无记录");
        } else {
            tvEmptyInfo.setText(text);
        }
        typedArray.recycle();
    }

    public void setText(String str) {
        tvEmptyInfo.setText(str);
    }

}
