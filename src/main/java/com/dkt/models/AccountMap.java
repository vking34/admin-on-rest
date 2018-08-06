package com.dkt.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class AccountMap {

    private String AccountId;
    private String AccessToken;
    private boolean Active;
    private int CountDelete;
    private String Name;

    // must have an empty constructor
    public AccountMap(){}

    public AccountMap(String accountId, String accessToken, boolean active, int countDelete, String name) {
        AccountId = accountId;
        AccessToken = accessToken;
        Active = active;
        CountDelete = countDelete;
        Name = name;
    }


    @JsonProperty("name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    @JsonProperty("id")
    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }


    @JsonProperty("access_token")
    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }


    @JsonProperty("count_delete")
    public int getCountDelete() {
        return CountDelete;
    }

    public void setCountDelete(int countDelete) {
        CountDelete = countDelete;
    }
}
