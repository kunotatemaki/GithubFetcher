package com.rukiasoft.githubfetcher.ui.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rukiasoft.githubfetcher.model.UserBasic;

import java.util.List;

/**
 * Created by Roll on 20/7/17.
 */

public class ListViewModel extends ViewModel{

    private MutableLiveData<List<UserBasic>> users;

    public MutableLiveData<List<UserBasic>> getUsers() {
        if(users == null){
            users = new MutableLiveData<>();
        }
        return users;
    }
}
