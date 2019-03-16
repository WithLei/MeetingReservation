package com.android.renly.meetingreservation.module.meeting.addPeople.department;

import android.content.Context;
import android.widget.ExpandableListView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.MyExAdapter;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.meeting.addPeople.AddPeopleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DepartmentFrag extends BaseFragment {
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;

    List<String>groupList; //部门列表
    List<List<String>>childList; //人员列表
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
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void ScrollToTop() {

    }

    private void initGroupAndChildList() {
        groupList = new ArrayList<>();
        childList = new ArrayList<>();

        groupList.add("销售部");
        groupList.add("技术部");
        groupList.add("事业部");
        groupList.add("人事部");

        List<String> firstGroup = new ArrayList<>();
        firstGroup.add("陈奕迅");
        firstGroup.add("阿信");
        firstGroup.add("吴青峰");

        List<String> secondGroup = new ArrayList<>();
        secondGroup.add("Steve");
        secondGroup.add("Allen Zhang");
        secondGroup.add("Jack Ma");
        secondGroup.add("Bill Gates");
        secondGroup.add("Buffett");

        List<String> thirdGroup = new ArrayList<>();
        thirdGroup.add("灰太狼");

        List<String> fourthGroup = new ArrayList<>();
        fourthGroup.add("喜羊羊");
        fourthGroup.add("美羊羊");
        fourthGroup.add("懒羊羊");

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
}
