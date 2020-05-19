package com.saad.baitalkhairat.ui.walletjourney.paymentholder;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentPaymentHolderBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.model.Amount;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class PaymentHolderFragment extends BaseFragment<FragmentPaymentHolderBinding, PaymentHolderViewModel>
        implements PaymentHolderNavigator {

    private static final String TAG = PaymentHolderFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private PaymentHolderViewModel mViewModel;
    private FragmentPaymentHolderBinding mViewBinding;


    @Override
    public int getBindingVariable() {
        return com.saad.baitalkhairat.BR.viewModel;
    }

    @Override
    public boolean hideToolbar() {
        return true;
    }

    @Override
    public boolean hideBottomSheet() {
        return true;
    }

    @Override
    public boolean isNeedActivityResult() {
        return false;
    }

    @Override
    public boolean hasOptionMenu() {
        return false;
    }

    @Override
    public ActivityResultCallBack activityResultCallBack() {
        return null;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_payment_holder;
    }

    @Override
    public PaymentHolderViewModel getViewModel() {
        mViewModel = (PaymentHolderViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(PaymentHolderViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.payment_method);
        mViewModel.setUp();
    }

    @Override
    public FragmentManager getChildFragment() {
        return getChildFragmentManager();
    }

    @Override
    public Amount getAmount() {
        return (Amount) getArguments().getSerializable(AppConstants.BundleData.AMOUNT);
    }

}
