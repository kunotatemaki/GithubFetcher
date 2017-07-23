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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Roll on 20/7/17.
 */

//@CustomScopes.ActivityScope
public class ListPresenterImpl implements ListActivityContracts.ProvidedPresenterOps {

    private static final String TAG = LogHelper.makeLogTag(ListPresenterImpl.class);

    @Inject
    public NetworkHelper mNetworkHelper;

    public ListActivityContracts.RequiredViewOps mView;

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
            mView = checkNotNull(mView);
            users.observe(mView.getLifecycleOwner(), new Observer<List<UserBasic>>() {
                @Override
                public void onChanged(@Nullable List<UserBasic> listOfUsers) {
                    Log.d(TAG, "he actualizado los datos del servidor y los muestro por pantalla");
                    mView.setUsersInUI(listOfUsers);
                }
            });
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }

    }

    @Override
    public void destroy() {
        //me aseguro de borrar la referencia
        this.mView = null;
    }

    @Override
    public void cardClicked(View view, UserBasic user) {
        try {
            mView = checkNotNull(mView);
            Log.d(TAG, "he pulsado: " + user.getLogin());
            Intent intent = new Intent(mView.getActivityContext(), DetailsActivity.class);
            intent.putExtra(GithubFetcherConstants.NICKNAME, user.getLogin());
            mView.launchNewActivity(intent);
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @Override
    public void setView(ListActivityContracts.RequiredViewOps view) {
        mView = view;
    }

    @Override
    public void removeView() {
        destroy();
    }


    @Override
    public void setDataFromNetworkOrCache() {
        try {
            mView = checkNotNull(mView);
            MutableLiveData<List<UserBasic>> users = mView.getUsersFromModelView();
            if(!downloadUsersIfNecessary(users)){
                //no ha descargado porque no hacía falta, lo pongo en el recycler
                refreshUI(users);
            }
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
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

    /***
     * refresh data in view
     * @param users list of users to set in view
     */
    private void refreshUI(MutableLiveData<List<UserBasic>> users){
        try {
            mView = checkNotNull(mView);
            mView.setUsersInUI(users.getValue());
            hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    /***
     * Show progress bar in view
     */
    private void showProgressBar(){
        try {
            mView = checkNotNull(mView);
            mView.showProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    /***
     * Show progress bar in view
     */
    private void hideProgressBar(){
        try {
            mView = checkNotNull(mView);
            mView.hideProgressBar();
        }catch (NullPointerException e){
            Log.e(TAG, "No hay referencia de la actividad");
        }
    }

    @VisibleForTesting
    public ListActivityContracts.RequiredViewOps getmListActivityContract(){
        return mView;
    }
}
