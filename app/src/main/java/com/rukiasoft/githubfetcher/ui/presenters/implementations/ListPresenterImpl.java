package com.rukiasoft.githubfetcher.ui.presenters.implementations;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContract;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListPresenterContract;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import java.util.List;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Roll on 20/7/17.
 */

public class ListPresenterImpl implements ListPresenterContract {

    private static final String TAG = LogHelper.makeLogTag(ListPresenterImpl.class);
    private NetworkHelper mNetworkHelper;

    private ListActivityContract listActivityContract;

    @Inject
    public ListPresenterImpl(NetworkHelper mNetworkHelper) {
        this.mNetworkHelper = mNetworkHelper;
    }

    @Override
    public void getUsers(MutableLiveData<List<UserBasic>> users) {

        mNetworkHelper.getUsers(users);
    }


    @Override
    public void setView(ListActivityContract view){
        listActivityContract = view;
    }

    @Override
    public void removeView(){
        listActivityContract = null;
    }


    @Override
    public void setData(MutableLiveData<List<UserBasic>> users) {
        if(!downloadUsersIfNecessary(users)){
            //no ha descargado porque no hacía falta, lo pongo en el recycler
            refreshUI(users);
        }
    }

    private boolean downloadUsersIfNecessary(MutableLiveData<List<UserBasic>> users) {
        if(users.getValue() == null){
            Log.d(TAG, "No hay usuarios, los descargo");
            showProgressBar();
            getUsers(users);
            return true;
        }
        return false;
    }

    private void refreshUI(MutableLiveData<List<UserBasic>> users){
        try {
            listActivityContract = checkNotNull(listActivityContract);
            listActivityContract.setUsers(users.getValue());
            hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void showProgressBar(){
        try {
            listActivityContract = checkNotNull(listActivityContract);
            listActivityContract.showProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void hideProgressBar(){
        try {
            listActivityContract = checkNotNull(listActivityContract);
            listActivityContract.hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @VisibleForTesting
    public ListActivityContract getListActivityContract(){
        return listActivityContract;
    }
}
