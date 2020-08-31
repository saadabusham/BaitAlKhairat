package com.saad.baitalkhairat.ui.intro.needy;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentNeedyBinding;
import com.saad.baitalkhairat.helper.SessionManager;
import com.saad.baitalkhairat.interfaces.RecyclerClick;
import com.saad.baitalkhairat.model.needs.Needy;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.adapter.NeedyAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;


public class NeedyViewModel extends BaseViewModel<NeedyNavigator, FragmentNeedyBinding>
        implements RecyclerClick<Needy> {

    NeedyAdapter needyAdapter;

    public <V extends ViewDataBinding, N extends BaseNavigator> NeedyViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (NeedyNavigator) navigation, (FragmentNeedyBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecycler();
    }

    private void setUpRecycler() {
        getViewBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        needyAdapter = new NeedyAdapter(getMyContext(), this);
        getViewBinding().recyclerView.setAdapter(needyAdapter);
        getLocalData();
    }

    private void getLocalData() {
        needyAdapter.addItem(new Needy(1, "https://test-1.baytalkhayrat.net/website/images/financial-aid.png",
                getMyContext().getResources().getString(R.string.apply_your_needs),
                getMyContext().getResources().getString(R.string.submit_a_request_for_your_needs_with_all_the_details_and_papers_in_order_to_present_it_to_the_donors)));

        needyAdapter.addItem(new Needy(3, "https://test-1.baytalkhayrat.net/website/images/urgentCase.png",
                getMyContext().getResources().getString(R.string.urgent_case),
                getMyContext().getResources().getString(R.string.for_urgent_cases_that_need_immediate_assistance_be_sure_to_fill_out_the_application_completely)));
    }
    private void notifiAdapter() {
        getViewBinding().recyclerView.post(new Runnable() {
            @Override
            public void run() {
                needyAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(Needy needy, int position) {
        if (SessionManager.isLoggedInAndLogin(getBaseActivity())) {
            Bundle data = new Bundle();
            data.putInt(AppConstants.BundleData.DEGREE, needy.getId());
            Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.applyNeedsFragment, data);

        }
    }

}
