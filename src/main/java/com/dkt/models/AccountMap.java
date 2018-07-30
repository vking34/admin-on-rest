package com.dkt.models;

public class AccountMap {

    private String AccountId;
    private String AccessToken;
    private boolean Active;
    private int CountDelete;

    // must have an empty constructor
    public AccountMap(){}

    public AccountMap(String accountId, String accessToken, boolean active, int countDelete) {
        AccountId = accountId;
        AccessToken = accessToken;
        Active = active;
        CountDelete = countDelete;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

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

    public int getCountDelete() {
        return CountDelete;
    }

    public void setCountDelete(int countDelete) {
        CountDelete = countDelete;
    }
}
