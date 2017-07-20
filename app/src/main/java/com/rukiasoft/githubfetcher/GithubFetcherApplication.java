package com.rukiasoft.githubfetcher;

import android.app.Application;

import com.rukiasoft.githubfetcher.injection.components.DaggerGithubFetcherComponent;
import com.rukiasoft.githubfetcher.injection.components.GithubFetcherComponent;
import com.rukiasoft.githubfetcher.injection.modules.GithubFetcherModule;

/**
 * Created by Roll on 20/7/17.
 */

public class GithubFetcherApplication extends Application {

    GithubFetcherComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerGithubFetcherComponent.builder()
                .githubFetcherModule(new GithubFetcherModule())
                .build();

    }

    public GithubFetcherComponent getGithubFetcherComponent(){
        return mComponent;
    }
}
