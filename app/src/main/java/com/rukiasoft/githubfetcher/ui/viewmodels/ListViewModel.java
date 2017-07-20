package com.rukiasoft.githubfetcher.ui.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rukiasoft.githubfetcher.model.UserBasicResponse;
import com.rukiasoft.githubfetcher.model.UserDetailedResponse;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListPresenter;

import java.util.List;

/**
 * Created by Roll on 20/7/17.
 */

public class ListViewModel extends ViewModel{

    private MutableLiveData<List<UserBasicResponse>> users;

//    public void setUsersInRecycler(ListPresenter presenter) {
//        if(users == null){
//            users = new MutableLiveData<List<UserBasicResponse>>();
//            presenter.getUsers(users);
//        }else {
//            presenter.setUsers(users.getValue());
//        }
//    }

    public MutableLiveData<List<UserBasicResponse>> getUsers() {
        return users;
    }
}
