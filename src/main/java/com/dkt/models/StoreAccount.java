package com.dkt.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class StoreAccount {

    private String accountId;
    private String accountName;
    private List<String> pageName;

    public StoreAccount(){}

    public StoreAccount(String accountId, String accountName) {
        this.accountId = accountId;
        this.accountName = accountName;
        pageName = new ArrayList<String>();
    }

    @JsonProperty("id")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("accountName")
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @JsonProperty("pagesName")
    public List<String> getPageName() {
        return pageName;
    }

    public void setPageName(List<String> pageName) {
        this.pageName = pageName;
    }
}
