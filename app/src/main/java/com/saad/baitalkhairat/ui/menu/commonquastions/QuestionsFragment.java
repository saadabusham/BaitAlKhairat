package com.saad.baitalkhairat.ui.menu.commonquastions;

import android.content.Context;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentCommonQuastionsBinding;
import com.saad.baitalkhairat.interfaces.ActivityResultCallBack;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseFragment;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;


public class QuestionsFragment extends BaseFragment<FragmentCommonQuastionsBinding, QuestionsViewModel> implements QuestionsNavigator {

    private static final String TAG = QuestionsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private QuestionsViewModel mViewModel;
    private FragmentCommonQuastionsBinding mViewBinding;


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
        return R.layout.fragment_common_quastions;
    }

    @Override
    public QuestionsViewModel getViewModel() {
        mViewModel = (QuestionsViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(QuestionsViewModel.class, getViewDataBinding(), this);
        return mViewModel;
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    protected void setUp() {
        mViewBinding = getViewDataBinding();
        setUpToolbar(mViewBinding.toolbar, TAG, R.string.common_questions);
        mViewModel.setUp();
    }


}
