package com.tech.challenge.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tech.challenge.HomeActivityContract;
import com.tech.challenge.R;
import com.tech.challenge.adapter.CustomAdapter;
import com.tech.challenge.model.Response;
import com.tech.challenge.presenter.HomeFragmentPresenter;
import com.tech.challenge.utility.AppSettings;
import com.tech.challenge.utility.Constants;
import com.tech.challenge.utility.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

public class HomeFragment extends BaseFragment implements HomeActivityContract.View {

    @BindView(R.id.myOrders)
    AppCompatButton myOrders;
    @BindView(R.id.signOut)
    AppCompatButton signOut;
    List<Response> responses = new ArrayList<>();
    @BindView(R.id.toolbarTextView)
    TextView toolbarTextView;
    @BindView(R.id.mListView)
    ListView mListView;
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

    private Unbinder unbinder;
    private HomeFragmentPresenter mLoginPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment, container, false);

        mLoginPresenter = new HomeFragmentPresenter();
        mLoginPresenter.setView(this);
        mLoginPresenter.created();


        return mView;
    }

    @Override
    public void init() {

        unbinder = ButterKnife.bind(this, mView);

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

    @Override
    public void signOutAlert() {
        Utils.showExitAlert(getActivity(), mView.getContext());
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @OnClick({R.id.myOrders, R.id.signOut})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myOrders:
                break;
            case R.id.signOut:
                mLoginPresenter.clickSignOut();
                break;
        }
    }
}
