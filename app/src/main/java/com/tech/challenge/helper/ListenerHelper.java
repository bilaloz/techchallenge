package com.tech.challenge.helper;

import androidx.fragment.app.Fragment;

import com.tech.challenge.listeners.FragmentDelete;
import com.tech.challenge.listeners.FragmentSwitch;

/**
 * Responsible to manage ListenerHelper
 * @author bilal
 * @version 1.0.0
 */

public class ListenerHelper {
    public static FragmentSwitch mFragmentSwitch;
    public static FragmentDelete mFragmentDelete;

    public static void setFragmentSwitch(FragmentSwitch fragmentSwitch) {
        ListenerHelper.mFragmentSwitch = fragmentSwitch;
    }

    public static void setDeleteFragment(FragmentDelete fragmentDelete) {
        ListenerHelper.mFragmentDelete = fragmentDelete;
    }
}
