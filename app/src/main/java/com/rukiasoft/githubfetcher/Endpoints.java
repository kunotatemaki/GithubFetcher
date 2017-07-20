package com.rukiasoft.githubfetcher;

import android.arch.lifecycle.LiveData;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.model.UserDetailed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Roll on 20/7/17.
 */

public interface Endpoints {


    @GET("users/")
    Call<LiveData<List<UserBasic>>> getUsers();

    @GET("users/{name}")
    Call<LiveData<UserDetailed>> getUserInfo(@Path("name") String userName);
}
