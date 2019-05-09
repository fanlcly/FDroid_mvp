package com.fancy.rx_android_mvp.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.fancy.androidutils.recyclerviewhelper.base.BaseQuickAdapter;
import com.fancy.androidutils.recyclerviewhelper.base.BaseViewHolder;
import com.fancy.androidutils.utils.PicassoUtils;
import com.fancy.rx_android_mvp.entity.GirlEntity;

import java.util.List;

import fancy.com.rx_android_mvp.R;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/5/8 0008
 * @since JDK 1.8
 */
public class GirlAdapter extends BaseQuickAdapter<GirlEntity,BaseViewHolder> {


    public GirlAdapter( @Nullable List<GirlEntity> data) {
        super(R.layout.item_girl, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GirlEntity item) {
        PicassoUtils.getInstance().loadImage(mContext,item.getImage_url(), (ImageView) helper.getView(R.id.image_item));
//        helper.setText(R.id.name_item,item.getCollect_num());
    }
}
