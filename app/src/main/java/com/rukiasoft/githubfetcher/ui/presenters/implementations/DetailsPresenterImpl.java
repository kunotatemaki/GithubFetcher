package com.rukiasoft.githubfetcher.ui.presenters.implementations;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.rukiasoft.githubfetcher.injection.scope.CustomScopes;
import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContracts;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Roll on 21/7/17.
 */

@CustomScopes.DetailsActivityScope
public class DetailsPresenterImpl implements DetailsActivityContracts.ProvidedPresenterOps {

    private final static String TAG = LogHelper.makeLogTag(DetailsPresenterImpl.class);

    private String name;
    @Inject
    NetworkHelper mNetworkHelper;

    private DetailsActivityContracts.RequiredViewOps mActivity;


    @Inject
    public DetailsPresenterImpl() {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void observerUser(MutableLiveData<UserDetailed> user) {
        try {
            mActivity = checkNotNull(mActivity);
            user.observe(mActivity.getLifecycleOwner(), new Observer<UserDetailed>() {
                @Override
                public void onChanged(@Nullable UserDetailed user) {
                    Log.d(TAG, "he actualizado los datos del servidor y los muestro por pantalla");
                    refreshUI(user);
                }
            });
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @Override
    public void setDataFromNetworkOrCache(MutableLiveData<UserDetailed> user) {
        try {
            mActivity = checkNotNull(mActivity);
            if(!downloadUsersIfNecessary(user)){
                //no ha descargado porque no hacía falta, lo pongo en el recycler
                refreshUI(user.getValue());
            }
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }


    @Override
    public void getUser(MutableLiveData<UserDetailed> user) {
        mNetworkHelper.getUserInfo(name, user);
    }

    @Override
    public void bindView(DetailsActivityContracts.RequiredViewOps view) {
        mActivity = view;
    }

    @Override
    public void unbindView() {
        mActivity = null;
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

    private void showProgressBar(){
        try {
            mActivity = checkNotNull(mActivity);
            mActivity.showProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void hideProgressBar(){
        try {
            mActivity = checkNotNull(mActivity);
            mActivity.hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    private void refreshUI(UserDetailed user){
        try {
            mActivity = checkNotNull(mActivity);
            mActivity.setUserInUI(user);
            hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @VisibleForTesting
    public DetailsActivityContracts.RequiredViewOps getActivityAsociatedToPresenter(){
        return mActivity;
    }

}
