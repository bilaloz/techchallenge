package com.tech.challenge.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mView = null;
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
