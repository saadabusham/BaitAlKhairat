package com.saad.baitalkhairat.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityDrawerMainBinding;
import com.saad.baitalkhairat.model.User;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.services.TokenService;
import com.saad.baitalkhairat.ui.base.BaseActivity;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityDrawerMainBinding, MainActivityViewModel>
        implements MainActivityNavigator {

    NavController navController;

    @Inject
    ViewModelProviderFactory factory;
    private MainActivityViewModel mMainViewModel;
    private ActivityDrawerMainBinding mViewBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, TokenService.class));
    }

    @Override
    public int getBindingVariable() {
        return com.saad.baitalkhairat.BR.viewModel;
    }

    @Override
    public void setUpToolbar() {
        setUpToolbar(getViewDataBinding().appBarMain.toolbar.toolbar,
                R.string.app_name, false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_drawer_main;
    }

    @Override
    public MainActivityViewModel getViewModel() {
        mMainViewModel = (MainActivityViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(MainActivityViewModel.class, getViewDataBinding(), this);
        return mMainViewModel;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = getViewDataBinding();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.setGraph(R.navigation.nav_graph);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .build();
        NavigationUI.setupWithNavController(getViewDataBinding().appBarMain.drawerMainContent.bottomSheet, navController);
//        startTokenService();
        mMainViewModel.setUp();
    }

    private void startTokenService() {
        if (User.getInstance().getTokenResponse() != null) {
            Intent intent = new Intent(getMyContext(), TokenService.class);
            startService(intent);
        }
    }

    public void onActivityResultFromFragment(int requestCode, int resultCode, @Nullable Intent data) {
        onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getActivityResultCallBack() != null) {
            getActivityResultCallBack().callBack(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemCart:
                openCart();
                break;
            case R.id.itemSearch: {

                break;
            }
        }
        return true;
    }

    public void moveBottomNavigation(int id) {
        getViewDataBinding().appBarMain.drawerMainContent.bottomSheet.setSelectedItemId(id);
    }
}