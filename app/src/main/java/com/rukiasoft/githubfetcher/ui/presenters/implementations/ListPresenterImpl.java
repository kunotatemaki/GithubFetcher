package com.rukiasoft.githubfetcher.ui.presenters.implementations;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.View;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.activities.DetailsActivity;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;
import com.rukiasoft.githubfetcher.utils.GithubFetcherConstants;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Roll on 20/7/17.
 */

@Singleton
public class ListPresenterImpl implements ListActivityContracts.ProvidedPresenterOps {

    private static final String TAG = LogHelper.makeLogTag(ListPresenterImpl.class);

    @Inject
    NetworkHelper mNetworkHelper;

    private ListActivityContracts.RequiredViewOps mActivity;

    @Inject
    public ListPresenterImpl() {

    }

    @Override
    public void getUsers(MutableLiveData<List<UserBasic>> users) {
        mNetworkHelper.getUsers(users);
    }

    @Override
    public void observerListOfUsers(MutableLiveData<List<UserBasic>> users) {
        try {
            mActivity = checkNotNull(mActivity);
            users.observe(mActivity.getLifecycleOwner(), new Observer<List<UserBasic>>() {
                @Override
                public void onChanged(@Nullable List<UserBasic> listOfUsers) {
                    Log.d(TAG, "he actualizado los datos del servidor y los muestro por pantalla");
                    refreshUI(listOfUsers);
                }
            });
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @Override
    public void unbindView() {
        mActivity = null;
    }

    @Override
    public void setDataFromNetworkOrCache(MutableLiveData<List<UserBasic>> users) {
        try {
            mActivity = checkNotNull(mActivity);
            if(!downloadUsersIfNecessary(users)){
                //no ha descargado porque no hacía falta, lo pongo en el recycler
                refreshUI(users.getValue());
            }
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @Override
    public void cardClicked(View view, UserBasic user) {
        try {
            mActivity = checkNotNull(mActivity);
            Log.d(TAG, "he pulsado: " + user.getLogin());
            Intent intent = new Intent(mActivity.getActivityContext(), DetailsActivity.class);
            intent.putExtra(GithubFetcherConstants.NICKNAME, user.getLogin());
            mActivity.launchNewActivity(intent);
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }
    
    @Override
    public void bindView(ListActivityContracts.RequiredViewOps view){
        mActivity = view;
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

    private void refreshUI(List<UserBasic> users){
        try {
            mActivity = checkNotNull(mActivity);
            mActivity.setUsersInUI(users);
            hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
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

    @VisibleForTesting
    public ListActivityContracts.RequiredViewOps getActivityAsociatedToPresenter(){
        return mActivity;
    }
}
