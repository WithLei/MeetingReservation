package com.android.renly.meetingreservation.module.home.fullscreen;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.SearchView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseFragment;

import java.lang.reflect.Field;

import butterknife.BindView;

public class HomeFrag extends BaseFragment {
    @BindView(R.id.searchview)
    SearchView searchview;

    @Override
    protected void initInjector() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.frag_home;
    }

    @Override
    protected void initData(Context content) {

    }

    @Override
    protected void initView() {
        removeUnderLine();
    }

    private void removeUnderLine() {
//        Class<?> c=searchview.getClass();
//        try {
//            Field f=c.getDeclaredField("mSearchPlate");//通过反射，获得类对象的一个属性对象
//            f.setAccessible(true);//设置此私有属性是可访问的
//            View v=(View) f.get(searchview);//获得属性的值
//            v.setBackgroundResource(R.drawable.searchview);//设置此view的背景
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        searchview.setBackgroundResource(R.drawable.searchview);
//        View viewById = searchview.findViewById(android.support.v7.appcompat.R.id.search_plate);
//        if (viewById != null) {
//            viewById.setBackgroundColor(Color.TRANSPARENT);
//        }
    }

    @Override
    public void ScrollToTop() {

    }
}
