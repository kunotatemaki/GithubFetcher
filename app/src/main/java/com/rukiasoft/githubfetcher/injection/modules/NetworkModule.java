package com.rukiasoft.githubfetcher.injection.modules;

import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.network.retrofit.RetrofitNetworkHelperImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 20/7/17.
 */

@Module
public class NetworkModule {

    @Provides @Singleton static NetworkHelper providesNetworkHelper(RetrofitNetworkHelperImpl networkHelper){
        return networkHelper;
    }

}
