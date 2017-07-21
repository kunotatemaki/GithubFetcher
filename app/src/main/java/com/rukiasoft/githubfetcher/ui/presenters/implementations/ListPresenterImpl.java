package com.rukiasoft.githubfetcher.ui.presenters.implementations;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.view.View;

import com.rukiasoft.githubfetcher.model.UserBasic;
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
    private NetworkHelper mNetworkHelper;

    private View mView;

    @Inject
    ListPresenterImpl(NetworkHelper mNetworkHelper) {
        this.mNetworkHelper = mNetworkHelper;
    }

    @Override
    public void getUsers(MutableLiveData<List<UserBasic>> users) {
        mNetworkHelper.getUsers(users);
    }

    @Override
    public void showProgressBar() {
        Log.d(TAG, "MUESTRO LA BARRA");
        if(mView != null){
            mView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar() {
        Log.d(TAG, "OCULTO LA BARRA");
        if(mView != null){
            mView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setUsers(List<UserBasic> users) {

    }

    @Override
    public void setView(View view){
        mView = view;
    }

    @Override
    public void removeView(){
        mView = null;
    }

    @Override
    public boolean downloadUsersIfNecessary(MutableLiveData<List<UserBasic>> users) {
        if(users.getValue() == null){
            Log.d(TAG, "No hay usuarios, los descargo");
            showProgressBar();
            getUsers(users);
            return true;
        }
        return false;
    }
}
