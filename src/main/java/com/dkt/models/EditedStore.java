package com.dkt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EditedStore {

    private String alias;

    private PackageInfo packageInfo;

    public EditedStore(){}

    public EditedStore(String alias, PackageInfo packageInfo) {
        this.alias = alias;
        this.packageInfo = packageInfo;
    }

    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }


    public void setAlias(String alias) {
        this.alias = alias;
    }

    @JsonProperty("packageInfo")
    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }
}
