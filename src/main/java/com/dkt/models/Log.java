package com.dkt.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "Logs")
public class Log {

    @Field("Date")
    private Date date;

    @Field("username")
    private String username;

    @Field("Role")
    private String role;

    @Field("InvokedMethod")
    private String invokedMethod;

    @Field("Params")
    private String params;

    @Field("Result")
    private boolean result;

    public Log(Date date, String username, String role, String invokedMethod, String params, boolean result) {
        this.date = date;
        this.username = username;
        this.role = role;
        this.invokedMethod = invokedMethod;
        this.params = params;
        this.result = result;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getInvokedMethod() {
        return invokedMethod;
    }

    public void setInvokedMethod(String invokedMethod) {
        this.invokedMethod = invokedMethod;
    }

    @Override
    public String toString(){
        return "{\nusername: " + username + ",\nrole: " + role + ",\ninvoked Method: " + invokedMethod + ",\nparams: " + params + ",\nresult: " + result + "\n};";
    }
}

