package com.tech.challenge.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Responsible to manage BaseFragment
 *
 * @author bilal
 * @version 1.0.0
 */

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {

        /**
         * This method aims to Garbage collector time finish Activity.
         * @see onDestroyView
         * @author bilal
         * @since 1.0.0
         */
        super.onDestroyView();
        System.gc();
    }

    @Override
    public void onStart() {
        super.onStart();
        setRetainInstance(true);
        try {
            if (getActivity() != null)
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
