package com.android.renly.meetingreservation.module.folder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.FolderAdapter;
import com.android.renly.meetingreservation.api.bean.Folder;
import com.android.renly.meetingreservation.listener.ItemClickListener;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.folder.fileList.FileListActivity;
import com.android.renly.meetingreservation.module.folder.upload.UploadActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FolderFrag extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.upload)
    LinearLayout upload;
    Unbinder unbinder;

    private FolderAdapter adapter;
    private List<Folder> folderList;

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
        folderList.add(new Folder("关于新产品的售价", new Date().getTime() - 10000, 1));
        folderList.add(new Folder("关于新产品的售价.pdf", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.png", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.psd", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.txt", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.ppt", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.upload", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.video", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.zip", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.word", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.html", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.jpg", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.mp3", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.excel", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.gif", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.download", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.ai", new Date().getTime() - 10000, 2, "1.28MB"));
        folderList.add(new Folder("关于新产品的售价.other", new Date().getTime() - 10000, 2, "1.28MB"));

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
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(getContext(), FileListActivity.class);
                intent.putExtra("title", folderList.get(pos).getName());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    @Override
    public void ScrollToTop() {

    }

    public void loseFocus() {
        search.setFocusable(false);
        search.setFocusable(true);
        search.setFocusableInTouchMode(true);
    }

    @OnClick({R.id.upload, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload:
                jumpToActivity(UploadActivity.class);
                break;
            case R.id.search:
                break;
        }
    }
}
