package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.model.UserDetailed;

import java.util.List;

/**
 * Created by Roll on 23/7/17.
 */

public interface DetailsActivityContracts {

    interface RequiredViewOps{

        // View operations permitted to Presenter
        GithubFetcherApplication getGApplication();

        Activity getActivityContext();

        LifecycleOwner getLifecycleOwner();

        LifecycleRegistry getViewLifecycle();

        void setUserInUI(UserDetailed user);

        void showProgressBar();

        void hideProgressBar();

        MutableLiveData<UserDetailed> getUserFromModelView();


    }

    interface ProvidedPresenterOps{

        // Presenter operations permitted to View
        void getUser(MutableLiveData<UserDetailed> user);

        void observerUser(MutableLiveData<UserDetailed> user);

        void destroy();

        void setDataFromNetworkOrCache(MutableLiveData<UserDetailed> user);

        void setView(DetailsActivityContracts.RequiredViewOps view);

        void removeView();

        void setName(String name);

    }

}
