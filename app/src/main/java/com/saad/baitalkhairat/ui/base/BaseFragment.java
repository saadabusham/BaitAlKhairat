package com.saad.baitalkhairat.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ToolbarBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;

import dagger.android.support.AndroidSupportInjection;


public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

    private BaseActivity mActivity;
    private View mRootView;
    private T mViewDataBinding;
    private V mViewModel;

    public abstract int getBindingVariable();

    public abstract boolean hideToolbar();

    public abstract boolean hideBottomSheet();

    public abstract boolean isNeedActivityResult();

    public abstract boolean hasOptionMenu();

    public abstract ActivityResultCallBack activityResultCallBack();

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    public abstract Context getMyContext();

    protected abstract void setUp();

    private void notifiActivity(Context context) {
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            if (isNeedActivityResult()) {
                activity.onFragmentAttachedNeedActivityResult(hideToolbar(), hideBottomSheet(), activityResultCallBack());
            } else {
                activity.onFragmentAttached(hideToolbar(), hideBottomSheet());
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notifiActivity(context);
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        notifiActivity(getMyContext());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        if (mRootView != null) {
            return mRootView;
        } else {
            mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            mRootView = mViewDataBinding.getRoot();
            this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
            mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
            mViewDataBinding.setLifecycleOwner(this);
            mViewDataBinding.executePendingBindings();
            setUp();
            return mRootView;
        }
    }


    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    protected void setUpToolbar(ToolbarBinding toolbar, String TAG, int title) {
        toolbar.toolbar.setTitle(title);
        toolbar.toolbarTitle.setVisibility(View.GONE);
        toolbar.toolbar.setTitleTextAppearance(getContext(), R.style.bold_style);
        setUpToolbar(toolbar, TAG);
    }

    protected void setUpToolbar(ToolbarBinding toolbar, String TAG, String title) {
        if (!title.isEmpty()) {
            toolbar.toolbar.setTitle(title);
            toolbar.toolbarTitle.setVisibility(View.VISIBLE);
        }
        setUpToolbar(toolbar, TAG);
    }


    protected void setUpToolbar(ToolbarBinding toolbar, String TAG) {

//        ((MainActivity) getActivity()).setSupportActionBar(toolbar.toolbar);

        toolbar.toolbar.setNavigationIcon(getMyContext().getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.toolbar.setNavigationOnClickListener(v -> {
            getBaseActivity().onFragmentDetached(TAG);
            getViewModel().popUp();
        });
    }


    public interface Callback {

        void onFragmentAttached(boolean hideToolbar, boolean hideBottomSheet);

        void onFragmentDetached(String tag);

        void onFragmentAttachedNeedActivityResult(boolean hideToolbar, boolean hideBottomSheet,
                                                  ActivityResultCallBack activityResultCallBack);
    }

}

