package com.rukiasoft.githubfetcher.injection.components;

import com.rukiasoft.githubfetcher.injection.modules.DetailsActivityModule;
import com.rukiasoft.githubfetcher.injection.modules.ListActivityModule;
import com.rukiasoft.githubfetcher.injection.scope.CustomScopes;
import com.rukiasoft.githubfetcher.ui.activities.DetailsActivity;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;

import dagger.Subcomponent;

/**
 * Created by Roll on 24/7/17.
 */

@CustomScopes.DetailsActivityScope
@Subcomponent(modules = {DetailsActivityModule.class})
public interface DetailsActivityComponent {
    void inject(DetailsActivity activity);
}
