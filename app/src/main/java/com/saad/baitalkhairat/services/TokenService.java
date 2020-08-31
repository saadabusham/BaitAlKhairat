package com.saad.baitalkhairat.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.model.auth.User;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.utils.TimeUtils;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TokenService extends Service {

    public static MutableLiveData<Boolean> isTokenExpired = new MutableLiveData<Boolean>();
    private Timer mTimer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new CheckForTokenAvailability(), 0, 10000);

        return super.onStartCommand(intent, flags, startId);
    }

    private void isTokenAvailable() {
        SessionManager.init(getApplicationContext());
        SessionManager.getUserDetails();
        if (User.getInstance().getTokenResponse() != null &&
                (Calendar.getInstance().getTimeInMillis() >=
                        (User.getInstance().getTokenResponse().getToken_generated_date() +
                                TimeUtils.secToMillisecond(User.getInstance().getTokenResponse().getExpiresIn())))) {
            isTokenExpired.postValue(true);
            refreshToken();
        } else {
            isTokenExpired.postValue(false);
        }
    }

    private void refreshToken() {
        DataManager.getInstance().getAuthService().refreshToken(getApplicationContext(),
                User.getInstance().getTokenResponse().getRefreshToken());
    }


    class CheckForTokenAvailability extends TimerTask {
        @Override
        public void run() {
            isTokenAvailable();
        }
    }
}
