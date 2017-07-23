package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.rukiasoft.githubfetcher.model.UserBasic;

import java.util.List;

/**
 * Created by Roll on 23/7/17.
 */

public interface ListActivityContracts {

    interface RequiredViewOps{

        // View operations permitted to Presenter
        Context getAppContext();

        Context getActivityContext();

        LifecycleOwner getLifecycleOwner();

        LifecycleRegistry getViewLifecycle();

        ProvidedPresenterOps getPresenter();

        void setUsersInUI(List<UserBasic> users);

        void showProgressBar();

        void hideProgressBar();

        MutableLiveData<List<UserBasic>> getUsersFromModelView();

        void launchNewActivity(Intent intent);
    }

    interface ProvidedPresenterOps{

        // Presenter operations permitted to View
        void getUsers(MutableLiveData<List<UserBasic>> users);

        void observerListOfUsers(MutableLiveData<List<UserBasic>> users);

        void destroy();

        void setDataFromNetworkOrCache();

        void cardClicked(View view, UserBasic user);

        void setView(RequiredViewOps view);

        void removeView();

    }

}
