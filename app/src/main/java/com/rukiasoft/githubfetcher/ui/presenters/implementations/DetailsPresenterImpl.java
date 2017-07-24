package com.rukiasoft.githubfetcher.ui.presenters.implementations;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContracts;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Roll on 21/7/17.
 */

public class DetailsPresenterImpl implements DetailsActivityContracts.ProvidedPresenterOps {

    private final static String TAG = LogHelper.makeLogTag(DetailsPresenterImpl.class);

    private String name;

    private DetailsActivityContracts.RequiredViewOps mView;

    @Inject
    NetworkHelper mNetworkHelper;

    @Inject
    public DetailsPresenterImpl() {
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
    public void observerUser(MutableLiveData<UserDetailed> user) {
        try {
            mView = checkNotNull(mView);
            user.observe(mView.getLifecycleOwner(), new Observer<UserDetailed>() {
                @Override
                public void onChanged(@Nullable UserDetailed user) {
                    Log.d(TAG, "he actualizado los datos del servidor y los muestro por pantalla");
                    mView.setUserInUI(user);
                }
            });
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void setDataFromNetworkOrCache(MutableLiveData<UserDetailed> user) {
        try {
            mView = checkNotNull(mView);
            if(!downloadUsersIfNecessary(user)){
                //no ha descargado porque no hacía falta, lo pongo en el recycler
                refreshUI(user);
            }
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @Override
    public void setView(DetailsActivityContracts.RequiredViewOps view) {
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
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
            mView = checkNotNull(mView);
            mView.setUserInUI(user.getValue());
            hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void showProgressBar(){
        try {
            mView = checkNotNull(mView);
            mView.showProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void hideProgressBar(){
        try {
            mView = checkNotNull(mView);
            mView.hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @VisibleForTesting
    public DetailsActivityContracts.RequiredViewOps getmDetailsActivityContract() {
        return mView;
    }
}
