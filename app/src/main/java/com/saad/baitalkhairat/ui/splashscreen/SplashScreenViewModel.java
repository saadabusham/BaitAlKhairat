package com.saad.baitalkhairat.ui.splashscreen;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;

import com.saad.baitalkhairat.databinding.ActivitySplashScreenBinding;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.model.auth.User;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.main.MainActivity;
import com.saad.baitalkhairat.utils.TimeUtils;

import java.util.Calendar;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashScreenViewModel extends BaseViewModel<SplashScreenNavigator, ActivitySplashScreenBinding> {


    private static final int PERMISSION_REQUEST_CODE = 1;
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    public <V extends ViewDataBinding, N extends BaseNavigator> SplashScreenViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (SplashScreenNavigator) navigation, (ActivitySplashScreenBinding) viewDataBinding);
        checkPermission();
    }


    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(getMyContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getMyContext(),
                        WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                && (ContextCompat.checkSelfPermission(getMyContext(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(getMyContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(getMyContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            requestPermission();
        } else {
            doSplash();
        }
    }

    public void doSplash() {
        new Handler().postDelayed(() -> {
            getBaseActivity().finish();
            getMyContext().startActivity(MainActivity.newIntent(getMyContext()));

        }, SPLASH_DISPLAY_LENGTH);
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions((Activity) getMyContext(), new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}
                , PERMISSION_REQUEST_CODE);

    }

    @Override
    protected void setUp() {
        SessionManager.init(getMyContext());
        SessionManager.getUserDetails();
        if (User.getInstance().getTokenResponse() != null &&
                User.getInstance().getTokenResponse().getToken_generated_date() +
                        TimeUtils.secToMillisecond(User.getInstance().getTokenResponse().getExpiresIn())
                        >= Calendar.getInstance().getTimeInMillis()) {
            refreshToken();
        }
    }

    private void refreshToken() {
        DataManager.getInstance().getAuthService().refreshToken(getMyContext(),
                User.getInstance().getTokenResponse().getRefreshToken());
    }
}
