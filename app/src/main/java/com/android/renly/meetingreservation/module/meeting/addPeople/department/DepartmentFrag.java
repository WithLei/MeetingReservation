package com.android.renly.meetingreservation.module.meeting.addPeople.department;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.MyExAdapter;
import com.android.renly.meetingreservation.api.bean.SortModel;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.meeting.addPeople.AddPeopleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DepartmentFrag extends BaseFragment {
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;

    List<SortModel>groupList; //部门列表
    List<List<SortModel>>childList; //人员列表
    MyExAdapter adapter;

    @Override
    protected void initInjector() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.frag_department;
    }

    @Override
    protected void initData(Context content) {
        initGroupAndChildList();
        initView();
    }

    @Override
    protected void initView() {
        adapter = new MyExAdapter(mActivity,groupList, childList);
        adapter.setOnRefresh(new MyExAdapter.onRefresh() {
            @Override
            public void doGroupCheck(boolean check, int group) {
                groupList.get(group).setChecked(check);
                int num = 0;
                for (int i = 0; i < childList.get(group).size(); i++) {
                    if (childList.get(group).get(i).isChecked() != check) {
                        childList.get(group).get(i).setChecked(check);
                        num += check ? 1 : -1;
                    }
                }
                notifyDataSetChanged();
                mActivity.updateSelectedNum(num);
            }

            @Override
            public void doChildCheck(boolean check, int group, int child) {
                if (childList.get(group).get(child).isChecked() != check) {
                    childList.get(group).get(child).setChecked(check);
                }
                notifyDataSetChanged();
                mActivity.updateSelectedNum(check ? 1 : -1);
            }
        });
        expandableListView.setAdapter(adapter);

        expandableListView.expandGroup(1);
    }

    @Override
    public void ScrollToTop() {

    }

    private void initGroupAndChildList() {
        groupList = new ArrayList<>();
        childList = new ArrayList<>();

        groupList.add(new SortModel("销售部"));
        groupList.add(new SortModel("技术部"));
        groupList.add(new SortModel("事业部"));
        groupList.add(new SortModel("人事部"));

        List<SortModel> firstGroup = new ArrayList<>();
        firstGroup.add(new SortModel("陈奕迅"));
        firstGroup.add(new SortModel("阿信"));
        firstGroup.add(new SortModel("吴青峰"));

        List<SortModel> secondGroup = new ArrayList<>();
        secondGroup.add(new SortModel("Steve"));
        secondGroup.add(new SortModel("Allen Zhang"));
        secondGroup.add(new SortModel("Jack Ma"));
        secondGroup.add(new SortModel("Bill Gates"));
        secondGroup.add(new SortModel("Buffett"));

        List<SortModel> thirdGroup = new ArrayList<>();
        thirdGroup.add(new SortModel("灰太狼"));

        List<SortModel> fourthGroup = new ArrayList<>();
        fourthGroup.add(new SortModel("喜羊羊"));
        fourthGroup.add(new SortModel("美羊羊"));
        fourthGroup.add(new SortModel("懒羊羊"));

        childList.add(firstGroup);
        childList.add(secondGroup);
        childList.add(thirdGroup);
        childList.add(fourthGroup);
    }

    private AddPeopleActivity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AddPeopleActivity) context;
    }

    public void setNeedCheck(boolean flag) {
        adapter.setNeedCheck(flag);
    }

    public void notifyDataSetChanged() {
        if (adapter == null || expandableListView == null)
            return;
        for (int i = 0;i < groupList.size();i++) {
            expandableListView.collapseGroup(i);
            expandableListView.expandGroup(i);
        }
    }
}
