package com.rukiasoft.githubfetcher.injection.modules;

import com.rukiasoft.githubfetcher.ui.activities.DetailsActivity;
import com.rukiasoft.githubfetcher.ui.observers.DetailsActivityObserver;
import com.rukiasoft.githubfetcher.ui.presenters.implementations.DetailsPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContracts;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 24/7/17.
 */

@Module
public class DetailsActivityModule {


    @Provides
    @Singleton
    DetailsActivityContracts.ProvidedPresenterOps providesPresenter(DetailsPresenterImpl presenterImpl){
        return presenterImpl;
    }

    @Provides
    @Singleton
    DetailsActivityObserver providesObserver(){
        return new DetailsActivityObserver();
    }

}
