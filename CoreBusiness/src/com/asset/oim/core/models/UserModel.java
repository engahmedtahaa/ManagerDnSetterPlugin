package com.asset.oim.core.models;

public class UserModel {
    private String userId="";
    private String managerDn="";
    public UserModel() {
        super();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setManagerDn(String managerDn) {
        this.managerDn = managerDn;
    }

    public String getManagerDn() {
        return managerDn;
    }
}
