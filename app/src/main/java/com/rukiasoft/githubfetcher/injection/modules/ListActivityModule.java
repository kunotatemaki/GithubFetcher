package com.rukiasoft.githubfetcher.injection.modules;

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

    @Provides
    @Singleton
    ListActivityContracts.ProvidedPresenterOps providesPresenterOps(ListPresenterImpl listPresenterImpl) {
        return listPresenterImpl;
    }

    @Provides
    @Singleton
    ListActivityContracts.ObserverOps providesObserver(ListActivityObserver observer){
        return observer;
    }
}
