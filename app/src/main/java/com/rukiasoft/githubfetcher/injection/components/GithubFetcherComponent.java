package com.rukiasoft.githubfetcher.injection.components;

import com.rukiasoft.githubfetcher.injection.modules.DetailsActivityModule;
import com.rukiasoft.githubfetcher.injection.modules.ListActivityModule;
import com.rukiasoft.githubfetcher.injection.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Roll on 20/7/17.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface GithubFetcherComponent {
    ListActivityComponent getListActivityComponent(ListActivityModule module);
    DetailsActivityComponent getDetailsActivityComponent(DetailsActivityModule module);
}
