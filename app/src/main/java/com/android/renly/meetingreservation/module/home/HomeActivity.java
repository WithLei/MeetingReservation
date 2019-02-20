package com.android.renly.meetingreservation.module.home;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.adapter.MainPageAdapter;
import com.android.renly.meetingreservation.module.base.BaseActivity;
import com.android.renly.meetingreservation.module.base.BaseFragment;
import com.android.renly.meetingreservation.module.booking.BookingFrag;
import com.android.renly.meetingreservation.module.folder.FolderFrag;
import com.android.renly.meetingreservation.module.home.fullscreen.HomeFrag;
import com.android.renly.meetingreservation.module.mine.MineFrag;
import com.android.renly.meetingreservation.utils.SoftKeyboardStateHelper;
import com.android.renly.meetingreservation.widget.MyBottomTab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity
        implements ViewPager.OnPageChangeListener {
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.bottom_bar)
    MyBottomTab bottomBar;
    private ViewPager viewPager;
    private List<BaseFragment> fragments = new ArrayList<>();
    private long mExitTime;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        initViewpager();

    }

    @Override
    protected void initView() {
        bottomBar.setOnTabChangeListener((v, position, isChange) -> setSelect(position, isChange));
        bottomBar.setMessage(true);
        setKeyBoardListener();
    }

    private void setSelect(int position, boolean isChange) {
        if (isChange)
            viewPager.setCurrentItem(position, false);
        else
            fragments.get(position).ScrollToTop();
    }

    @Override
    protected void doBeforeSetContent() {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomBar.setSelect(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private HomeFrag homeFrag;
    private BookingFrag bookingFrag;
    private FolderFrag folderFrag;
    private MineFrag mineFrag;
    private void initViewpager() {
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(this);
        homeFrag = new HomeFrag();
        bookingFrag = new BookingFrag();
        folderFrag = new FolderFrag();
        mineFrag = new MineFrag();
        fragments.add(homeFrag);
        fragments.add(bookingFrag);
        fragments.add(folderFrag);
        fragments.add(mineFrag);
        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            if ((System.currentTimeMillis() - mExitTime) > 1500) {
                ToastShort("再按一次退出客户端(｡･ω･｡)~~");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }

    public void hideBottomBar(){
        bottomBar.setVisibility(View.GONE);
    }

    public void showBottomBar(){
        bottomBar.setVisibility(View.VISIBLE);
    }

    private void setKeyBoardListener() {
        // 监听软键盘弹起落下事件，确保底部栏不弹起
        SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(root);
        softKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                hideBottomBar();
            }

            @Override
            public void onSoftKeyboardClosed() {
                showBottomBar();
                bookingFrag.loseFocus();
                folderFrag.loseFocus();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
