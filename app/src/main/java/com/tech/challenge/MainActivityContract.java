package com.tech.challenge;

import androidx.fragment.app.Fragment;

public interface MainActivityContract {

    interface  View {
       void switchScreen(Fragment fragment, String backStage);
    }

    interface  Presenter{
        
        void setView(View view);

        void created();
    }
}
