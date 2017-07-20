package com.rukiasoft.githubfetcher.network.retrofit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.rukiasoft.githubfetcher.model.UserBasicResponse;
import com.rukiasoft.githubfetcher.model.UserDetailedResponse;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.utils.GithubFetcherConstants;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 * Created by Roll on 20/7/17.
 */

public class RetrofitNetworkHelperImpl implements NetworkHelper {

    private static final String TAG = LogHelper.makeLogTag(RetrofitNetworkHelperImpl.class);
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GithubFetcherConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @Override
    public void getUsers(final MutableLiveData<List<UserBasicResponse>> users) {
        RetrofitEndpoints endpoint = retrofit.create(RetrofitEndpoints.class);

        final Call<List<UserBasicResponse>> call = endpoint.getUsers();
        call.enqueue(new Callback<List<UserBasicResponse>>() {
            @Override
            public void onResponse(Call<List<UserBasicResponse>> call, Response<List<UserBasicResponse>> response) {
                Log.d(TAG, "Response get users ok");
                if(response.body() != null) {
                    users.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<UserBasicResponse>> call, Throwable t) {
                Log.d(TAG, "algo ha ido mal:" + t.getMessage());
            }
        });
    }

    @Override
    public void getUserInfo(final String userName, final MutableLiveData<UserDetailedResponse> user) {
        RetrofitEndpoints endpoints = retrofit.create(RetrofitEndpoints.class);

        final Call<UserDetailedResponse> call = endpoints.getUserInfo(userName);
        call.enqueue(new Callback<UserDetailedResponse>() {
            @Override
            public void onResponse(Call<UserDetailedResponse> call, Response<UserDetailedResponse> response) {
                Log.d(TAG, "Response get user info ok");
                if(response.body() != null) {
                    user.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserDetailedResponse> call, Throwable t) {
                Log.d(TAG, "algo ha ido mal:" + t.getMessage());
            }
        });

    }
}
