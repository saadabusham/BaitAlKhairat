package com.saad.baitalkhairat.ui.walletjourney.transactions;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentTransactionsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class TransactionsFragment extends BaseFragment<FragmentTransactionsBinding, TransactionsViewModel> implements TransactionsNavigator {

    private static final String TAG = TransactionsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private TransactionsViewModel mViewModel;
    private FragmentTransactionsBinding mViewBinding;


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
        return R.layout.fragment_transactions;
    }

    @Override
    public TransactionsViewModel getViewModel() {
        mViewModel = (TransactionsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(TransactionsViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.all_transactions);
        mViewModel.setUp();
    }


}
