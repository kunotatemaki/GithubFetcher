package com.rukiasoft.githubfetcher.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.databinding.ActivityListBinding;
import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.ui.adapters.UsersAdapter;
import com.rukiasoft.githubfetcher.ui.observers.ListActivityObserver;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListPresenter;
import com.rukiasoft.githubfetcher.ui.viewmodels.ListViewModel;
import com.rukiasoft.githubfetcher.utils.LogHelper;

import java.util.List;

import javax.inject.Inject;

public class ListActivity extends BaseActivity {

    private static final String TAG = LogHelper.makeLogTag(ListActivity.class);

    ListActivityObserver mObserver;

    @Inject
    ListPresenter presenter;

    ActivityListBinding mBinding;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inject dependencieas
        ((GithubFetcherApplication)getApplication()).getGithubFetcherComponent().inject(this);
        setContentView(R.layout.activity_list);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        setSupportActionBar(mBinding.toolbar);

        //register the observer
        mObserver = new ListActivityObserver(this);

        //get the view Model to observe the data
        final ListViewModel listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        listViewModel.getUsers().observe(this, new Observer<List<UserBasic>>() {
            @Override
            public void onChanged(@Nullable List<UserBasic> listOfUsers) {
                Log.d(TAG, "he actualizado los datos");
                setDataInRecycler(listViewModel.getUsers().getValue());
                presenter.hideProgressBar();
            }
        });


        //set the adapter for the recycler view
        mRecyclerView = mBinding.listContent.listUsers;
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (!presenter.downloadUsersIfNecessary(listViewModel.getUsers())) {
            //no ha descargado porque no hací falta, lo pongo en el recycler
            setDataInRecycler(listViewModel.getUsers().getValue());
        }
    }

    private void setDataInRecycler(List<UserBasic> users){
        mAdapter = new UsersAdapter(getApplicationContext(), users);
        mRecyclerView.setAdapter(mAdapter);
    }

    public ListPresenter getPresenter() {
        return presenter;
    }

    public ActivityListBinding getmBinding() {
        return mBinding;
    }
}
