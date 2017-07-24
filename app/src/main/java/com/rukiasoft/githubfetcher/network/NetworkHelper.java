package com.rukiasoft.githubfetcher.network;

import android.arch.lifecycle.MutableLiveData;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.model.UserDetailed;

import java.util.List;

/**
 * Created by Roll on 20/7/17.
 */

public interface NetworkHelper {

    void getUsers(MutableLiveData<List<UserBasic>> users);

    void getUserInfo(String userName, MutableLiveData<UserDetailed> user);

}
