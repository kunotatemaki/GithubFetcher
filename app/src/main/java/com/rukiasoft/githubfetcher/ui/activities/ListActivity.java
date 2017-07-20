package com.rukiasoft.githubfetcher.ui.activities;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.model.UserBasicResponse;
import com.rukiasoft.githubfetcher.model.UserDetailedResponse;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.network.retrofit.RetrofitNetworkHelperImpl;
import com.rukiasoft.githubfetcher.ui.observers.ListActivityObserver;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListPresenter;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListActivity extends BaseActivity {

    private static final String TAG = LogHelper.makeLogTag(ListActivity.class);

    ListActivityObserver mObserver;

    @Inject
    ListPresenter presenter;

    View vistaDePrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inject dependencieas
        ((GithubFetcherApplication)getApplication()).getGithubFetcherComponent().inject(this);
        setContentView(R.layout.activity_github_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vistaDePrueba = findViewById(R.id.progressBar);

        //register the observer
        mObserver = new ListActivityObserver(this);

        //get the view Model to observe the data



    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        MutableLiveData<UserDetailedResponse> user = new MutableLiveData<>();
        user.observe(this, new Observer<UserDetailedResponse>() {
            @Override
            public void onChanged(@Nullable UserDetailedResponse userDetailedResponse) {
                Log.d(TAG, "he actualizado los datos");


                presenter.hideProgressBar();
            }
        });

        user.setValue(new UserDetailedResponse());
        Log.d(TAG, "voy a pedir iformación");
        presenter.showProgressBar();
        presenter.getUserInfo("kunotatemaki", user);
    }

    public ListPresenter getPresenter() {
        return presenter;
    }

    public View getVistaDePrueba() {
        return vistaDePrueba;
    }
}
