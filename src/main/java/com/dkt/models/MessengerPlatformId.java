package com.dkt.models;

public class MessengerPlatformId {

    private String MpUserId;
    private String FbPageId;

    public MessengerPlatformId(){}

    public MessengerPlatformId(String mpUserId, String fbPageId) {
        MpUserId = mpUserId;
        FbPageId = fbPageId;
    }

    public String getMpUserId() {
        return MpUserId;
    }

    public void setMpUserId(String mpUserId) {
        MpUserId = mpUserId;
    }

    public String getFbPageId() {
        return FbPageId;
    }

    public void setFbPageId(String fbPageId) {
        FbPageId = fbPageId;
    }
}
