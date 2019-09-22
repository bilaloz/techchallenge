package com.tech.challenge.contract;


/**
 * Responsible to manage HomeActivityContract
 * @author bilal
 * @version 1.0.0
 */

public interface HomeActivityContract {

    interface Presenter {

        void created();

        void setView(View view);

        void clickSignOut();
    }

    interface View {

        void init();

        void signOutAlert();
    }


}
