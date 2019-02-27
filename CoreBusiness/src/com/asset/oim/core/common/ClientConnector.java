package com.asset.oim.core.common;


import com.asset.oim.core.models.ConfigurationHolder;

import com.asset.oim.core.utils.ConfigReader;

import java.io.IOException;

import java.util.Hashtable;

import java.util.Properties;

import com.asset.oim.core.utils.Defines;


import oracle.iam.platform.OIMClient;

/**
 *
 * @author Ahemd Taha
 */

public abstract class ClientConnector {

    protected ConfigurationHolder configurationHolder = null;
    Hashtable<String, String> env = new Hashtable<String, String>();
    protected static final String NULL = "(null)";
    protected static final String NL = "\n";
    protected static final String SP = "\t";
    protected OIMClient _oimClient = null;

    public ClientConnector() throws Exception {
         config();
        _oimClient = new OIMClient(env);
        _oimClient.login(configurationHolder.getOIM_USERNAME(), configurationHolder.getOIM_PASSWORD().toCharArray());
        return;
    }

    public void config(){
        try{
        String ctxFactory = "weblogic.jndi.WLInitialContextFactory";
        Properties prop = null;
        prop = new ConfigReader().read();
        if(prop!=null){
            configurationHolder = new ConfigurationHolder();
            configurationHolder.setAUTH_CONF(prop.getProperty(Defines.AUTH_CONF));
            configurationHolder.setOIM_URL(prop.getProperty(Defines.OIM_URL));
            configurationHolder.setOIM_PASSWORD(prop.getProperty(Defines.PASSWORD));
            configurationHolder.setOIM_USERNAME(prop.getProperty(Defines.USER_NAME));
        }
        else{
            log("Error While Loading property file");
            }
        System.setProperty("OIM.AppServerType", "wls");
        System.setProperty("APPSERVER_TYPE", "wls");
        env.put(OIMClient.JAVA_NAMING_FACTORY_INITIAL, ctxFactory);
        env.put(OIMClient.JAVA_NAMING_PROVIDER_URL,(configurationHolder!=null&&configurationHolder.getOIM_URL()!=null)?configurationHolder.getOIM_URL():"");
        System.setProperty("java.security.auth.login.config",(configurationHolder!=null&&configurationHolder.getAUTH_CONF()!=null)?configurationHolder.getAUTH_CONF():"");
        }
        catch(Exception ex){
            ex.printStackTrace();
            }
    }

    protected abstract void execute() throws Exception;

    protected void log(String str) {
        if (str != null) {
            System.out.println("LOG: " + str);
        }
        return;
    }
}
