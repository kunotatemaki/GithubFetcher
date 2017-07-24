package com.rukiasoft.githubfetcher.ui.observers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.rukiasoft.githubfetcher.injection.scope.CustomScopes;
import com.rukiasoft.githubfetcher.ui.activities.DetailsActivity;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContracts;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import javax.inject.Inject;

/**
 * Created by Roll on 20/7/17.
 */

@CustomScopes.DetailsActivityScope
public class DetailsActivityObserver implements LifecycleObserver, DetailsActivityContracts.ObserverOps {

    private static final String TAG = LogHelper.makeLogTag(DetailsActivityObserver.class);

    DetailsActivityContracts.RequiredViewOps mActivity;

    @Inject
    public DetailsActivityObserver() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void injectViewInPresenter(){
        Log.d(TAG, "inyecto la vista en el presentador");
        mActivity.getPresenter().bindView(mActivity);
        //observo la lista de usuarios
        mActivity.getPresenter().observerUser(mActivity.getUserFromModelView());
        //pinto en la pantalla los usuarios
        mActivity.getPresenter().setDataFromNetworkOrCache(mActivity.getUserFromModelView());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void removeActivityReferenceFromObserver(){
        Log.d(TAG, "quito la referencia de la mActivity");
        mActivity.getPresenter().unbindView();
        mActivity = null;
    }

    @Override
    public void registerActivity(DetailsActivity activity) {
        mActivity = activity;
        mActivity.getViewLifecycle().addObserver(this);
    }
}
