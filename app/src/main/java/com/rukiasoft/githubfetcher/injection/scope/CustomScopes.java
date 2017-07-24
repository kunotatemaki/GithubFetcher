package com.rukiasoft.githubfetcher.injection.scope;

import android.support.annotation.RestrictTo;

import javax.inject.Scope;


/**
 * Created by Roll on 24/7/17.
 */

public interface CustomScopes {

    @Scope
    @interface ListActivityScope {

    }

    @Scope
    @interface DetailsActivityScope {

    }
}
