package com.rukiasoft.githubfetcher.ui.presenters.interfaces;

import android.arch.lifecycle.MutableLiveData;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.ui.activities.DetailsActivity;

import java.util.List;

/**
 * Created by Roll on 20/7/17.
 */

public interface DetailsPresenterContract {

    void getUser(MutableLiveData<UserDetailed> user);

    void setView(DetailsActivityContract view);

    void removeView();

    void setData(MutableLiveData<UserDetailed> user);

    void setName(String name);
}
