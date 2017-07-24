package com.rukiasoft.githubfetcher.ui.activities;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.databinding.ActivityListBinding;
import com.rukiasoft.githubfetcher.injection.modules.ListActivityModule;
import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.ui.adapters.UsersAdapter;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;
import com.rukiasoft.githubfetcher.ui.viewmodels.ListViewModel;
import com.rukiasoft.githubfetcher.utils.LogHelper;
import com.rukiasoft.githubfetcher.utils.MyViewUtils;

import java.util.List;

import javax.inject.Inject;

public class ListActivity extends BaseActivity implements UsersAdapter.OnCardClickListener, ListActivityContracts.RequiredViewOps {

    private static final String TAG = LogHelper.makeLogTag(ListActivity.class);

    @Inject
    ListActivityContracts.ObserverOps mObserver;

    @Inject
    ListActivityContracts.ProvidedPresenterOps presenter;

    ActivityListBinding mBinding;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inject dependencieas
        ((GithubFetcherApplication)getApplication())
                .getGithubFetcherComponent()
                .getListActivityComponent(new ListActivityModule())
                .inject(this);
        setContentView(R.layout.activity_list);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        setToolbar(mBinding.listToolbar);

        //register the observer
        mObserver.registerActivity(this);


        //set the adapter for the recycler view
        mRecyclerView = mBinding.listContent.listUsers;
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



    }


    public ListActivityContracts.ProvidedPresenterOps getPresenter() {
        return presenter;
    }

    public ActivityListBinding getmBinding() {
        return mBinding;
    }

    @Override
    public void onCardClick(View view, UserBasic user) {
        presenter.cardClicked(view, user);
    }

    @Override
    public void setUsersInUI(List<UserBasic> users) {
        UsersAdapter mAdapter = new UsersAdapter(getApplicationContext(), users);
        mAdapter.setOnCardClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public Activity getActivityContext() {
        return this;
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    @Override
    public LifecycleRegistry getViewLifecycle() {
        return this.getLifecycle();
    }

    @Override
    public void showProgressBar() {
        MyViewUtils.setViewVisible(mBinding.listContent.listProgressbar);
    }

    @Override
    public void hideProgressBar() {
        MyViewUtils.setViewInisible(mBinding.listContent.listProgressbar);
    }

    @Override
    public MutableLiveData<List<UserBasic>> getUsersFromModelView() {
        return ViewModelProviders.of(this).get(ListViewModel.class).getUsers();
    }

    @Override
    public void launchNewActivity(Intent intent) {
        startActivity(intent);
    }
}
