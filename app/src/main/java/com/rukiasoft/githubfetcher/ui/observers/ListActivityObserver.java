package com.rukiasoft.githubfetcher.ui.observers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.rukiasoft.githubfetcher.injection.modules.ListActivityModule;
import com.rukiasoft.githubfetcher.ui.activities.ListActivity;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import javax.inject.Inject;

/**
 * Created by Roll on 20/7/17.
 */

public class ListActivityObserver implements LifecycleObserver {

    private static final String TAG = LogHelper.makeLogTag(ListActivityObserver.class);

    private ListActivityContracts.RequiredViewOps mActivity;

    @Inject
    ListActivityContracts.ProvidedPresenterOps presenter;

    public ListActivityObserver(ListActivityContracts.RequiredViewOps activity) {

        mActivity = activity;
        //inyecto dependencias aquí
        activity.getGApplication()
                .getGithubFetcherComponent()
                .getListActivityComponent(new ListActivityModule((ListActivity)mActivity))
                .inject(this);
        //añado esto como observer la activity
        mActivity.getViewLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void injectViewObserveAnsShowDataInPresenter(){
        Log.d(TAG, "inyecto la vista en el presentador");
        presenter.setView(mActivity);
        //observo la lista de usuarios
        presenter.observerListOfUsers(mActivity.getUsersFromModelView());
        //pinto en la pantalla los usuarios
        presenter.setDataFromNetworkOrCache(mActivity.getUsersFromModelView());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void removeViewFromPresenter(){
        Log.d(TAG, "quito la vista del presentador");
        presenter.removeView();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void removeActivityReferenceFromObserver(){
        Log.d(TAG, "quito la referencia de la mActivity aquí y en el presenter (si no estaba)");
        presenter.destroy();
        mActivity = null;
    }

}
