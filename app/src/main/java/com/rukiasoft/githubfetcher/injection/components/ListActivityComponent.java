package com.rukiasoft.githubfetcher.injection.components;

import com.rukiasoft.githubfetcher.injection.modules.ListActivityModule;
import com.rukiasoft.githubfetcher.injection.scope.CustomScopes;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;

import dagger.Subcomponent;

/**
 * Created by Roll on 24/7/17.
 */

@CustomScopes.ListActivityScope
@Subcomponent(modules = {ListActivityModule.class})
public interface ListActivityComponent {
    void inject(ListActivity activity);
}
