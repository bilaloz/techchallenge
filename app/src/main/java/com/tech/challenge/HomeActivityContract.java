package com.tech.challenge;


/**
 * Responsible to manage HomeActivityContract
 * @author bilal
 * @version 1.0.0
 */

public interface HomeActivityContract {

    interface Presenter {

        void created();

        void setView(View view);
    }

    interface View {

        void init();

    }


}
