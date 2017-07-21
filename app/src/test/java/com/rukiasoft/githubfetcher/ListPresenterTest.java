package com.rukiasoft.githubfetcher;

import android.arch.lifecycle.MutableLiveData;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.presenters.implementations.ListPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContract;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListPresenterContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Roll on 21/7/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ListPresenterTest {

    @Mock
    NetworkHelper networkHelper;

    @Mock
    MutableLiveData<List<UserBasic>> usersEmpty;

    @Mock
    MutableLiveData<List<UserBasic>> usersFull;

    @Mock
    ListActivityContract listActivityContract;



    private static final String NAME = "kunotatemaki";
    private static final String URL = "http://someurl.com";
    private static final String IMAGE_URL = "http://someimageurl.com";



    private ListPresenterContract presenter;

    @Before
    public void setUp(){
        presenter = new ListPresenterImpl(networkHelper);
        presenter.setView(listActivityContract);
        configureMocks();
    }

    @Test
    public void checkIfShowProgressWhenRequestingUsers(){
        presenter.setData(usersEmpty);
        verify(listActivityContract, times(1)).showProgressBar();
    }


    @Test
    public void checkListActivityContractIsSetted(){
        presenter.setView(listActivityContract);
        assertNotNull(((ListPresenterImpl)presenter).getmListActivityContract());
    }

    @Test
    public void checkListActivityContractIsRemoved(){
        presenter.removeView();
        assertNull(((ListPresenterImpl)presenter).getmListActivityContract());
    }

    @Test
    public void checkOnEmptyListCallDownloadUsers(){
        presenter.setData(usersEmpty);
        verify(networkHelper, times(1)).getUsers(usersEmpty);
    }

    @Test
    public void checkOnNonEmptyListCallSetUI(){
        presenter.setData(usersFull);
        verify(listActivityContract, times(1)).setUsers(usersFull.getValue());
    }

    @Test
    public void checkIfHideProgressWhenSettingUsersOnUI(){
        presenter.setData(usersFull);
        verify(listActivityContract, times(1)).hideProgressBar();
    }

    private void configureMocks(){
        //mock users
        when(usersEmpty.getValue()).thenReturn(null);
        List<UserBasic> users = new ArrayList<>();
        UserBasic userBasic = new UserBasic(NAME, IMAGE_URL, URL);
        users.add(userBasic);
        when(usersFull.getValue()).thenReturn(users);

        //mock network
        doNothing().when(networkHelper).getUsers(usersEmpty);


    }


}
