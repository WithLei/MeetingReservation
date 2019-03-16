package com.android.renly.meetingreservation.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.android.renly.meetingreservation.R;

import java.util.List;

public class MyExAdapter implements ExpandableListAdapter {
    List<String> groupList; //父级列表数据
    List<List<String>> childrenList; //子级列表数据
    Context mContext;

    public MyExAdapter(Context context, List<String> groupList, List<List<String>> childrenList) {
        this.mContext = context;
        this.groupList = groupList;
        this.childrenList = childrenList;
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
        //加载部门数据
        ((TextView) view.findViewById(R.id.department))
                .setText(groupList.get(pos) + "（" + childrenList.get(pos).size() + "）");

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
        ((TextView) view
                .findViewById(R.id.name))
                .setText(childrenList.get(groupPos)
                        .get(childrenPos));
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
