package com.android.renly.meetingreservation.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.api.bean.SortModel;
import com.android.renly.meetingreservation.utils.LogUtils;

import java.util.List;

public class MyExAdapter implements ExpandableListAdapter {
    List<SortModel> groupList; //父级列表数据
    List<List<SortModel>> childrenList; //子级列表数据
    Context mContext;

    private boolean isNeedCheck;
    private onRefresh onRefresh;

    public interface onRefresh {
        void doGroupCheck(boolean check, int group);

        void doChildCheck(boolean check, int group, int child);
    }

    public void setOnRefresh(onRefresh onRefresh) {
        this.onRefresh = onRefresh;
    }

    public MyExAdapter(Context context, List<SortModel> groupList, List<List<SortModel>> childrenList) {
        this.mContext = context;
        this.groupList = groupList;
        this.childrenList = childrenList;
    }

    public boolean isNeedCheck() {
        return isNeedCheck;
    }

    public void setNeedCheck(boolean isNeedCheck) {
        this.isNeedCheck = isNeedCheck;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPos) {
        return childrenList.get(groupPos).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int groupPos, int childPos) {
        return childrenList.get(groupPos).get(childPos);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int pos, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        //加载Group布局
        View view;
        if (convertView == null) {
            //converView是布局的缓存，如果缓存为空的话，就加载布局到view
            view = LayoutInflater.from(mContext).inflate(R.layout.item_department, viewGroup, false);
        } else {
            //如果convert不为空，说明有缓存，直接把缓存布局赋值给view
            view = convertView;
        }
        SortModel groupModel = groupList.get(pos);
        //加载部门数据
        ((TextView) view.findViewById(R.id.department))
                .setText(groupModel.getName() + "（" + childrenList.get(pos).size() + "）");
        ImageView departmentCheck = view.findViewById(R.id.department_check);
        departmentCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.printLog("Group " + groupModel.isChecked());
                if (groupModel.isChecked()) {
                    departmentCheck.setImageResource(R.drawable.ic_round_unchecked);
                    onRefresh.doGroupCheck(false, pos);
                } else {
                    departmentCheck.setImageResource(R.drawable.ic_round_checked);
                    onRefresh.doGroupCheck(true, pos);
                }
            }
        });

        if (isNeedCheck) {
            departmentCheck.setVisibility(View.VISIBLE);
            if (groupModel.isChecked())
                departmentCheck.setImageResource(R.drawable.ic_round_checked);
            else
                departmentCheck.setImageResource(R.drawable.ic_round_unchecked);
        } else {
            departmentCheck.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public View getChildView(int groupPos, int childrenPos, boolean isExpanded,
                             View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_department_people, viewGroup, false);
        } else {
            view = convertView;
        }
        SortModel childrenModel = childrenList.get(groupPos).get(childrenPos);
        ((TextView) view
                .findViewById(R.id.name))
                .setText(childrenModel.getName());

        ImageView childrenCheck = view.findViewById(R.id.check);
        childrenCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.printLog("children " + childrenModel.isChecked());
                if (childrenModel.isChecked()) {
                    childrenCheck.setImageResource(R.drawable.ic_round_unchecked);
                    onRefresh.doChildCheck(false, groupPos, childrenPos);
                } else {
                    childrenCheck.setImageResource(R.drawable.ic_round_checked);
                    onRefresh.doChildCheck(true, groupPos, childrenPos);
                }
            }
        });

        if (isNeedCheck) {
            childrenCheck.setVisibility(View.VISIBLE);
            if (childrenModel.isChecked())
                childrenCheck.setImageResource(R.drawable.ic_round_checked);
            else
                childrenCheck.setImageResource(R.drawable.ic_round_unchecked);
        } else {
            childrenCheck.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}
