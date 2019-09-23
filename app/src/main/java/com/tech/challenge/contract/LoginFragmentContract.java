package com.tech.challenge.contract;

import android.content.Context;
import android.text.Editable;


/**
 * Responsible to manage LoginFragmentContract
 * @author bilal
 * @version 1.0.0
 */

public interface LoginFragmentContract {

    interface  View {

        void init();

        void loginNullMessage();

        void isStatus(boolean isStatus);
    }

    interface  Presenter{
        
        void setView(View view);

        void created();

        /*
        Login Request
         */
        void doLogin(Context context , boolean isRemember, Editable text, Editable text1);
    }
}
