package com.test.calow.training.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.test.calow.training.R;
import com.test.calow.training.ui.fragment.Dynamic;
import com.test.calow.training.ui.fragment.Friends;
import com.test.calow.training.ui.fragment.Message;
import com.test.calow.training.ui.fragment.Setting;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";
    public static final int MESSAGE_FRAGMENT = 0;
    public static final int FRIENDS_FRAGMENT = 1;
    public static final int DYNAMIC_FRAGMENT = 2;
    public static final int SETTING_FRAGMENT = 3;

    @InjectView(R.id.bnb_navigation)
    BottomNavigationBar bnbNavigation;
    @InjectView(R.id.fl_content)
    FrameLayout flContent;

    private FragmentManager fm;
    private Message mMsgFragment;
    private Friends mFriFragment;
    private Dynamic mDynFragment;
    private Setting mSetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        init();
    }

    public void init(){
        fm = getFragmentManager();
        initBottomBar();
        initFragment();
    }

    public void initBottomBar() {
        bnbNavigation.setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(new BottomNavigationItem(R.drawable.chat_selected, R.string.string_chat))
                .addItem(new BottomNavigationItem(R.drawable.friends_selected, R.string.string_friends))
                .addItem(new BottomNavigationItem(R.drawable.dynamic_selected, R.string.string_dynamic))
                .addItem(new BottomNavigationItem(R.drawable.setting_selected, R.string.string_setting))
                .setInActiveColor(R.color.tabInActive)
                .setActiveColor(R.color.tabActive)
                .setFirstSelectedPosition(0)
                .initialise();

        bnbNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.d(TAG, "-------onTabSelected--------position = " + position + "----------");
//                exchangeFragment(position);
                showFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {
                Log.d(TAG, "-------onTabUnselected--------position = " + position + "----------");
                hideFragment(position);
            }

            @Override
            public void onTabReselected(int position) {
                Log.d(TAG, "-------onTabReselected--------position = " + position + "----------");
            }
        });
    }

    public void initFragment(){
        mMsgFragment = new Message();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_content, mMsgFragment).show(mMsgFragment).commit();
    }

    public void showFragment(int position){
        FragmentTransaction ft = fm.beginTransaction();
        switch (position) {
            case MESSAGE_FRAGMENT:
                showMsgFragment(ft);
                break;
            case FRIENDS_FRAGMENT:
                showFriFragment(ft);
                break;
            case DYNAMIC_FRAGMENT:
                showDynFragment(ft);
                break;
            case SETTING_FRAGMENT:
                showSetFragment(ft);
                break;
            default:
                break;
        }
        ft.commit();
    }

    public void hideFragment(int position){
        FragmentTransaction ft = fm.beginTransaction();
        switch (position) {
            case MESSAGE_FRAGMENT:
                hideMsgFragment(ft);
                break;
            case FRIENDS_FRAGMENT:
                hideFriFragment(ft);
                break;
            case DYNAMIC_FRAGMENT:
                hideDynFragment(ft);
                break;
            case SETTING_FRAGMENT:
                hideSetFragment(ft);
                break;
            default:
                break;
        }
        ft.commit();
    }

    public void exchangeFragment(int position){
        FragmentTransaction ft = fm.beginTransaction();
        switch (position) {
            case MESSAGE_FRAGMENT:
                showMsgFragment(ft);
                hideFriFragment(ft);
                hideDynFragment(ft);
                hideSetFragment(ft);
                break;
            case FRIENDS_FRAGMENT:
                hideMsgFragment(ft);
                showFriFragment(ft);
                hideDynFragment(ft);
                hideSetFragment(ft);
                break;
            case DYNAMIC_FRAGMENT:
                hideMsgFragment(ft);
                hideFriFragment(ft);
                showDynFragment(ft);
                hideSetFragment(ft);
                break;
            case SETTING_FRAGMENT:
                hideMsgFragment(ft);
                hideFriFragment(ft);
                hideDynFragment(ft);
                showSetFragment(ft);
                break;
            default:
                break;
        }
        ft.commit();
    }

    public void showMsgFragment(FragmentTransaction ft){
        if (mMsgFragment == null){
            mMsgFragment = new Message();
            ft.add(R.id.fl_content, mMsgFragment);
        }
        ft.show(mMsgFragment);
    }

    public void hideMsgFragment(FragmentTransaction ft){
        if (mMsgFragment != null){
            ft.hide(mMsgFragment);
        }
    }

    public void showFriFragment(FragmentTransaction ft){
        if (mFriFragment == null){
            mFriFragment = new Friends();
            ft.add(R.id.fl_content, mFriFragment);
        }
        ft.show(mFriFragment);
    }

    public void hideFriFragment(FragmentTransaction ft){
        if (mFriFragment != null){
            ft.hide(mFriFragment);
        }
    }

    public void showDynFragment(FragmentTransaction ft){
        if (mDynFragment == null){
            mDynFragment = new Dynamic();
            ft.add(R.id.fl_content, mDynFragment);
        }
        ft.show(mDynFragment);
    }

    public void hideDynFragment(FragmentTransaction ft){
        if (mDynFragment != null){
            ft.hide(mDynFragment);
        }
    }

    public void showSetFragment(FragmentTransaction ft){
        if (mSetFragment == null){
            mSetFragment = new Setting();
            ft.add(R.id.fl_content, mSetFragment);
        }
        ft.show(mSetFragment);
    }

    public void hideSetFragment(FragmentTransaction ft){
        if (mSetFragment != null){
            ft.hide(mSetFragment);
        }
    }


}
