package com.rukiasoft.githubfetcher.ui.presenters.implementations;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;
import android.view.View;

import com.rukiasoft.githubfetcher.model.UserBasicResponse;
import com.rukiasoft.githubfetcher.model.UserDetailedResponse;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListPresenter;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Roll on 20/7/17.
 */

public class ListPresenterImpl implements ListPresenter{

    private static final String TAG = LogHelper.makeLogTag(ListPresenterImpl.class);
    NetworkHelper mNetworkHelper;

    View mView;

    @Inject
    public ListPresenterImpl(NetworkHelper mNetworkHelper) {
        this.mNetworkHelper = mNetworkHelper;
    }

    @Override
    public void getUsers(MutableLiveData<List<UserBasicResponse>> users) {
        mNetworkHelper.getUsers(users);
    }

    @Override
    public void getUserInfo(String userName, MutableLiveData<UserDetailedResponse> user) {
        mNetworkHelper.getUserInfo(userName, user);
    }

    @Override
    public void showProgressBar() {
        Log.d(TAG, "MUESTRO");
        if(mView != null){
            mView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar() {
        Log.d(TAG, "OCULTO");
        if(mView != null){
            mView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setUsers(List<UserBasicResponse> users) {

    }

    @Override
    public void setView(View view){
        mView = view;
    }

    @Override
    public void removeView(){
        mView = null;
    }
}
