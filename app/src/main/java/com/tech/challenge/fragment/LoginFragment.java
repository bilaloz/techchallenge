package com.tech.challenge.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.SwitchCompat;

import com.tech.challenge.LoginFragmentContract;
import com.tech.challenge.R;
import com.tech.challenge.helper.ListenerHelper;
import com.tech.challenge.presenter.LoginFragmentPresenter;
import com.tech.challenge.utility.Constants;
import com.tech.challenge.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Responsible to manage LoginFragment
 * @author bilal
 * @version 1.0.0
 */

public class LoginFragment extends BaseFragment implements LoginFragmentContract.View {
    @BindView(R.id.edtUserName)
    AppCompatEditText edtUserName;
    @BindView(R.id.edtPassword)
    AppCompatEditText edtPassword;
    @BindView(R.id.switchRemember)
    SwitchCompat switchRemember;
    @BindView(R.id.btnLogin)
    AppCompatButton btnLogin;
    private View mView;

    private Unbinder unbinder;

    private LoginFragmentPresenter mLoginPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.login_activity, container, false);

        mLoginPresenter = new LoginFragmentPresenter();
        mLoginPresenter.setView(this);
        mLoginPresenter.created();

        return mView;
    }

    @OnClick(R.id.btnLogin)
    void onViewClicked() {

        Utils.hideKeyboard(mView);


        boolean isRemember;


        isRemember = switchRemember.isChecked();
        mLoginPresenter.doLogin(mView.getContext(), isRemember, edtUserName.getText(), edtPassword.getText());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void init() {
        unbinder = ButterKnife.bind(this, mView);
    }

    @Override
    public void loginNullMessage() {
        Utils.showAlertDialogButtonClicked(mView, getResources().getString(R.string.login_error_tittle), getString(R.string.login_error_message));
    }

    @Override
    public void isStatus(boolean status) {
        if (status)
            Toast.makeText(getContext(), getResources().getString(R.string.success), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), getResources().getString(R.string.login_error), Toast.LENGTH_SHORT).show();

        if (ListenerHelper.mFragmentSwitch != null) {
            ListenerHelper.mFragmentDelete.deleteFragment(this, Constants.LOGIN_FRAGMENT);
            ListenerHelper.mFragmentSwitch.switchScreen(new HomeFragment(), Constants.HOME_FRAGMENT);
        }
    }

}
