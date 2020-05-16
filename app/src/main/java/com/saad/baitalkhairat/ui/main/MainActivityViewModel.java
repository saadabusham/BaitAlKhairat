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
import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.model.MenuItem;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.adapter.DrawerAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;

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
        getViewBinding().appBarMain.drawerMainContent.bottomSheet.setItemIconTintList(null);
        menuItems = getVendorDrawerList();
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

    public ArrayList<MenuItem> getVendorDrawerList() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.string.help_center, R.drawable.ic_help_center, DrawerWithIconTypes.WITH_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.share_app, R.drawable.ic_share_app, DrawerWithIconTypes.WITH_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.rate_app, R.drawable.ic_rate_app, DrawerWithIconTypes.WITH_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.use_language, R.drawable.ic_language, DrawerWithIconTypes.WITH_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.use_language, R.drawable.ic_language, DrawerWithIconTypes.HIDE_ITEM.getMode()));
        menuItems.add(new MenuItem(R.string.privacy_policy, R.drawable.ic_privacy_policy, DrawerWithIconTypes.NO_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.terms_and_conditions, R.drawable.ic_terms_condition, DrawerWithIconTypes.NO_ICON.getMode()));
        menuItems.add(new MenuItem(R.string.logout, R.drawable.ic_logout, DrawerWithIconTypes.WITH_ICON.getMode()));
        return menuItems;
    }

    @Override
    public void onClick(int position) {

        switch (position) {
            case 7:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.signInHolderFragment);
                break;
        }
    }
}
