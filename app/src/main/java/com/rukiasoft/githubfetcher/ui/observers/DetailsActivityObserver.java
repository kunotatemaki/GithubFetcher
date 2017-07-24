package com.rukiasoft.githubfetcher.ui.observers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.rukiasoft.githubfetcher.injection.modules.DetailsActivityModule;
import com.rukiasoft.githubfetcher.ui.activities.DetailsActivity;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContracts;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import javax.inject.Inject;

/**
 * Created by Roll on 20/7/17.
 */

public class DetailsActivityObserver implements LifecycleObserver {

    private static final String TAG = LogHelper.makeLogTag(DetailsActivityObserver.class);

    DetailsActivityContracts.RequiredViewOps mActivity;

    @Inject
    DetailsActivityContracts.ProvidedPresenterOps presenter;

    public DetailsActivityObserver() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void injectViewInPresenter(){
        Log.d(TAG, "inyecto la vista en el presentador");
        presenter.setView(mActivity);
        //observo el usuario
        presenter.observerUser(mActivity.getUserFromModelView());
        //pinto en la pantalla el usuario
        presenter.setDataFromNetworkOrCache(mActivity.getUserFromModelView());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void removeViewFromPresenter(){
        Log.d(TAG, "quito la vista del presentador");
        presenter.removeView();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void removeActivityReferenceFromObserver(){
        Log.d(TAG, "quito la referencia de la mActivity");
        presenter.destroy();
        mActivity = null;
    }

}
