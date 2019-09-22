package com.tech.challenge.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tech.challenge.R;
import com.tech.challenge.adapter.CustomAdapter;
import com.tech.challenge.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends BaseFragment {

    ListView mListView;
    List<Response> responses = new ArrayList<>();
    private View mView;
    private Response[] mResponseObj;
    private CustomAdapter mCustomAdapter;
    private Gson mGson;
    private String mUrl = "http://kariyertechchallenge.mockable.io/";
    private AsyncHttpClient mClient;
    private String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment, container, false);

        mListView = mView.findViewById(R.id.mListView);

        mClient = new AsyncHttpClient();
        mClient.get(mView.getContext(), mUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String responseResult = new String(responseBody);

                    mGson = new Gson();

                    mResponseObj = mGson.fromJson(responseResult, Response[].class);

                    Log.d("HomeFragment", "onSuccess: " + mResponseObj);

                    mCustomAdapter = new CustomAdapter(Arrays.asList(mResponseObj), mView.getContext());
                    mListView.setAdapter(mCustomAdapter);
                } catch (Exception e) {
                    Log.d("HomeFragment", "onSuccess: " + e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        mListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Log.d(TAG, "onItemClick: " + i);
            Log.d(TAG, "onItemClick: " + view);
        });

        return mView;
    }
}
