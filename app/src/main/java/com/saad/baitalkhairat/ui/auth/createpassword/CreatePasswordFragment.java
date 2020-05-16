package com.saad.baitalkhairat.ui.auth.createpassword;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentCreatePasswordBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class CreatePasswordFragment extends BaseFragment<FragmentCreatePasswordBinding, CreatePasswordViewModel>
        implements CreatePasswordNavigator {

    private static final String TAG = CreatePasswordFragment.class.getSimpleName();
    String token = "";
    @Inject
    ViewModelProviderFactory factory;
    private CreatePasswordViewModel mCreatePasswordViewModel;
    private FragmentCreatePasswordBinding mViewBinding;



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
        return R.layout.fragment_create_password;
    }

    @Override
    public CreatePasswordViewModel getViewModel() {
        mCreatePasswordViewModel = (CreatePasswordViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(CreatePasswordViewModel.class, getViewDataBinding(), this);
        return mCreatePasswordViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
//        token = getIntent().getStringExtra("token");
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.new_password);
        mCreatePasswordViewModel.setUp();
    }


    @Override
    public String getToken() {
        return token;
    }
}