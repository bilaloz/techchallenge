package com.tech.challenge.presenter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;

import com.tech.challenge.LoginFragmentContract;
import com.tech.challenge.utility.Utils;


/**
 * Responsible to manage LoginFragmentPresenter
 * @author bilal
 * @version 1.0.0
 */

public class LoginFragmentPresenter implements LoginFragmentContract.Presenter {

    private LoginFragmentContract.View mView;


    @Override
    public void setView(LoginFragmentContract.View view) {
        this.mView = view;
    }

    @Override
    public void created() {
        mView.init();
    }

    @Override
    public void doLogin(Context context, boolean isRemember, Editable userName, Editable password) {

        String strUserName = userName.toString();
        String strPass = password.toString();


        /*
        *Check controll null
         */
        if (TextUtils.isEmpty(strUserName) || TextUtils.isEmpty(strPass)) {
            /*
            Message Controll
             */
            mView.loginNullMessage();
            return;
        }

        //is status remember
        boolean isStatus = Utils.userLogin(context, strUserName, strPass, isRemember);

        mView.isStatus(isStatus);

    }


}
