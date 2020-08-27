package com.saad.baitalkhairat.repository;


import com.saad.baitalkhairat.App;
import com.saad.baitalkhairat.repository.db.database.LogDatabase;
import com.saad.baitalkhairat.repository.network.services.AppService;
import com.saad.baitalkhairat.repository.network.services.AuthService;
import com.saad.baitalkhairat.repository.network.services.DataExampleService;
import com.saad.baitalkhairat.repository.network.services.DonorsService;
import com.saad.baitalkhairat.repository.network.services.NeedsService;
import com.saad.baitalkhairat.repository.network.services.WalletService;

import javax.inject.Singleton;

/**
 * Private Instance @Singleton Class That uses @Room for Data Management Purpose.
 * This is like the RoomDatabase Base Class to construct a room database instance.
 */
@Singleton
public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }


    public LogDatabase getLogDatabse() {
        return LogDatabase.getInstance(App.getInstance());
    }

    public DataExampleService getDataService() {
        return DataExampleService.getInstance();
    }

    public AuthService getAuthService() {
        return AuthService.getInstance();
    }

    public AppService getAppService() {
        return AppService.getInstance();
    }

    public DonorsService getDonorsService() {
        return DonorsService.getInstance();
    }

    public WalletService getWalletService() {
        return WalletService.getInstance();
    }

    public NeedsService getNeedsService() {
        return NeedsService.getInstance();
    }
}
