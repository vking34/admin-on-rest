package com.dkt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


public class PackageInfo {

    @Field("PackageName")
    private String packageName;

    @Field("AdminLimit")
    private int adminLimit;

    @Field("PageLimit")
    private int pageLimit;

    @Field("Scopes")
    private List<String> scopes;

    public PackageInfo(){}

    public PackageInfo(String packageName, int adminLimit, int pageLimit, List<String> scopes) {
        this.packageName = packageName;
        this.adminLimit = adminLimit;
        this.pageLimit = pageLimit;
        this.scopes = scopes;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getAdminLimit() {
        return adminLimit;
    }

    public void setAdminLimit(int adminLimit) {
        this.adminLimit = adminLimit;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public String toString(){
        return "name: " + packageName + "; adminLimit: " + adminLimit + "; pageLimit: " + packageName + "; Scopes: " + scopes;
    }
}
