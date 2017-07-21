package com.rukiasoft.githubfetcher.ui.presenters.implementations;

import android.arch.lifecycle.MutableLiveData;

import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContract;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsPresenterContract;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import javax.inject.Inject;

/**
 * Created by Roll on 21/7/17.
 */

public class DetailsPresenterImpl implements DetailsPresenterContract {

    private final static String TAG = LogHelper.makeLogTag(DetailsPresenterImpl.class);

    private String name;
    private NetworkHelper mNetworkHelper;

    private DetailsActivityContract mDetailsActivityContract;

    NetworkHelper networkHelper;

    @Inject
    public DetailsPresenterImpl(NetworkHelper mNetworkHelper) {
        this.mNetworkHelper = mNetworkHelper;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }




    @Override
    public void getUser(MutableLiveData<UserDetailed> user) {
        mNetworkHelper.getUserInfo(name, user);
    }

    @Override
    public void setView(DetailsActivityContract view) {
        mDetailsActivityContract = view;
    }

    @Override
    public void removeView() {
        mDetailsActivityContract = null;
    }

    @Override
    public void setData(MutableLiveData<UserDetailed> user) {

    }


}
