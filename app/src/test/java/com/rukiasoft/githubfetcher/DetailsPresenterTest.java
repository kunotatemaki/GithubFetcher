package com.rukiasoft.githubfetcher;

import android.arch.lifecycle.MutableLiveData;

import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.presenters.implementations.DetailsPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.implementations.ListPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContract;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsPresenterContract;

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
public class DetailsPresenterTest {

    @Mock
    NetworkHelper networkHelper;

    @Mock
    MutableLiveData<UserDetailed> mUserEmpty;

    @Mock
    MutableLiveData<UserDetailed> mUserFull;

    @Mock
    DetailsActivityContract mDetailsActivityContract;



    private static final String NAME = "kunotatemaki";
    private static final String URL = "http://someurl.com";
    private static final String IMAGE_URL = "http://someimageurl.com";



    private DetailsPresenterContract presenter;

    @Before
    public void setUp(){
        presenter = new DetailsPresenterImpl(networkHelper);
        presenter.setView(mDetailsActivityContract);
        configureMocks();
    }

    @Test
    public void checkIfShowProgressWhenRequestingUsers(){
        presenter.setData(mUserEmpty);
        verify(mDetailsActivityContract, times(1)).showProgressBar();
    }


    @Test
    public void checkListActivityContractIsSetted(){
        presenter.setView(mDetailsActivityContract);
        assertNotNull(((DetailsPresenterImpl)presenter).getmDetailsActivityContract());
    }

    @Test
    public void checkListActivityContractIsRemoved(){
        presenter.removeView();
        assertNull(((DetailsPresenterImpl)presenter).getmDetailsActivityContract());
    }

    @Test
    public void checkOnEmptyListCallDownloadUsers(){
        presenter.setName(NAME);
        presenter.setData(mUserEmpty);
        verify(networkHelper, times(1)).getUserInfo(NAME, mUserEmpty);
    }

    @Test
    public void checkOnNonEmptyListCallSetUI(){
        presenter.setData(mUserFull);
        verify(mDetailsActivityContract, times(1)).setUser(mUserFull.getValue());
    }

    @Test
    public void checkIfHideProgressWhenSettingUsersOnUI(){
        presenter.setData(mUserFull);
        verify(mDetailsActivityContract, times(1)).hideProgressBar();
    }

    private void configureMocks(){
        //mock users
        when(mUserEmpty.getValue()).thenReturn(null);
        UserDetailed userDetailed = new UserDetailed();
        when(mUserFull.getValue()).thenReturn(userDetailed);

        //mock network
        doNothing().when(networkHelper).getUserInfo(NAME, mUserEmpty);


    }


}
