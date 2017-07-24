package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.view.View;

import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;

import java.util.List;

/**
 * Created by Roll on 23/7/17.
 */

public interface ListActivityContracts {

    interface RequiredViewOps{

        // View operations permitted to Presenter
        Activity getActivityContext();

        LifecycleOwner getLifecycleOwner();

        LifecycleRegistry getViewLifecycle();

        void setUsersInUI(List<UserBasic> users);

        void showProgressBar();

        void hideProgressBar();

        MutableLiveData<List<UserBasic>> getUsersFromModelView();

        void launchNewActivity(Intent intent);

        ListActivityContracts.ProvidedPresenterOps getPresenter();
    }

    interface ProvidedPresenterOps{

        // Presenter operations permitted to View
        void getUsers(MutableLiveData<List<UserBasic>> users);

        void observerListOfUsers(MutableLiveData<List<UserBasic>> users);

        void setDataFromNetworkOrCache(MutableLiveData<List<UserBasic>> users);

        void cardClicked(View view, UserBasic user);

        void bindView(RequiredViewOps view);

        void unbindView();

    }

    interface ObserverOps{
        void registerActivity(ListActivity activity);
    }

}
