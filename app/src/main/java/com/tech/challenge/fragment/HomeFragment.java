package com.tech.challenge.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tech.challenge.HomeActivityContract;
import com.tech.challenge.R;
import com.tech.challenge.adapter.CustomAdapter;
import com.tech.challenge.model.Response;
import com.tech.challenge.presenter.HomeFragmentPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cz.msebera.android.httpclient.Header;

public class HomeFragment extends BaseFragment implements HomeActivityContract.View {

    @BindView(R.id.myOrders)
    AppCompatButton myOrders;
    @BindView(R.id.signOut)
    AppCompatButton signOut;
    private ListView mListView;
    List<Response> responses = new ArrayList<>();
    private View mView;
    private Response[] mResponseObj;
    private CustomAdapter mCustomAdapter;
    private Gson mGson;
    private String TAG = "HomeFragment";


    /**
     * Responsible to manage HomeFragment
     *
     * @author bilal
     * @version 1.0.0
     */


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment, container, false);

        HomeFragmentPresenter mLoginPresenter = new HomeFragmentPresenter();
        mLoginPresenter.setView(this);
        mLoginPresenter.created();


        return mView;
    }

    @Override
    public void init() {
        mListView = mView.findViewById(R.id.mListView);

        /**TODO
         * HTTP MVP DESIGN EDIT , HTTP be must MVP ->  Helper Class
         * View only view
         */
        AsyncHttpClient mClient = new AsyncHttpClient();
        String mUrl = "http://kariyertechchallenge.mockable.io/";
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
    }
}
