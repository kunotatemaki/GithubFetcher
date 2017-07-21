package com.rukiasoft.githubfetcher.ui.presenters.implementations;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContract;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsPresenterContract;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContract;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import java.util.List;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

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
        if(!downloadUsersIfNecessary(user)){
            //no ha descargado porque no hacía falta, lo pongo en el recycler
            refreshUI(user);
        }
    }

    private boolean downloadUsersIfNecessary(MutableLiveData<UserDetailed> user) {
        if(user.getValue() == null){
            Log.d(TAG, "No hay usuarios, los descargo");
            showProgressBar();
            getUser(user);
            return true;
        }
        return false;
    }

    private void refreshUI(MutableLiveData<UserDetailed> user){
        try {
            mDetailsActivityContract = checkNotNull(mDetailsActivityContract);
            mDetailsActivityContract.setUser(user.getValue());
            hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void showProgressBar(){
        try {
            mDetailsActivityContract = checkNotNull(mDetailsActivityContract);
            mDetailsActivityContract.showProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void hideProgressBar(){
        try {
            mDetailsActivityContract = checkNotNull(mDetailsActivityContract);
            mDetailsActivityContract.hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @VisibleForTesting
    public DetailsActivityContract getmDetailsActivityContract() {
        return mDetailsActivityContract;
    }
}
