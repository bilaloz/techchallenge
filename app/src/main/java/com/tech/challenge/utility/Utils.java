package com.tech.challenge.utility;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;

public class Utils {
    private static String TAG = "Utils";


    public static void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static boolean isUserLogin(String status, Context context) {
        boolean userLogin = AppSettings.getInstance(context).getBoolean(Constants.IS_LOGIN, false);
        if (status.equals("get")) {
            return userLogin;
        } else {
            return AppSettings.getInstance(context).set(Constants.IS_LOGIN, false);
        }
    }

    public static void showAlertDialogButtonClicked(View view, String message, String tittle) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(message);
        builder.setMessage(tittle);

        // add a button
        builder.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static boolean userLogin(Context context, String inputName, String inputPass, boolean remember) {
        String userName = AppSettings.getInstance(context).getString(Constants.USER_NAME, "kariyer");
        String userPass = AppSettings.getInstance(context).getString(Constants.USER_PASS, "2019ADev");

        Log.d(TAG, "userLogin: " + inputName + "" + inputPass);
        Log.d(TAG, "userLogin: " + userName + "" + userPass);
        AppSettings.getInstance(context).set(Constants.IS_LOGIN, remember);
        return userName.equals(inputName) && userPass.equals(inputPass);
    }

}
