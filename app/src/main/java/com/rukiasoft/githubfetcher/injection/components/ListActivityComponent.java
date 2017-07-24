package com.rukiasoft.githubfetcher.injection.components;

import com.rukiasoft.githubfetcher.injection.modules.ListActivityModule;
import com.rukiasoft.githubfetcher.injection.scopes.CustomScopes;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;
import com.rukiasoft.githubfetcher.ui.observers.ListActivityObserver;

import dagger.Subcomponent;

/**
 * Created by Roll on 23/7/17.
 */

@CustomScopes.ActivityScope
@Subcomponent( modules = ListActivityModule.class )
public interface ListActivityComponent {
    ListActivity inject(ListActivity activity);
    ListActivityObserver inject(ListActivityObserver observer);
}