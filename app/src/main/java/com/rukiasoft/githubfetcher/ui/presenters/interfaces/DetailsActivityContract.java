package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.model.UserDetailed;

import java.util.List;

/**
 * Created by Roll on 21/7/17.
 */

public interface DetailsActivityContract {

    void setUser(UserDetailed user);

    void showProgressBar();

    void hideProgressBar();
}
