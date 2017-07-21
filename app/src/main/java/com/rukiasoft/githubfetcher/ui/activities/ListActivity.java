package com.rukiasoft.githubfetcher.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.databinding.ActivityListBinding;
import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.ui.adapters.UsersAdapter;
import com.rukiasoft.githubfetcher.ui.observers.ListActivityObserver;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListPresenterContract;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContract;
import com.rukiasoft.githubfetcher.ui.viewmodels.ListViewModel;
import com.rukiasoft.githubfetcher.utils.LogHelper;
import com.rukiasoft.githubfetcher.utils.MyViewUtils;

import java.util.List;

import javax.inject.Inject;

public class ListActivity extends BaseActivity implements UsersAdapter.OnCardClickListener, ListActivityContract {

    private static final String TAG = LogHelper.makeLogTag(ListActivity.class);

    ListActivityObserver mObserver;

    @Inject
    ListPresenterContract presenter;

    ActivityListBinding mBinding;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inject dependencieas
        ((GithubFetcherApplication)getApplication()).getGithubFetcherComponent().inject(this);
        setContentView(R.layout.activity_list);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        setToolbar(mBinding.toolbar);
        MyViewUtils.setViewVisible(mBinding.toolbar);
        MyViewUtils.setViewVisible(null);

        //register the observer
        mObserver = new ListActivityObserver(this);

        //get the view Model to observe the data
        final ListViewModel listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        listViewModel.getUsers().observe(this, new Observer<List<UserBasic>>() {
            @Override
            public void onChanged(@Nullable List<UserBasic> listOfUsers) {
                Log.d(TAG, "he actualizado los datos del servidor y los muestro por pantalla");
                presenter.setData(listViewModel.getUsers());
            }
        });


        //set the adapter for the recycler view
        mRecyclerView = mBinding.listContent.listUsers;
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        presenter.setData(listViewModel.getUsers());

    }


    public ListPresenterContract getPresenter() {
        return presenter;
    }

    public ActivityListBinding getmBinding() {
        return mBinding;
    }

    @Override
    public void onCardClick(View view, UserBasic user) {
        Log.d(TAG, "he pulsado: " + user.getLogin());
    }

    @Override
    public void setUsers(List<UserBasic> users) {
        UsersAdapter mAdapter = new UsersAdapter(getApplicationContext(), users);
        mAdapter.setOnCardClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showProgressBar() {
        MyViewUtils.setViewVisible(mBinding.listContent.progressBar);
    }

    @Override
    public void hideProgressBar() {
        MyViewUtils.setViewInisible(mBinding.listContent.progressBar);
    }
}
