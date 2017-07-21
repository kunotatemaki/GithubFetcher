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

    private ListActivityContract mListActivityContract;

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
        mListActivityContract = view;
    }

    @Override
    public void removeView(){
        mListActivityContract = null;
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
            mListActivityContract = checkNotNull(mListActivityContract);
            mListActivityContract.setUsers(users.getValue());
            hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void showProgressBar(){
        try {
            mListActivityContract = checkNotNull(mListActivityContract);
            mListActivityContract.showProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void hideProgressBar(){
        try {
            mListActivityContract = checkNotNull(mListActivityContract);
            mListActivityContract.hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @VisibleForTesting
    public ListActivityContract getmListActivityContract(){
        return mListActivityContract;
    }
}
