package com.dkt.models;

public class Customer {

    private String PageId;

    private int BizwebCustomerId;

    public Customer(){}

    public Customer(String pageId, int bizwebCustomerId) {
        PageId = pageId;
        BizwebCustomerId = bizwebCustomerId;
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
