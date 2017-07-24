package com.rukiasoft.githubfetcher.ui.activities;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.databinding.ActivityDetailsBinding;
import com.rukiasoft.githubfetcher.injection.modules.DetailsActivityModule;
import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.ui.observers.DetailsActivityObserver;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContracts;
import com.rukiasoft.githubfetcher.ui.viewmodels.DetailsViewModel;
import com.rukiasoft.githubfetcher.utils.GithubFetcherConstants;
import com.rukiasoft.githubfetcher.utils.MyViewUtils;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity implements DetailsActivityContracts.RequiredViewOps {


    @Inject
    DetailsActivityContracts.ProvidedPresenterOps presenter;

    ActivityDetailsBinding mBinding;

    @Inject
    public DetailsActivityObserver mObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inyecto dependencias
        getGApplication()
                .getGithubFetcherComponent()
                //.getDetailsActivityComponent(new DetailsActivityModule(this))
                .inject(this);

        setContentView(R.layout.activity_details);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        setToolbar(mBinding.detailsToolbar);

        //obtengo los datos del intent.
        presenter.setName(getIntent().getStringExtra(GithubFetcherConstants.NICKNAME));

        presenter.observerUser(getUserFromModelView());

        presenter.setDataFromNetworkOrCache(getUserFromModelView());

    }

    @Override
    public GithubFetcherApplication getGApplication() {
        return (GithubFetcherApplication)getApplication();
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
        return getLifecycle();
    }

    @Override
    public void setUserInUI(UserDetailed user) {
        mBinding.setUser(user);
        Glide.with(this)
                .load(user.getAvatarUrl())
                .into(mBinding.imageUser);
    }

    @Override
    public void showProgressBar() {
        MyViewUtils.setViewVisible(mBinding.datos.detailsProgressbar);
    }

    @Override
    public void hideProgressBar() {
        MyViewUtils.setViewInisible(mBinding.datos.detailsProgressbar);
    }

    @Override
    public MutableLiveData<UserDetailed> getUserFromModelView() {
        final DetailsViewModel viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        return viewModel.getUser();
    }

}


