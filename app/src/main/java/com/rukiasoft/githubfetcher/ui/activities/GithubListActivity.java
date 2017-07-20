package com.rukiasoft.githubfetcher.ui.activities;

import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.rukiasoft.githubfetcher.GithubFetcherApplication;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.model.UserBasicResponse;
import com.rukiasoft.githubfetcher.model.UserDetailedResponse;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.network.retrofit.RetrofitNetworkHelperImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GithubListActivity extends BaseActivity {

    @Inject
    NetworkHelper mNetworkHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((GithubFetcherApplication)getApplication()).getGithubFetcherComponent().inject(this);
        setContentView(R.layout.activity_github_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_github_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        MutableLiveData<UserDetailedResponse> user = new MutableLiveData<>();

        user.setValue(new UserDetailedResponse());
        mNetworkHelper.getUserInfo("kunotatemaki", user);
    }
}
