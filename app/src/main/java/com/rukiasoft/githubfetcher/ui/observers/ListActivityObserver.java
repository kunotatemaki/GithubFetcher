package com.rukiasoft.githubfetcher.ui.observers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.rukiasoft.githubfetcher.injection.scope.CustomScopes;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import javax.inject.Inject;

/**
 * Created by Roll on 20/7/17.
 */

@CustomScopes.ListActivityScope
public class ListActivityObserver implements LifecycleObserver, ListActivityContracts.ObserverOps {

    private static final String TAG = LogHelper.makeLogTag(ListActivityObserver.class);

    private ListActivity mActivity;

    @Inject
    public ListActivityObserver() {

    }

    @Override
    public void registerActivity(ListActivity activity){
        mActivity = activity;
        mActivity.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void injectViewInPresenter(){
        Log.d(TAG, "inyecto la vista en el presentador");
        mActivity.getPresenter().bindView(mActivity);
        //observo la lista de usuarios
        mActivity.getPresenter().observerListOfUsers(mActivity.getUsersFromModelView());
        //pinto en la pantalla los usuarios
        mActivity.getPresenter().setDataFromNetworkOrCache(mActivity.getUsersFromModelView());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void removeActivityReferenceFromObserver(){
        Log.d(TAG, "quito la referencia de la mActivity");
        mActivity.getPresenter().unbindView();
        mActivity = null;
    }

}
