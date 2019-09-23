package com.tech.challenge.presenter;


import android.content.Context;

import com.tech.challenge.contract.MainActivityContract;
import com.tech.challenge.fragment.HomeFragment;
import com.tech.challenge.fragment.LoginFragment;
import com.tech.challenge.utility.Constants;
import com.tech.challenge.utility.Utils;


/**
 * Responsible to manage MainActivityPresenter
 * @author bilal
 * @version 1.0.0
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View mView;


    @Override
    public void setView(MainActivityContract.View view) {
        this.mView = view;
    }

    @Override
    public void created() {

        /*
        *user remember and user login controller
         */
        boolean isLogin = Utils.isUserLogin(Constants.GET, (Context) mView);
        if (!isLogin)
            mView.switchScreen(new LoginFragment(), Constants.LOGIN_FRAGMENT);
        else
            mView.switchScreen(new HomeFragment(), Constants.HOME_FRAGMENT);
    }
}
