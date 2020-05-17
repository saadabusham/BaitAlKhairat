package com.saad.baitalkhairat.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityDrawerMainBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.ui.dialog.CustomDialogUtils;
import com.saad.baitalkhairat.utils.LanguageUtils;
import com.saad.baitalkhairat.utils.NetworkUtils;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import dagger.android.AndroidInjection;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback {

    ActivityResultCallBack activityResultCallBack;
    private T mViewDataBinding;
    private V mViewModel;
    private CustomDialogUtils progressDialog;

    public abstract int getBindingVariable();

    public abstract void setUpToolbar();

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    public abstract Context getMyContext();


    public ActivityResultCallBack getActivityResultCallBack() {
        return activityResultCallBack;
    }

    @Override
    public void onFragmentAttachedNeedActivityResult(boolean hideToolbar, boolean hideBottom,
                                                     ActivityResultCallBack activityResultCallBack) {
        onFragmentAttachProcess(hideToolbar, hideBottom);
        this.activityResultCallBack = activityResultCallBack;
    }

    @Override
    public void onFragmentAttached(boolean hideToolbar, boolean hideBottom) {
        onFragmentAttachProcess(hideToolbar, hideBottom);
    }

    private void onFragmentAttachProcess(boolean hideToolbar, boolean hideBottom) {
        if (hideToolbar) {
            if (getSupportActionBar() != null)
                getSupportActionBar().hide();
        }
        if (mViewDataBinding instanceof ActivityDrawerMainBinding) {
            if (hideBottom) {
                ((ActivityDrawerMainBinding) mViewDataBinding).appBarMain.drawerMainContent.bottomSheet.setVisibility(View.GONE);
            } else {
                ((ActivityDrawerMainBinding) mViewDataBinding).appBarMain.drawerMainContent.bottomSheet.setVisibility(View.VISIBLE);
            }

            if (hideToolbar) {
                ((ActivityDrawerMainBinding) mViewDataBinding).appBarMain.toolbar.toolbar.setVisibility(View.GONE);
                ((ActivityDrawerMainBinding) mViewDataBinding).appBarMain.viewLine.setVisibility(View.GONE);
                ((ActivityDrawerMainBinding) mViewDataBinding).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                ((ActivityDrawerMainBinding) mViewDataBinding).drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                ((ActivityDrawerMainBinding) mViewDataBinding).appBarMain.toolbar.toolbar.setVisibility(View.VISIBLE);
                ((ActivityDrawerMainBinding) mViewDataBinding).appBarMain.viewLine.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onFragmentDetached(String tag) {
        if (activityResultCallBack != null) {
            activityResultCallBack = null;
        }
    }


//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        performDependencyInjection();
        setTheme(LanguageUtils.getStyle(this));

        checkPhoneTheme();
        progressDialog = new CustomDialogUtils(this, true, false);

        super.onCreate(savedInstanceState);
        performDataBinding();
        setUpToolbar();
    }

    public void setUpToolbar(Toolbar toolbar, int title, boolean withHome) {
        toolbar.setTitleTextAppearance(this, R.style.bold_style);
        setSupportActionBar(toolbar);
        setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(withHome);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        checkPhoneTheme();
    }

    private void checkPhoneTheme() {
        Configuration configuration = Resources.getSystem().getConfiguration();
        int currentNightMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                getTheme().applyStyle(R.style.AppThemeLight, true);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                getTheme().applyStyle(R.style.AppThemeLight, true);
                break;
        }
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void openActivityOnTokenExpire() {
//        startActivity(LoginActivity.newIntent(this));
//        finish();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void showLoading(boolean isCancelable) {
        progressDialog.showProgress(isCancelable);
    }

    public void showLoading() {
        progressDialog.showProgress();
    }

    public void hideLoading() {
        progressDialog.hideProgress();
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();

    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void showSnackBar(View view, int duration,
                             int icon, String title, String body,
                             String actionText) {
        SnackViewBulider snackViewBulider = new SnackViewBulider(this, view, icon, title,
                body, actionText);
        snackViewBulider.setDuration(duration);
        snackViewBulider.showSnackbar(new SnackViewBulider.SnackbarCallback() {
            @Override
            public void onActionClick(Snackbar snackbar) {
                snackbar.dismiss();
            }
        });
    }

    public void showSnackBar(View view, int icon,
                             String title, String body,
                             String actionText, SnackViewBulider.SnackbarCallback snackbarCallback) {
        SnackViewBulider snackViewBulider = new SnackViewBulider(this, view, icon, title,
                body, actionText);
        snackViewBulider.showSnackbar(snackbarCallback);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

