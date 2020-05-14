package com.saad.baitalkhairat.ui.filebox;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.ActivityFileBoxBinding;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.base.BaseActivity;
import com.saad.baitalkhairat.viewmodel.ViewModelProviderFactory;

import java.io.File;

import javax.inject.Inject;

public class FileBoxActivity extends BaseActivity<ActivityFileBoxBinding, FileBoxViewModel>
        implements FileBoxNavigator {

    String url = "";
    @Inject
    ViewModelProviderFactory factory;
    private FileBoxViewModel mFileBoxViewModel;
    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            hideLoading();
            mFileBoxViewModel.openFile(new File(mFileBoxViewModel.getState()));
            mFileBoxViewModel.setmFilePath("");
            mFileBoxViewModel.setState("");
        }
    };
    private ActivityFileBoxBinding mViewBinding;

    public static Intent getStartIntent(Context context, String url) {
        Intent intent = new Intent(context, FileBoxActivity.class);
        intent.putExtra("url", url);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return com.saad.baitalkhairat.BR.viewModel;
    }

    @Override
    public void setUpToolbar() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_file_box;
    }

    @Override
    public FileBoxViewModel getViewModel() {
        mFileBoxViewModel = (FileBoxViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(FileBoxViewModel.class, getViewDataBinding(), this);
        return mFileBoxViewModel;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = getViewDataBinding();
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        url = getIntent().getStringExtra("url");
        mFileBoxViewModel.downloadFile(url);
    }
}