package com.saad.baitalkhairat.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityDrawerMainBinding;
import com.saad.baitalkhairat.enums.DrawerWithIconTypes;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.model.MenuItem;
import com.saad.baitalkhairat.model.Rate;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.adapter.DrawerAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.ui.dialog.RateDialog;
import com.saad.baitalkhairat.utils.DeviceUtils;
import com.saad.baitalkhairat.utils.LanguageUtils;

import java.util.ArrayList;

public class MainActivityViewModel extends BaseViewModel<MainActivityNavigator, ActivityDrawerMainBinding>
        implements RecyclerClickNoData {

    ArrayList<MenuItem> menuItems;

    DrawerAdapter drawerAdapter;

    public <V extends ViewDataBinding, N extends BaseNavigator> MainActivityViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (MainActivityNavigator) navigation, (ActivityDrawerMainBinding) viewDataBinding);

    }

    @Override
    protected void setUp() {
        if (SessionManager.getIsThereNotification())
            getViewBinding().appBarMain.drawerMainContent.bottomSheet.getMenu().getItem(2)
                    .setIcon(getMyContext().getResources().getDrawable(R.drawable.ic_notification_active_nav));

        getViewBinding().appBarMain.drawerMainContent.bottomSheet.setItemIconTintList(null);
        menuItems = getDrawerList();
        drawerAdapter = new DrawerAdapter(getMyContext(), menuItems, this);
        getViewBinding().drawerList.recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().drawerList.recyclerView.setAdapter(drawerAdapter);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getBaseActivity(), getViewBinding().drawerLayout, getViewBinding().appBarMain.toolbar.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        initDrawer(toggle, getViewBinding().drawerLayout);

    }

    public void initDrawer(ActionBarDrawerToggle toggle, DrawerLayout drawer) {
        Drawable drawable = ResourcesCompat.getDrawable(getMyContext().getResources(), R.drawable.ic_menu,
                (getBaseActivity().getTheme()));
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(drawable);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(v -> {
            if (drawer.isDrawerVisible(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    public ArrayList<MenuItem> getDrawerList() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.string.service_center, R.drawable.ic_help_center, DrawerWithIconTypes.WITH_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.share_app, R.drawable.ic_share_app, DrawerWithIconTypes.WITH_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.rate_app, R.drawable.ic_rate_app, DrawerWithIconTypes.WITH_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.use_language, R.drawable.ic_language, DrawerWithIconTypes.WITH_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.privacy_policy, R.drawable.ic_privacy_policy, DrawerWithIconTypes.NO_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.about_us, R.drawable.ic_terms_condition, DrawerWithIconTypes.NO_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.login, R.drawable.ic_logout, !SessionManager.isLoggedIn() ?
                DrawerWithIconTypes.NO_ICON.getMode() :
                DrawerWithIconTypes.FULL_INVISIBLE_ITEM.getMode()));
        menuItems.add(new MenuItem(R.string.logout, R.drawable.ic_logout, SessionManager.isLoggedIn() ?
                DrawerWithIconTypes.WITH_ICON.getMode() :
                DrawerWithIconTypes.FULL_INVISIBLE_ITEM.getMode()));
        return menuItems;
    }

    @Override
    public void onClick(int position) {
        getViewBinding().drawerLayout.closeDrawers();
        switch (position) {
            case 0:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.serviceCenterFragment);
                break;

            case 1:
                DeviceUtils.shareApp(getBaseActivity());
                break;
            case 2:
                showRateDialog();
                break;
            case 3:
                LanguageUtils.checkAndUpdateLanguage(getBaseActivity());
                break;
            case 4:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.privacyPolicyFragment);
                break;
            case 5:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.aboutUsFragment);
                break;
            case 6:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.signInHolderFragment);
                break;
            case 7:

                break;
        }
    }

    private void showRateDialog() {
        RateDialog rateDialog = new RateDialog(getMyContext(), new RateDialog.RateCallback() {
            @Override
            public void callback(Rate rate) {

            }
        });
        rateDialog.showRateDialog();
    }
}
