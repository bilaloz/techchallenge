package com.tech.challenge;

import android.app.Application;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;
import com.orhanobut.logger.Logger;
import com.tech.challenge.utility.AppSettings;
import com.tech.challenge.utility.Constants;

public class ChallengeApplication extends Application {
    public static Application mApplication;
    private String TAG = "ApplicationStart";

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            mApplication = this;
        } catch (Exception e) {
            Log.d(TAG, "e");
            e.printStackTrace();
        }

        //Hawk == SharedPreferences
        try {
            Hawk.init(this)
                    .setEncryption(new NoEncryption())
                    .setLogInterceptor(message -> Logger.d("Hawk_Helper", "Message : " + message))
                    .build();
        } catch (Exception e) {
            Log.d(TAG, "onCreate: ");
        }

    }
}
