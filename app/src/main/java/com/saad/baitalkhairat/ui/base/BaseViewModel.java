
package com.saad.baitalkhairat.ui.base;

import android.content.Context;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.utils.SnackViewBulider;

import java.lang.ref.WeakReference;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;


public abstract class BaseViewModel<N, T extends ViewDataBinding> extends ViewModel {


    private WeakReference<N> mNavigator;
    private T ViewBinding;
    private Context mContext;
    private DataManager dataManager;

    public BaseViewModel(Context mContext, DataManager dataManager, N navigator, T viewBinding) {
        this.mContext = mContext;
        this.dataManager = dataManager;
        this.ViewBinding = viewBinding;
        this.mNavigator = new WeakReference<>(navigator);
    }


    public BaseViewModel() {
    }

    protected abstract void setUp();

    protected T getViewBinding() {
        return ViewBinding;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    protected N getNavigator() {
        return mNavigator.get();
    }

    protected Context getMyContext() {
        return mContext;
    }

    protected DataManager getDataManager() {
        return dataManager;
    }

    protected void showLoading() {
        ((BaseActivity<ViewDataBinding, BaseViewModel>) getMyContext()).showLoading();
    }

    protected void hideLoading() {
        ((BaseActivity<ViewDataBinding, BaseViewModel>) getMyContext()).hideLoading();
    }

    protected BaseActivity getBaseActivity() {
        return ((BaseActivity) getMyContext());
    }

    public void showSnackBar(String title, String message, String actionText, SnackViewBulider.SnackbarCallback snackbarCallback) {
        getBaseActivity().showSnackBar(getViewBinding().getRoot(), R.drawable.ic_warning,
                title, message, actionText, snackbarCallback);
    }

    public void showToast(String message) {
        Toast.makeText(getMyContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void popUp() {
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment).popBackStack();
    }

    public void showErrorSnackBar(String error) {
        showSnackBar(getMyContext().getResources().getString(R.string.error),
                error,
                getMyContext().getResources().getString(R.string.ok),
                new SnackViewBulider.SnackbarCallback() {
                    @Override
                    public void onActionClick(Snackbar snackbar) {
                        snackbar.dismiss();
                    }
                });
    }

}
