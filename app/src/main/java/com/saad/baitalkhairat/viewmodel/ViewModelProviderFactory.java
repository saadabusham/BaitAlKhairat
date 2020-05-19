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
import com.saad.baitalkhairat.ui.cartjourney.cart.CartViewModel;
import com.saad.baitalkhairat.ui.donatejourney.myneedsholder.MyDonationHolderViewModel;
import com.saad.baitalkhairat.ui.donatejourney.myneedslist.MyDonationListViewModel;
import com.saad.baitalkhairat.ui.donatejourney.needappliedsuccessful.DonorAppliedSuccessfulViewModel;
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
import com.saad.baitalkhairat.ui.menu.about_us.AboutUsViewModel;
import com.saad.baitalkhairat.ui.menu.commonquastions.QuestionsViewModel;
import com.saad.baitalkhairat.ui.menu.privacy_policy.PrivacyPolicyViewModel;
import com.saad.baitalkhairat.ui.menu.servicecenter.ServiceCenterViewModel;
import com.saad.baitalkhairat.ui.needjourney.applyneeds.ApplyNeedsViewModel;
import com.saad.baitalkhairat.ui.needjourney.myneedsholder.MyNeedsHolderViewModel;
import com.saad.baitalkhairat.ui.needjourney.myneedslist.MyNeedsListViewModel;
import com.saad.baitalkhairat.ui.needjourney.needappliedsuccessful.NeedAppliedSuccessfulViewModel;
import com.saad.baitalkhairat.ui.notificationjourney.notificationdetails.NotificationDetailsViewModel;
import com.saad.baitalkhairat.ui.notificationjourney.notifications.NotificationsViewModel;
import com.saad.baitalkhairat.ui.profilejourney.account.AccountViewModel;
import com.saad.baitalkhairat.ui.profilejourney.editprofile.EditProfileViewModel;
import com.saad.baitalkhairat.ui.profilejourney.identification_document.IdentificationDocumentViewModel;
import com.saad.baitalkhairat.ui.profilejourney.myinfolist.MyInfoListViewModel;
import com.saad.baitalkhairat.ui.profilejourney.userdegree.UserDegreeViewModel;
import com.saad.baitalkhairat.ui.profilejourney.usersociallinks.UserSocialLinksViewModel;
import com.saad.baitalkhairat.ui.profilejourney.viewdocument.ViewDocumentViewModel;
import com.saad.baitalkhairat.ui.splashscreen.SplashScreenViewModel;
import com.saad.baitalkhairat.ui.walletjourney.banktransfer.BankTransferViewModel;
import com.saad.baitalkhairat.ui.walletjourney.charge.ChargeViewModel;
import com.saad.baitalkhairat.ui.walletjourney.chargeto.ChargeToViewModel;
import com.saad.baitalkhairat.ui.walletjourney.creditcard.CreditCardViewModel;
import com.saad.baitalkhairat.ui.walletjourney.paymentholder.PaymentHolderViewModel;
import com.saad.baitalkhairat.ui.walletjourney.wallet.WalletViewModel;

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
        } else if (modelClass.isAssignableFrom(NotificationsViewModel.class)) {
            //noinspection unchecked
            return (T) new NotificationsViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(NotificationDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new NotificationDetailsViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(ServiceCenterViewModel.class)) {
            //noinspection unchecked
            return (T) new ServiceCenterViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(PrivacyPolicyViewModel.class)) {
            //noinspection unchecked
            return (T) new PrivacyPolicyViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(AboutUsViewModel.class)) {
            //noinspection unchecked
            return (T) new AboutUsViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(QuestionsViewModel.class)) {
            //noinspection unchecked
            return (T) new QuestionsViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(MyInfoListViewModel.class)) {
            //noinspection unchecked
            return (T) new MyInfoListViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(EditProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new EditProfileViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(UserSocialLinksViewModel.class)) {
            //noinspection unchecked
            return (T) new UserSocialLinksViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(UserDegreeViewModel.class)) {
            //noinspection unchecked
            return (T) new UserDegreeViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(IdentificationDocumentViewModel.class)) {
            //noinspection unchecked
            return (T) new IdentificationDocumentViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(ViewDocumentViewModel.class)) {
            //noinspection unchecked
            return (T) new ViewDocumentViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(MyNeedsHolderViewModel.class)) {
            //noinspection unchecked
            return (T) new MyNeedsHolderViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(MyNeedsListViewModel.class)) {
            //noinspection unchecked
            return (T) new MyNeedsListViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(MyDonationHolderViewModel.class)) {
            //noinspection unchecked
            return (T) new MyDonationHolderViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(MyDonationListViewModel.class)) {
            //noinspection unchecked
            return (T) new MyDonationListViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(ApplyNeedsViewModel.class)) {
            //noinspection unchecked
            return (T) new ApplyNeedsViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(NeedAppliedSuccessfulViewModel.class)) {
            //noinspection unchecked
            return (T) new NeedAppliedSuccessfulViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(DonorAppliedSuccessfulViewModel.class)) {
            //noinspection unchecked
            return (T) new DonorAppliedSuccessfulViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(CartViewModel.class)) {
            //noinspection unchecked
            return (T) new CartViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(WalletViewModel.class)) {
            //noinspection unchecked
            return (T) new WalletViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(ChargeToViewModel.class)) {
            //noinspection unchecked
            return (T) new ChargeToViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(ChargeViewModel.class)) {
            //noinspection unchecked
            return (T) new ChargeViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(PaymentHolderViewModel.class)) {
            //noinspection unchecked
            return (T) new PaymentHolderViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(BankTransferViewModel.class)) {
            //noinspection unchecked
            return (T) new BankTransferViewModel(mContext, dataManager, viewDataBinding, navigation);
        } else if (modelClass.isAssignableFrom(CreditCardViewModel.class)) {
            //noinspection unchecked
            return (T) new CreditCardViewModel(mContext, dataManager, viewDataBinding, navigation);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}