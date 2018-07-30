package com.dkt.models;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class PackageInfo {

    private String PackageName;
    private int AdminLimit;
    private int PageLimit;
    private List<String> Scopes;

    public PackageInfo(){}

    public PackageInfo(String packageName, int adminLimit, int pageLimit, List<String> scopes) {
        PackageName = packageName;
        AdminLimit = adminLimit;
        PageLimit = pageLimit;
        Scopes = scopes;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public int getAdminLimit() {
        return AdminLimit;
    }

    public void setAdminLimit(int adminLimit) {
        AdminLimit = adminLimit;
    }

    public int getPageLimit() {
        return PageLimit;
    }

    public void setPageLimit(int pageLimit) {
        PageLimit = pageLimit;
    }

    public List<String> getScopes() {
        return Scopes;
    }

    public void setScopes(List<String> scopes) {
        Scopes = scopes;
    }
}
