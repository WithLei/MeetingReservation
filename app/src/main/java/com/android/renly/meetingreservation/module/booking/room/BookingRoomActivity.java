package com.android.renly.meetingreservation.module.booking.room;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.listener.GlideImageLoader;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookingRoomActivity extends BaseActivity {
    @BindView(R.id.banner)
    Banner banner;

    private List<Integer> images = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.activity_book_room;
    }

    @Override
    protected void initData() {
        initImages();
    }

    private void initImages() {
        images.add(R.drawable.room01);
        images.add(R.drawable.room02);
        images.add(R.drawable.room03);
        images.add(R.drawable.room04);
        images.add(R.drawable.room05);
    }

    @Override
    protected void initView() {
        initBanner();
        initToolBar(true,"");
        initSlidr();
    }

    private void initBanner() {
        //设置banner样式
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
