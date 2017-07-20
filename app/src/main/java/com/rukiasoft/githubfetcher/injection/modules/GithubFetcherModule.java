package com.rukiasoft.githubfetcher.injection.modules;

import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.network.retrofit.RetrofitNetworkHelperImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 20/7/17.
 */

@Module
public class GithubFetcherModule {

    @Provides static NetworkHelper providesNetworkHelper(){
        return new RetrofitNetworkHelperImpl();
    }
}
