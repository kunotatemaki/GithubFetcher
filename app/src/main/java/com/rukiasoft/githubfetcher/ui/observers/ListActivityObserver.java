package com.rukiasoft.githubfetcher.ui.observers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.rukiasoft.githubfetcher.injection.modules.ListActivityModule;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Roll on 20/7/17.
 */
@Singleton
public class ListActivityObserver implements LifecycleObserver, ListActivityContracts.ObserverOps{

    private static final String TAG = LogHelper.makeLogTag(ListActivityObserver.class);

    private ListActivityContracts.RequiredViewOps mActivity;

    @Inject
    public ListActivityObserver() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void injectViewObserveAnsShowDataInPresenter(){
        Log.d(TAG, "inyecto la vista en el presentador");

        mActivity.getPresenter().setView(mActivity);
        //observo la lista de usuarios
        mActivity.getPresenter().observerListOfUsers(mActivity.getUsersFromModelView());
        //pinto en la pantalla los usuarios
        mActivity.getPresenter().setDataFromNetworkOrCache(mActivity.getUsersFromModelView());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void removeViewFromPresenter(){
        Log.d(TAG, "quito la vista del presentador");
        mActivity.getPresenter().removeView();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void removeActivityReferenceFromObserver(){
        Log.d(TAG, "quito la referencia de la mActivity aquí y en el mActivity.getPresenter() (si no estaba)");
        mActivity.getPresenter().destroy();
        mActivity = null;
    }

    @Override
    public void registerActivity(ListActivity activity) {
        mActivity = activity;
        mActivity.getViewLifecycle().addObserver(this);
    }
}
