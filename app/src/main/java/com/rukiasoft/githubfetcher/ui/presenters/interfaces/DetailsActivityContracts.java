package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;

import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.ui.activities.DetailsActivity;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;

/**
 * Created by Roll on 23/7/17.
 */

public interface DetailsActivityContracts {

    interface RequiredViewOps{

        // View operations permitted to Presenter
        void showProgressBar();

        void hideProgressBar();

        Activity getActivityContext();

        LifecycleOwner getLifecycleOwner();

        LifecycleRegistry getViewLifecycle();

        void setUserInUI(UserDetailed user);

        MutableLiveData<UserDetailed> getUserFromModelView();

        DetailsActivityContracts.ProvidedPresenterOps getPresenter();


    }

    interface ProvidedPresenterOps{

        // Presenter operations permitted to View
        void getUser(MutableLiveData<UserDetailed> user);

        void bindView(DetailsActivityContracts.RequiredViewOps view);

        void unbindView();

        void setName(String name);

        // Presenter operations permitted to View

        void observerUser(MutableLiveData<UserDetailed> user);

        void setDataFromNetworkOrCache(MutableLiveData<UserDetailed> user);

    }

    interface ObserverOps{
        void registerActivity(DetailsActivity activity);
    }

}
