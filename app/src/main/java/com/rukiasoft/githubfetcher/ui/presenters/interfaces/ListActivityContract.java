package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import com.rukiasoft.githubfetcher.model.UserBasic;

import java.util.List;

/**
 * Created by Roll on 21/7/17.
 */

public interface ListActivityContract {

    void setUsers(List<UserBasic> users);

    void showProgressBar();

    void hideProgressBar();
}
