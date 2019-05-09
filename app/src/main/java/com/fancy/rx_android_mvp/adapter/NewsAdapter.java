package com.fancy.rx_android_mvp.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.fancy.androidutils.recyclerviewhelper.base.BaseQuickAdapter;
import com.fancy.androidutils.recyclerviewhelper.base.BaseViewHolder;
import com.fancy.androidutils.utils.PicassoUtils;
import com.fancy.rx_android_mvp.entity.NewsEntity;

import java.util.List;

import fancy.com.rx_android_mvp.R;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/5/9 0009
 * @since JDK 1.8
 */
public class NewsAdapter extends BaseQuickAdapter<NewsEntity,BaseViewHolder> {
    public NewsAdapter( @Nullable List<NewsEntity> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        PicassoUtils.getInstance().loadImage(mContext,item.getThumbnail_pic_s(), (ImageView) helper.getView(R.id.iv_news));
        helper.setText(R.id.tv_name,item.getTitle());
        helper.setText(R.id.tv_from,item.getAuthor_name());
    }
}
