package com.rukiasoft.githubfetcher.injection.modules;

import com.rukiasoft.githubfetcher.injection.scopes.CustomScopes;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;
import com.rukiasoft.githubfetcher.ui.observers.ListActivityObserver;
import com.rukiasoft.githubfetcher.ui.presenters.implementations.ListPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 23/7/17.
 */

@Module
public class ListActivityModule {

    private ListActivity activity;

    public ListActivityModule(ListActivity activity) {
        this.activity = activity;
    }

    @Provides
    @CustomScopes.ActivityScope
    ListActivityContracts.RequiredViewOps providesRequiredViewOps() {
        return activity;
    }

    @Provides
    @CustomScopes.ActivityScope
    ListActivityContracts.ProvidedPresenterOps providesPresenterOps(ListPresenterImpl listPresenterImpl) {
        return listPresenterImpl;
    }

    @Provides
    @CustomScopes.ActivityScope
    ListActivityObserver providesObserver(){
        return new ListActivityObserver(activity);
    }
}
