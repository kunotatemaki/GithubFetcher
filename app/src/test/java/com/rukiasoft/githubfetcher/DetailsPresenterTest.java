package com.rukiasoft.githubfetcher;

import android.arch.lifecycle.MutableLiveData;

import com.rukiasoft.githubfetcher.model.UserDetailed;
import com.rukiasoft.githubfetcher.network.NetworkHelper;
import com.rukiasoft.githubfetcher.ui.presenters.implementations.DetailsPresenterImpl;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.DetailsActivityContracts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    DetailsActivityContracts.RequiredViewOps mDetailsActivityContract;

    @InjectMocks
    DetailsPresenterImpl presenter;



    private static final String NAME = "kunotatemaki";
    private static final String URL = "http://someurl.com";
    private static final String IMAGE_URL = "http://someimageurl.com";



    @Before
    public void setUp(){
        presenter.bindView(mDetailsActivityContract);
        configureMocks();
    }

    @Test
    public void checkIfShowProgressWhenRequestingUsers(){
        presenter.setDataFromNetworkOrCache(mUserEmpty);
        verify(mDetailsActivityContract, times(1)).showProgressBar();
    }


    @Test
    public void checkListActivityContractIsSetted(){
        presenter.bindView(mDetailsActivityContract);
        assertNotNull(((DetailsPresenterImpl)presenter).getActivityAsociatedToPresenter());
    }

    @Test
    public void checkListActivityContractIsRemoved(){
        presenter.unbindView();
        assertNull(presenter.getActivityAsociatedToPresenter());
    }

    @Test
    public void checkOnEmptyListCallDownloadUsers(){
        presenter.setName(NAME);
        presenter.setDataFromNetworkOrCache(mUserEmpty);
        verify(networkHelper, times(1)).getUserInfo(NAME, mUserEmpty);
    }

    @Test
    public void checkOnNonEmptyListCallSetUI(){
        presenter.setDataFromNetworkOrCache(mUserFull);
        verify(mDetailsActivityContract, times(1)).setUserInUI(mUserFull.getValue());
    }

    @Test
    public void checkIfHideProgressWhenSettingUsersOnUI(){
        presenter.setDataFromNetworkOrCache(mUserFull);
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
