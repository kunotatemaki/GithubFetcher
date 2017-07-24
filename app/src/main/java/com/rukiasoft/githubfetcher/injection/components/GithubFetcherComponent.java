package com.rukiasoft.githubfetcher.injection.components;

import com.rukiasoft.githubfetcher.injection.modules.AppModule;
import com.rukiasoft.githubfetcher.injection.modules.DetailsActivityModule;
import com.rukiasoft.githubfetcher.injection.modules.ListActivityModule;
import com.rukiasoft.githubfetcher.injection.modules.NetworkModule;
import com.rukiasoft.githubfetcher.ui.activities.DetailsActivity;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Roll on 20/7/17.
 */

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class, ListActivityModule.class, DetailsActivityModule.class})
public interface GithubFetcherComponent {
    //ListActivityComponent getListActivityComponent(ListActivityModule module);
    ListActivity inject(ListActivity activity);
    DetailsActivity inject(DetailsActivity activity);
}
