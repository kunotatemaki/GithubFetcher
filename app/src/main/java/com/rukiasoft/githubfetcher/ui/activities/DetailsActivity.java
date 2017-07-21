package com.rukiasoft.githubfetcher.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.databinding.ActivityDetailsBinding;
import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.ui.observers.DetailsActivityObserver;
import com.rukiasoft.githubfetcher.ui.observers.ListActivityObserver;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContract;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsPresenterContract;
import com.rukiasoft.githubfetcher.ui.viewmodels.DetailsViewModel;
import com.rukiasoft.githubfetcher.utils.GithubFetcherConstants;
import com.rukiasoft.githubfetcher.utils.MyViewUtils;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity implements DetailsActivityContract {


    @Inject
    DetailsPresenterContract presenter;

    ActivityDetailsBinding mBinding;
    private DetailsActivityObserver mObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inyecto dependencias
        ((GithubFetcherApplication)getApplication()).getGithubFetcherComponent().inject(this);
        setContentView(R.layout.activity_details);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        setToolbar(mBinding.detailsToolbar);

        //register the observer
        mObserver = new DetailsActivityObserver(this);

        //obtengo los datos del intent.
        presenter.setName(getIntent().getStringExtra(GithubFetcherConstants.NICKNAME));

        final DetailsViewModel viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        viewModel.getUser().observe(this, new Observer<UserDetailed>() {
            @Override
            public void onChanged(@Nullable UserDetailed userDetailed) {
                presenter.setData(viewModel.getUser());
            }
        });

        presenter.setData(viewModel.getUser());

    }

    @Override
    public void setUser(UserDetailed user) {
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

    public DetailsPresenterContract getPresenter() {
        return presenter;
    }
}
