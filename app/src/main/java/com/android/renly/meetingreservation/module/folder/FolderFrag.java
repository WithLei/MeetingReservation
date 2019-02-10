package com.android.renly.meetingreservation.module.folder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.FolderAdapter;
import com.android.renly.meetingreservation.api.bean.Folder;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.utils.LogUtils;
import com.android.renly.meetingreservation.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class FolderFrag extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.search)
    EditText search;

    private FolderAdapter adapter;
    private List<Folder>folderList;

    @Override
    protected void initInjector() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.frag_folder;
    }

    @Override
    protected void initData(Context content) {
        folderList = new ArrayList<>();
        folderList.add(new Folder("关于新产品的售价", new Date().getTime()-10000,1));
        folderList.add(new Folder("关于新产品的售价", new Date().getTime()-10000,1));
        folderList.add(new Folder("关于新产品的售价", new Date().getTime()-10000,1));
        folderList.add(new Folder("关于新产品的售价", new Date().getTime()-10000,1));
        folderList.add(new Folder("关于新产品的售价", new Date().getTime()-10000,1));
        folderList.add(new Folder("关于新产品的售价", new Date().getTime()-10000,1));
        folderList.add(new Folder("关于新产品的售价", new Date().getTime()-10000,1));
        folderList.add(new Folder("关于新产品的售价", new Date().getTime()-10000,1));
        folderList.add(new Folder("关于新产品的售价", new Date().getTime()-10000,1));

        LogUtils.printLog(new Date().getTime()+"initdata");
        initAdapter();
    }

    /**
     * 初始化recylerView的一些属性
     */
    protected RecyclerView.LayoutManager mLayoutManager;

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

//        recyclerView.addItemDecoration(new RecycleViewDivider(
//                getActivity(), LinearLayoutManager.VERTICAL, 20, getResources().getColor(R.color.colorDivider)));
        // 调整draw缓存,加速recyclerview加载
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    private void initAdapter() {
        adapter = new FolderAdapter(getActivity(), folderList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    public void ScrollToTop() {

    }

    public void loseFocus(){
        search.setFocusable(false);
        search.setFocusable(true);
        search.setFocusableInTouchMode(true);
    }
}
