package com.rukiasoft.githubfetcher.network.retrofit;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.network.model.UserBasicResponse;
import com.rukiasoft.githubfetcher.network.model.UserDetailedResponse;
import com.rukiasoft.githubfetcher.utils.GithubFetcherConstants;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Roll on 20/7/17.
 */

public class RetrofitNetworkHelperImpl implements NetworkHelper {

    private static final String TAG = LogHelper.makeLogTag(RetrofitNetworkHelperImpl.class);
    private final Retrofit retrofit;

    @Inject
    public RetrofitNetworkHelperImpl() {
        retrofit = new Retrofit.Builder()
                .baseUrl(GithubFetcherConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getUsers(final MutableLiveData<List<UserBasic>> users) {
        RetrofitEndpoints endpoint = retrofit.create(RetrofitEndpoints.class);

        final Call<List<UserBasicResponse>> call = endpoint.getUsers();
        call.enqueue(new Callback<List<UserBasicResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserBasicResponse>> call, @NonNull Response<List<UserBasicResponse>> response) {
                Log.d(TAG, "Response get users ok");
                if(response.body() != null) {
                    List<UserBasicResponse> listResponse = response.body();
                    if(listResponse == null){
                        return ;
                    }
                    List<UserBasic> finalList = new ArrayList<>();
                    for(UserBasicResponse userResponse : listResponse){
                        UserBasic user = new UserBasic(userResponse);
                        finalList.add(user);
                    }
                    users.setValue(finalList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserBasicResponse>> call, @NonNull Throwable t) {
                Log.d(TAG, "algo ha ido mal:" + t.getMessage());
            }
        });
    }

    @Override
    public void getUserInfo(final String userName, final MutableLiveData<UserDetailed> user) {
        RetrofitEndpoints endpoints = retrofit.create(RetrofitEndpoints.class);

        final Call<UserDetailedResponse> call = endpoints.getUserInfo(userName);
        call.enqueue(new Callback<UserDetailedResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserDetailedResponse> call, @NonNull Response<UserDetailedResponse> response) {
                Log.d(TAG, "Response get user info ok");
                if(response.body() != null) {
                    UserDetailedResponse userResponse = response.body();
                    UserDetailed finalUser = new UserDetailed()
                            .loadData(userResponse);
                    user.setValue(finalUser);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDetailedResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "algo ha ido mal:" + t.getMessage());
            }
        });

    }
}
