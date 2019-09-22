package com.tech.challenge.presenter;

import com.tech.challenge.HomeActivityContract;

/**
 * Responsible to manage ManinActivity
 * @author bilal
 * @version 1.0.0
 */

public class HomeFragmentPresenter implements HomeActivityContract.Presenter {
    private HomeActivityContract.View mView;

    @Override
    public void created() {
        mView.init();
    }

    @Override
    public void setView(HomeActivityContract.View view) {
        this.mView = view;
    }
}
