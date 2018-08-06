package com.dkt.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Accounts")
public class Account {

    @Id
    private String id;

    @Field("FacebookUserId")
    private String facebookUserId;

    @Field("Name")
    private String name;

    @Field("AccessToken")
    private String accessToken;

    @Field("CreatedOn")
    private String createdOn;

    @Field("ModifiedOn")
    private String modifiedOn;

    @Field("PageMaps")
    private List<PageMap> pageMaps;

    @Field("BizwebCustomerMaps")
    private List<Customer> bizwebCustomerMaps;

    @Field("MessengerPlatformIds")
    private List<MessengerPlatformId> messengerPlatformIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacebookUserId() {
        return facebookUserId;
    }

    public void setFacebookUserId(String facebookUserId) {
        this.facebookUserId = facebookUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

//    public List<String> getPageMaps() {
//        return pageMaps;
//    }
//
//    public void setPageMaps(List<String> pageMaps) {
//        this.pageMaps = pageMaps;
//    }


    public List<PageMap> getPageMaps() {
        return pageMaps;
    }

    public void setPageMaps(List<PageMap> pageMaps) {
        this.pageMaps = pageMaps;
    }

    public List<Customer> getBizwebCustomerMaps() {
        return bizwebCustomerMaps;
    }

    public void setBizwebCustomerMaps(List<Customer> bizwebCustomerMaps) {
        this.bizwebCustomerMaps = bizwebCustomerMaps;
    }

    public List<MessengerPlatformId> getMessengerPlatformIds() {
        return messengerPlatformIds;
    }

    public void setMessengerPlatformIds(List<MessengerPlatformId> messengerPlatformIds) {
        this.messengerPlatformIds = messengerPlatformIds;
    }
}
