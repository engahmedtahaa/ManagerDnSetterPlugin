package com.asset.oim.core.models;



import java.io.Serializable;

/**
 * @author Ahemd Taha
 */
public class ConfigurationHolder implements Serializable {

    private String OIM_URL = "";
    private String AUTH_CONF = "";
    private String OIM_USERNAME = "";
    private String OIM_PASSWORD = "";

    public ConfigurationHolder() {
        super();
    }

    public void setOIM_URL(String OIM_URL) {
        this.OIM_URL = OIM_URL;
    }

    public String getOIM_URL() {
        return OIM_URL;
    }

    public void setAUTH_CONF(String AUTH_CONF) {
        this.AUTH_CONF = AUTH_CONF;
    }

    public String getAUTH_CONF() {
        return AUTH_CONF;
    }

    public void setOIM_USERNAME(String OIM_USERNAME) {
        this.OIM_USERNAME = OIM_USERNAME;
    }

    public String getOIM_USERNAME() {
        return OIM_USERNAME;
    }

    public void setOIM_PASSWORD(String OIM_PASSWORD) {
        this.OIM_PASSWORD = OIM_PASSWORD;
    }

    public String getOIM_PASSWORD() {
        return OIM_PASSWORD;
    }

}
