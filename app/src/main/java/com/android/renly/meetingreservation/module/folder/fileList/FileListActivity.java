package com.android.renly.meetingreservation.module.folder.fileList;

import android.support.v7.widget.RecyclerView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

import butterknife.BindView;

public class FileListActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_filelist;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String title = getIntent().getStringExtra("title");
        initToolBar(true, title);
        initSlidr();
    }
}
