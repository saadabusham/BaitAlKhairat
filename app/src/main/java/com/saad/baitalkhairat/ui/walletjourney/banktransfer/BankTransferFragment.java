package com.saad.baitalkhairat.ui.walletjourney.banktransfer;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentBankTransferBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.model.Amount;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.utils.AppConstants;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class BankTransferFragment extends BaseFragment<FragmentBankTransferBinding, BankTransferViewModel> implements BankTransferNavigator {

    private static final String TAG = BankTransferFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private BankTransferViewModel mViewModel;
    private FragmentBankTransferBinding mViewBinding;


    @Override
    public boolean hasOptionMenu() {
        return false;
    }

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
    public ActivityResultCallBack activityResultCallBack() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bank_transfer;
    }

    @Override
    public BankTransferViewModel getViewModel() {
        mViewModel = (BankTransferViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(BankTransferViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        mViewModel.setUp();
    }

    @Override
    public Amount getAmount() {
        return (Amount) getArguments().getSerializable(AppConstants.BundleData.AMOUNT);
    }
}
