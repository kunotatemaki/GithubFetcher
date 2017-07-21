package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import android.arch.lifecycle.MutableLiveData;
import android.view.View;

import com.rukiasoft.githubfetcher.model.UserBasic;

import java.util.List;

/**
 * Created by Roll on 20/7/17.
 */

public interface ListPresenter {

    void getUsers(MutableLiveData<List<UserBasic>> users);

    void showProgressBar();

    void hideProgressBar();

    void setUsers(List<UserBasic> users);

    void setView(View view);

    void removeView();

    boolean downloadUsersIfNecessary(MutableLiveData<List<UserBasic>> users);
}
