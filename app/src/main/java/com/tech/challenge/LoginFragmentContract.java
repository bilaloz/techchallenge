package com.tech.challenge;

import android.content.Context;
import android.text.Editable;

public interface LoginFragmentContract {

    interface  View {

        void init();

        void loginNullMessage();

        void isStatus(boolean isStatus);
    }

    interface  Presenter{
        
        void setView(View view);

        void created();

        void doLogin(Context context , boolean isRemember, Editable text, Editable text1);
    }
}
