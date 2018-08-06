package com.dkt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "Pages")
public class FbPage {

    @Id
    private String id;

    @Field("Name")
    private String name;

    @Field("FacebookPageId")
    private String facebookPageId;

    @Field("CreatedOn")
    private String createdOn;

    @Field("ModifiedOn")
    private String modifiedOn;

    @Field("AccountMaps")
    private List<AccountMap> accountMaps;

    @Field("IsUsed")
    private boolean isUsed;

    @Field("WebhookStatus")
    private String webhookStatus;

//    @Field("StoreId")
//    private int storeId;
//
//    public int getStoreId() {
//        return storeId;
//    }
//
//    public void setStoreId(int storeId) {
//        this.storeId = storeId;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebookPageId() {
        return facebookPageId;
    }

    public void setFacebookPageId(String facebookPageId) {
        this.facebookPageId = facebookPageId;
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

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public String getWebhookStatus() {
        return webhookStatus;
    }

    public void setWebhookStatus(String webhookStatus) {
        this.webhookStatus = webhookStatus;
    }

    public List<AccountMap> getAccountMaps() {
        return accountMaps;
    }

    public void setAccountMaps(List<AccountMap> accountMaps) {
        this.accountMaps = accountMaps;
    }
}
