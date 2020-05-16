package com.saad.baitalkhairat.viewmodel;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.auth.createpassword.CreatePasswordViewModel;
import com.saad.baitalkhairat.ui.auth.loginJourney.login.LoginViewModel;
import com.saad.baitalkhairat.ui.auth.otpverifier.OtpVerifierViewModel;
import com.saad.baitalkhairat.ui.auth.registerJourney.register.RegisterViewModel;
import com.saad.baitalkhairat.ui.auth.signinholder.SignInHolderViewModel;
import com.saad.baitalkhairat.ui.auth.verifyphonenumber.VerifyPhoneNumberViewModel;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.emptyactivity.EmptyActivityViewModel;
import com.saad.baitalkhairat.ui.emptyfragment.EmptyFragmentViewModel;
import com.saad.baitalkhairat.ui.filebox.FileBoxViewModel;
import com.saad.baitalkhairat.ui.intro.casedetails.CaseDetailsViewModel;
import com.saad.baitalkhairat.ui.intro.cases.CasesViewModel;
import com.saad.baitalkhairat.ui.intro.category.CategoryViewModel;
import com.saad.baitalkhairat.ui.intro.donors.DonorsViewModel;
import com.saad.baitalkhairat.ui.intro.filtercases.FilterCasesViewModel;
import com.saad.baitalkhairat.ui.intro.home.HomeViewModel;
import com.saad.baitalkhairat.ui.intro.needy.NeedyViewModel;
import com.saad.baitalkhairat.ui.main.MainActivityViewModel;
import com.saad.baitalkhairat.ui.profilejourney.account.AccountViewModel;
import com.saad.baitalkhairat.ui.splashscreen.SplashScreenViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @ViewDataBinding : Base class for generated data binding classes
 * @ViewModel: The purpose of the ViewModel is to acquire and keep the information that is necessary for an
 * Activity or a Fragment. The Activity or the Fragment should be able to observe changes in the
 * ViewModel. ViewModels usually expose this information via LiveData or Android Data
 * Binding. You can also use any observability construct from you favorite framework.
 * <p>
 * ViewModel's only responsibility is to manage the data for the UI. It <b>should never</b> access
 * your view hierarchy or hold a reference back to the Activity or the Fragment.
 * <p>
 */
@Singleton
public class ViewModelProviderFactory<V extends ViewDataBinding, N extends BaseNavigator>
        extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final Context mContext;


    @Inject
    public ViewModelProviderFactory(DataManager dataManager, Context mContext) {
        this.dataManager = dataManager;
        this.mContext = mContext;
    }

    public <T extends ViewModel> T create(Class<T> modelClass, V viewDataBinding, N navigation) {
        if (modelClass.isAssignableFrom(EmptyFragmentViewModel.class)) {
            //noinspection unchecked
            return (T) new EmptyFragmentViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(EmptyActivityViewModel.class)) {
            //noinspection unchecked
            return (T) new EmptyActivityViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(SplashScreenViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashScreenViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            //noinspection unchecked
            return (T) new RegisterViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(FileBoxViewModel.class)) {
            //noinspection unchecked
            return (T) new FileBoxViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(VerifyPhoneNumberViewModel.class)) {
            //noinspection unchecked
            return (T) new VerifyPhoneNumberViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(OtpVerifierViewModel.class)) {
            //noinspection unchecked
            return (T) new OtpVerifierViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(CreatePasswordViewModel.class)) {
            //noinspection unchecked
            return (T) new CreatePasswordViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            //noinspection unchecked
            return (T) new MainActivityViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            //noinspection unchecked
            return (T) new HomeViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(DonorsViewModel.class)) {
            //noinspection unchecked
            return (T) new DonorsViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(CategoryViewModel.class)) {
            //noinspection unchecked
            return (T) new CategoryViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(CasesViewModel.class)) {
            //noinspection unchecked
            return (T) new CasesViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(NeedyViewModel.class)) {
            //noinspection unchecked
            return (T) new NeedyViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(CaseDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new CaseDetailsViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(FilterCasesViewModel.class)) {
            //noinspection unchecked
            return (T) new FilterCasesViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(SignInHolderViewModel.class)) {
            //noinspection unchecked
            return (T) new SignInHolderViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(AccountViewModel.class)) {
            //noinspection unchecked
            return (T) new AccountViewModel(mContext, dataManager, viewDataBinding, navigation);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}