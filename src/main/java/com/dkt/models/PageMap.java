package com.dkt.models;

public class PageMap {

    private String PageId;
    private boolean Active;

    public PageMap(){}

    public PageMap(String pageId, boolean active) {
        PageId = pageId;
        Active = active;
    }

    public String getPageId() {
        return PageId;
    }

    public void setPageId(String pageId) {
        PageId = pageId;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }
}
