package com.tech.challenge;

import androidx.fragment.app.Fragment;

public interface MainActivityContract {
    /**
     * Responsible to manage Contract with MainActivity
     * @author bilal
     * @version 1.0.0
     */

    interface  View {
        /*
        Fragment Switch
         */
       void switchScreen(Fragment fragment, String backStage);
    }

    interface  Presenter{
        
        void setView(View view);

        void created();
    }
}
