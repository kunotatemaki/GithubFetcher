package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import android.arch.lifecycle.MutableLiveData;
import android.view.View;

import com.rukiasoft.githubfetcher.model.UserBasicResponse;
import com.rukiasoft.githubfetcher.model.UserDetailedResponse;
import com.rukiasoft.githubfetcher.network.NetworkHelper;

import java.util.List;

/**
 * Created by Roll on 20/7/17.
 */

public interface ListPresenter {

    void getUsers(MutableLiveData<List<UserBasicResponse>> users);

    void getUserInfo(String userName, MutableLiveData<UserDetailedResponse> user);

    void showProgressBar();

    void hideProgressBar();

    void setUsers(List<UserBasicResponse> users);

    void setView(View view);

    void removeView();

    void downloadUsersIfNecessary(MutableLiveData<List<UserBasicResponse>> users);
}
