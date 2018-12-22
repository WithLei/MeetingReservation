package com.android.renly.meetingreservation.module.mine;

import com.android.renly.meetingreservation.App;
import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MineFragPresenter implements BasePresenter {
    private final MineFrag mView;
    private static final int[] icons = new int[]{
            R.drawable.ic_settings_24dp,
            R.drawable.ic_menu_share_24dp,
            R.drawable.ic_info_24dp,
    };

    private static final String[] titles = new String[]{
            "设置",
            "分享客户端",
            "关于本程序",
    };

    @Override
    public void getData(boolean isRefresh) {
        if (App.iSLOGIN()) {
            getUserAvatar();
        }
    }

    private void getUserAvatar() {
    }

    @Override
    public void getMoreData() {

    }

    List<Map<String, Object>> getMenuList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> ob = new HashMap<>();
            ob.put("icon", icons[i]);
            ob.put("title", titles[i]);
            list.add(ob);
        }
        return list;
    }

    public MineFragPresenter(MineFrag mView) {
        this.mView = mView;
    }
}
