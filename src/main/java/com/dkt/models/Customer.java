package com.dkt.models;


import org.springframework.data.mongodb.core.mapping.Field;

public class Customer {

    @Field("PageId")
    private String PageId;

    @Field("StoreId")
    private String StoreId;

    @Field("BizwebCustomerId")
    private int BizwebCustomerId;

    @Field("Name")
    private String Name;

    @Field("Email")
    private String Email;


    public Customer(){}

    public Customer(String pageId, String storeId, int bizwebCustomerId, String name, String email) {
        PageId = pageId;
        StoreId = storeId;
        BizwebCustomerId = bizwebCustomerId;
        Name = name;
        Email = email;
    }

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPageId() {
        return PageId;
    }

    public void setPageId(String pageId) {
        PageId = pageId;
    }

    public int getBizwebCustomerId() {
        return BizwebCustomerId;
    }

    public void setBizwebCustomerId(int bizwebCustomerId) {
        BizwebCustomerId = bizwebCustomerId;
    }
}
