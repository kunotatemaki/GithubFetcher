package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import android.arch.lifecycle.MutableLiveData;

import com.rukiasoft.githubfetcher.model.UserBasic;

import java.util.List;

/**
 * Created by Roll on 20/7/17.
 */

public interface ListPresenterContract {

    void getUsers(MutableLiveData<List<UserBasic>> users);

    void setView(ListActivityContract view);

    void removeView();

    void setData(MutableLiveData<List<UserBasic>> users);
}
