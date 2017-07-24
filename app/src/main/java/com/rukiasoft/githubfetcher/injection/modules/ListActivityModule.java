package com.rukiasoft.githubfetcher.injection.modules;

import com.rukiasoft.githubfetcher.injection.scope.CustomScopes;
import com.rukiasoft.githubfetcher.ui.observers.ListActivityObserver;
import com.rukiasoft.githubfetcher.ui.presenters.implementations.ListPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 24/7/17.
 */

@Module
public class ListActivityModule {

    @Provides
    @CustomScopes.ListActivityScope
    static ListActivityContracts.ProvidedPresenterOps providesListPresenter(ListPresenterImpl listPresenterImpl){
        return listPresenterImpl;
    }

    @Provides
    @CustomScopes.ListActivityScope
    static ListActivityContracts.ObserverOps providesObserver(ListActivityObserver observer){
        return observer;
    }

}
