package com.rukiasoft.githubfetcher.network;

import android.arch.lifecycle.MutableLiveData;

import com.rukiasoft.githubfetcher.model.UserBasicResponse;
import com.rukiasoft.githubfetcher.model.UserDetailedResponse;

import java.util.List;

import retrofit2.http.Path;

/**
 * Created by Roll on 20/7/17.
 */

public interface NetworkHelper {

    void getUsers(MutableLiveData<List<UserBasicResponse>> users);

    void getUserInfo(@Path("name") String userName, MutableLiveData<UserDetailedResponse> user);

}
