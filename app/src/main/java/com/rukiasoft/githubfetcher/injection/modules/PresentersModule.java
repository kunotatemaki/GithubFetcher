package com.rukiasoft.githubfetcher.injection.modules;

import com.rukiasoft.githubfetcher.ui.presenters.implementations.ListPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListPresenterContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roll on 20/7/17.
 */

@Module
public class PresentersModule {

    @Provides static ListPresenterContract providesListPresenter(ListPresenterImpl listPresenterImpl){
        return listPresenterImpl;
    }

}
