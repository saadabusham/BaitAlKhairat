package com.saad.baitalkhairat.ui.profilejourney.myinfolist;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.databinding.FragmentMyInfoListBinding;
import com.saad.baitalkhairat.interfaces.RecyclerClickNoData;
import com.saad.baitalkhairat.repository.DataManager;
import com.saad.baitalkhairat.ui.adapter.MyInfoAdapter;
import com.saad.baitalkhairat.ui.base.BaseNavigator;
import com.saad.baitalkhairat.ui.base.BaseViewModel;
import com.saad.baitalkhairat.utils.AppConstants;

import java.util.ArrayList;


public class MyInfoListViewModel extends BaseViewModel<MyInfoListNavigator, FragmentMyInfoListBinding>
        implements RecyclerClickNoData {

    MyInfoAdapter myInfoAdapter;

    public <V extends ViewDataBinding, N extends BaseNavigator> MyInfoListViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (MyInfoListNavigator) navigation, (FragmentMyInfoListBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setUpRecycler();
    }

    private void setUpRecycler() {
        getViewBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getMyContext(), LinearLayoutManager.VERTICAL, false));
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        myInfoAdapter = new MyInfoAdapter(getMyContext(), this, getData());
        getViewBinding().recyclerView.setAdapter(myInfoAdapter);
    }

    private ArrayList<Integer> getData() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.string.basic_information);
        arrayList.add(R.string.information_about_my_degree);
        arrayList.add(R.string.social_links);
        arrayList.add(R.string.my_identification_papers);
        return arrayList;
    }


    @Override
    public void onClick(int position) {

        Bundle data = new Bundle();
        data.putSerializable(AppConstants.BundleData.USER, getNavigator().getUser());
        switch (position) {
            case 0:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_myInfoListFragment_to_editProfileFragment, data);
                break;
            case 1:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_myInfoListFragment_to_userDegreeFragment, data);
                break;
            case 2:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_myInfoListFragment_to_userSocialLinksFragment, data);
                break;
            case 3:
                Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_myInfoListFragment_to_identificationDocumentFragment, data);
                break;

        }
    }
}
