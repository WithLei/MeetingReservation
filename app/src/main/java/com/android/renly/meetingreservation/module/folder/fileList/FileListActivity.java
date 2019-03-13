package com.android.renly.meetingreservation.module.folder.fileList;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.FolderAdapter;
import com.android.renly.meetingreservation.api.bean.Folder;
import com.android.renly.meetingreservation.listener.ItemClickListener;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class FileListActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private FolderAdapter adapter;
    private List<Folder> folderList;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_filelist;
    }

    @Override
    protected void initData() {
        folderList = new ArrayList<>();
        folderList.add(new Folder("产品样例.pdf", new Date().getTime() - 360000, 2, "17.28MB"));
        folderList.add(new Folder("产品A实体图.png", new Date().getTime() - 10000, 2, "3.45MB"));
        folderList.add(new Folder("产品A渲染图.psd", new Date().getTime() - 10000, 2, "7.18MB"));
        folderList.add(new Folder("关于新产品的售价.docx", new Date().getTime() - 10000, 2, "14.68MB"));
        folderList.add(new Folder("新产品介绍.ppt", new Date().getTime() - 10000, 2, "12.89MB"));
        folderList.add(new Folder("新产品介绍.mp4", new Date().getTime() - 10000, 2, "25.32MB"));
        initAdapter();
    }

    @Override
    protected void initView() {
        String title = getIntent().getStringExtra("title");
        initToolBar(true, title);
        initSlidr();
        initRecyclerView();
    }

    /**
     * 初始化recylerView的一些属性
     */
    protected RecyclerView.LayoutManager mLayoutManager;

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

//        recyclerView.addItemDecoration(new RecycleViewDivider(
//                getActivity(), LinearLayoutManager.VERTICAL, 20, getResources().getColor(R.color.colorDivider)));
        // 调整draw缓存,加速recyclerview加载
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    private void initAdapter() {
        adapter = new FolderAdapter(this, folderList);
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                    // 打开文件
                    String fileDirec = Environment.getExternalStorageDirectory().getAbsolutePath() +
                            File.separator + "meetingReservation" + File.separator;
                    if (pos == 5)
                        fileDirec += "【還願】插曲码头姑娘合唱版（巩莉芳&杜美心） [AV 44136941, From JIJIDOWN.COM].mp4";
                    else if (pos == 1)
                        fileDirec += "HeadPhoto.png";
                    else
                        fileDirec += "Oracle实验.docx";
//                    fileDirec += "speaker01.jpg";
//                    fileDirec += "【還願】插曲码头姑娘合唱版（巩莉芳&杜美心） [AV 44136941, From JIJIDOWN.COM].mp4";
//                    fileDirec += "PPT学习范本.pptx";
//                    fileDirec += "Oracle实验.docx";
//                    fileDirec += "html笔记.txt";
//                    fileDirec += "HeadPhoto.png";
                    FileUtils.openFile(FileListActivity.this, fileDirec);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
