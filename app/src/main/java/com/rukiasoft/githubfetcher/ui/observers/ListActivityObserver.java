package com.rukiasoft.githubfetcher.ui.observers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import com.rukiasoft.githubfetcher.ui.activities.ListActivity;
import com.rukiasoft.githubfetcher.utils.LogHelper;

/**
 * Created by Roll on 20/7/17.
 */

public class ListActivityObserver implements LifecycleObserver {

    private static final String TAG = LogHelper.makeLogTag(ListActivityObserver.class);

    ListActivity mActivity;

    public ListActivityObserver(ListActivity activity) {
        mActivity = activity;
        mActivity.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void injectViewInPresenter(){
        Log.d(TAG, "inyecto la vista en el presentador");
        mActivity.getPresenter().setView(mActivity.getmBinding().listContent.progressBar);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void removeViewFromPresenter(){
        Log.d(TAG, "quito la vista del presentador");
        mActivity.getPresenter().removeView();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void removeActivityReferenceFromObserver(){
        Log.d(TAG, "quito la referencia de la mActivity");
        mActivity = null;
    }

}
