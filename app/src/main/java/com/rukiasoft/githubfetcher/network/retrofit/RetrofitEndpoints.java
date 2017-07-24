package com.rukiasoft.githubfetcher.network.retrofit;

import com.rukiasoft.githubfetcher.network.model.UserBasicResponse;
import com.rukiasoft.githubfetcher.network.model.UserDetailedResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Roll on 20/7/17.
 */

public interface RetrofitEndpoints {


    @GET("users")
    Call<List<UserBasicResponse>> getUsers();

    @GET("users/{name}")
    Call<UserDetailedResponse> getUserInfo(@Path("name") String userName);
}
