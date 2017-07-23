package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.rukiasoft.githubfetcher.model.UserDetailed;

/**
 * Created by Roll on 23/7/17.
 */

public interface DetailsActivityContracts {

    interface RequiredViewOps{

        // View operations permitted to Presenter
        Context getAppContext();

        Context getActivityContext();

        void setUserInfoInUI(UserDetailed user);

        void showProgressBar();

        void hideProgressBar();
    }

    interface ProvidedPresenterOps{

        // Presenter operations permitted to View
        void observeUser(MutableLiveData<UserDetailed> user);

        void destroy();

        void getUserInfo(MutableLiveData<UserDetailed> user);

        void setName(String name);

    }

    interface RequiredPresenterOps{

    }

    interface ProvidedModelOps{

        void getUserInfo(String userName, MutableLiveData<UserDetailed> user);

    }

}
