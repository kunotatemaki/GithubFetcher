package com.rukiasoft.githubfetcher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rukiasoft.githubfetcher.network.model.UserBasicResponse;

/**
 * Created by Roll on 20/7/17.
 */

public class UserBasic {

    private String login;
    private String avatarUrl;
    private String htmlUrl;

    public UserBasic(UserBasicResponse response) {
        login = response.getLogin();
        avatarUrl = response.getAvatarUrl();
        htmlUrl = response.getHtmlUrl();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
