package com.dkt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "BizwebStores")
public class BizwebStore {

    @Id
    private String id;

    @Field("CreatedOn")
    private String createdOn;

    @Field("ModifiedOn")
    private String modifiedOn;

    @Field("Alias")
    private String alias;

    @Field("ApiAccessToken")
    private String apiAccessToken;

    @Field("FbUserId")
    private String fbUserId;

    @Field("FbUserAccessToken")
    private String fbUserAccessToken;

    @Field("PageIds")
    private List<String> pageIds;

    @Field("PackageInfo")
    private PackageInfo packageInfo;

    @Field("AccountIds")
    private List<String> accountIds;

    @Field("ExpirationTimeChannel")
    private String expirationTimeChannel;

    @Field("HasUpdate")
    private int hasUpdate;

    @Field("UpdateThreadKey")
    private String updateThreadKey;

    @Field("BwChannels")
    private String bwChannels;

    @Field("StoreId")
    private int storeId;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getApiAccessToken() {
        return apiAccessToken;
    }

    public void setApiAccessToken(String apiAccessToken) {
        this.apiAccessToken = apiAccessToken;
    }

    public String getFbUserId() {
        return fbUserId;
    }

    public void setFbUserId(String fbUserId) {
        this.fbUserId = fbUserId;
    }

    public String getFbUserAccessToken() {
        return fbUserAccessToken;
    }

    public void setFbUserAccessToken(String fbUserAccessToken) {
        this.fbUserAccessToken = fbUserAccessToken;
    }

    public List<String> getPageIds() {
        return pageIds;
    }

    public void setPageIds(List<String> pageIds) {
        this.pageIds = pageIds;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public List<String> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(List<String> accountIds) {
        this.accountIds = accountIds;
    }

    public int getHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(int hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    public String getUpdateThreadKey() {
        return updateThreadKey;
    }

    public void setUpdateThreadKey(String updateThreadKey) {
        this.updateThreadKey = updateThreadKey;
    }

    public String getBwChannels() {
        return bwChannels;
    }

    public void setBwChannels(String bwChannels) {
        this.bwChannels = bwChannels;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getExpirationTimeChannel() {
        return expirationTimeChannel;
    }

    public void setExpirationTimeChannel(String expirationTimeChannel) {
        this.expirationTimeChannel = expirationTimeChannel;
    }
}
