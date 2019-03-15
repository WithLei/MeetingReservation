package com.android.renly.meetingreservation.module.meeting.peopleList;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.SortAdapter;
import com.android.renly.meetingreservation.api.bean.SortModel;
import com.android.renly.meetingreservation.listener.PinyinComparator;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.meeting.addPeople.PeopleListActivity;
import com.android.renly.meetingreservation.utils.CharacterParser;
import com.android.renly.meetingreservation.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddPeopleActivity extends BaseActivity {
    @BindView(R.id.top_layout)
    LinearLayout xuanfuLayout; // 顶部悬浮的layout
    @BindView(R.id.top_char)
    TextView xuanfaText; // 悬浮的文字
    @BindView(R.id.check)
    TextView check; // 多选
    @BindView(R.id.sidrbar)
    SideBar sideBar; // 右边的引导
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.country_lvcountry)
    ListView sortListView;
    @BindView(R.id.num)
    TextView peopleNum;

    public static final int requestCode = 2048;

    private SortAdapter adapter; // 排序的适配器

    private CharacterParser characterParser;
    private List<SortModel> SourceDateList; // 数据

    private PinyinComparator pinyinComparator;
    private int lastFirstVisibleItem = -1;
    private boolean isNeedChecked; // 是否需要出现选择的按钮

    private int selectedNum = 0;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_addpeople;
    }

    @Override
    protected void initData() {
        initToolBar(true, "添加成员");
        initSlidr();
    }

    @Override
    protected void initView() {
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();
        sideBar.setTextView(dialog);

        /**
         * 为右边添加触摸事件
         */
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView.setOnItemClickListener((parent, view, position, id) -> {

            if (!isNeedChecked) {
                Toast.makeText(getApplication(),
                        ((SortModel) adapter.getItem(position)).getName(),
                        Toast.LENGTH_SHORT).show();
            } else {
                if (SourceDateList.get(position).isChecked()) {
                    SourceDateList.get(position).setChecked(false);
                    updateSelectedNum(--selectedNum);
                } else {
                    SourceDateList.get(position).setChecked(true);
                    updateSelectedNum(++selectedNum);
                }
                adapter.notifyDataSetChanged(); // 这样写效率很低， 以后可以改成
                // RecycleView 直接notify
                // item的状态
            }
        });

        SourceDateList = filledData(getResources().getStringArray(R.array.date));// 填充数据

        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);

        /**
         * 设置滚动监听， 实时跟新悬浮的字母的值
         */
        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int section = adapter.getSectionForPosition(firstVisibleItem);
                int nextSecPosition = adapter
                        .getPositionForSection(section + 1);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) xuanfuLayout
                            .getLayoutParams();
                    params.topMargin = 0;
                    xuanfuLayout.setLayoutParams(params);
                    xuanfaText.setText(String.valueOf((char) section));
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = xuanfuLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) xuanfuLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            xuanfuLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                xuanfuLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
    }

    /**
     * 更新选择人数
     */
    private void updateSelectedNum(int num) {
        peopleNum.setText(num + "");
    }

    /**
     * 填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            sortModel.setSex(i % 2);
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 过滤数据
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    @OnClick({R.id.check, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check:
                if (isNeedChecked) {
                    adapter.setNeedCheck(false);
                    check.setText("选择");
                    isNeedChecked = false;
                } else {
                    adapter.setNeedCheck(true);
                    check.setText("取消");
                    isNeedChecked = true;
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.confirm:
                Intent intent = new Intent(this, PeopleListActivity.class);
                intent.putExtra("people",9);
                setResult(RESULT_OK, intent);
                finishActivity();
                break;
        }
    }
}
