package com.tech.challenge.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;

import com.tech.challenge.listeners.FragmentDelete;
import com.tech.challenge.listeners.FragmentSwitch;
import com.tech.challenge.MainActivityContract;
import com.tech.challenge.R;
import com.tech.challenge.helper.ListenerHelper;
import com.tech.challenge.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, FragmentSwitch, FragmentDelete {


    public static FragmentManager mFragmentManager;


    public void switchScreen(Fragment fragment, String backStage) {

        FragmentTransaction fragments = mFragmentManager
                .beginTransaction()
                .replace(R.id.screens, fragment, backStage)
                .addToBackStack(backStage);
        fragments.commitAllowingStateLoss();
    }

    @Override
    public void deleteFragment(Fragment fragment, String backStage) {
        if (mFragmentManager == null)
            return;
        FragmentTransaction trans = mFragmentManager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        mFragmentManager.popBackStack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        ListenerHelper.setFragmentSwitch(this);
        ListenerHelper.setDeleteFragment(this);


        MainActivityPresenter mActivityPresenter = new MainActivityPresenter();
        mActivityPresenter.setView(this);

        mActivityPresenter.created();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return backStackControl();
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean backStackControl() {
        int backFragmentCount = (mFragmentManager.getBackStackEntryCount() - 1);
        if (mFragmentManager.getBackStackEntryCount() <= 0 || backFragmentCount <= 0)
            finishAffinity();
        else
            mFragmentManager.popBackStackImmediate();
        return true;
    }
}
