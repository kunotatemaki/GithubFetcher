package com.rukiasoft.githubfetcher.ui.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.model.UserDetailed;

import java.util.List;

/**
 * Created by Roll on 20/7/17.
 */

public class DetailsViewModel extends ViewModel{

    private MutableLiveData<UserDetailed> user;

    public MutableLiveData<UserDetailed> getUser() {
        if(user == null){
            user = new MutableLiveData<>();
        }
        return user;
    }
}
