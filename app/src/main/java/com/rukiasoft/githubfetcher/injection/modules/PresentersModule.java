package com.rukiasoft.githubfetcher.injection.modules;

import com.rukiasoft.githubfetcher.ui.presenters.implementations.DetailsPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsPresenterContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 20/7/17.
 */

@Module
public class PresentersModule {



    @Provides static DetailsPresenterContract providesDetailsPresenter(DetailsPresenterImpl detailsPresenter){
        return detailsPresenter;
    }
}
