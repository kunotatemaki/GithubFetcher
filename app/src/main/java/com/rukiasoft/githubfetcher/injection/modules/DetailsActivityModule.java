package com.rukiasoft.githubfetcher.injection.modules;

import com.rukiasoft.githubfetcher.injection.scope.CustomScopes;
import com.rukiasoft.githubfetcher.ui.observers.DetailsActivityObserver;
import com.rukiasoft.githubfetcher.ui.presenters.implementations.DetailsPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContracts;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 24/7/17.
 */

@Module
public class DetailsActivityModule {

    @Provides
    @CustomScopes.DetailsActivityScope
    static DetailsActivityContracts.ProvidedPresenterOps providesListPresenter(DetailsPresenterImpl detailsPresenter){
        return detailsPresenter;
    }

    @Provides
    @CustomScopes.DetailsActivityScope
    static DetailsActivityContracts.ObserverOps providesObserver(DetailsActivityObserver observer){
        return observer;
    }
}
