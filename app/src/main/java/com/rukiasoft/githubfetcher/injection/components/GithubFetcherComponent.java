package com.rukiasoft.githubfetcher.injection.components;

import com.rukiasoft.githubfetcher.injection.modules.GithubFetcherModule;
import com.rukiasoft.githubfetcher.ui.activities.GithubListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Roll on 20/7/17.
 */

@Singleton
@Component(modules = {GithubFetcherModule.class})
public interface GithubFetcherComponent {
    void inject(GithubListActivity activity);
}
