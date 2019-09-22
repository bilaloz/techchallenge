package com.tech.challenge.listeners;

import androidx.fragment.app.Fragment;

/**
 * Responsible to manage FragmentSwitch
 * @author bilal
 * @version 1.0.0
 */

public interface FragmentSwitch {

    /*
    Fragment Switch listener
     */

    void switchScreen(Fragment fragment, String backStage);
}
