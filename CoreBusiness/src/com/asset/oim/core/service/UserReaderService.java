package com.asset.oim.core.service;


import com.asset.oim.core.common.ClientInterfaceConnector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import oracle.iam.identity.usermgmt.api.UserManager;
import oracle.iam.identity.usermgmt.api.UserManagerConstants;
import oracle.iam.identity.usermgmt.vo.User;
import oracle.iam.provisioning.vo.Account;

/**
 * @author Ahemd Taha.
 */

public class UserReaderService extends ClientInterfaceConnector {
    private UserManager umgr = null;

    public UserReaderService() throws Exception {
        super();
        umgr = this.getUserManager();
        
        return;
    }

    public static void main(String[] args) throws Exception {
        UserManagerConstants.AttributeName.USER_LOGIN.getName();
        
        System.out.println("Account.ACCOUNT_TYPE.Primary:::  "+Account.ACCOUNT_TYPE.Primary);
        System.out.println("ssssssssss              "+UserManagerConstants.AttributeName.MANAGER_KEY.getName());
        UserReaderService test = new UserReaderService();
        test.execute();
        try {
          // test.execute();
           User User =  test.getUserInfo("ALICE.TAYLOR@ORACLE.COM");
            System.out.println("User"+User.getId());
        } catch (Exception ex) {
            test.log("EXCEPTION: " + ex.getMessage());
        }

        return;
    }

    public User getUserInfo(String userName) throws Exception {
        Set<String> attrNames  = new HashSet<String>();
        User user = null;
        user = umgr.getDetails(userName, attrNames, true);
        return user;

    }

    protected void execute() throws Exception {
        Object val = null;
        String str = null;
        String key = null;
        StringBuilder buf = new StringBuilder();
        HashMap<String, Object> mapAttrs = null;
        Set<String> attrNames = null;
        Iterator<String> iter = null;
        attrNames = new HashSet<String>();

        // UserManager umgr = null; // OIMClient API
        User user = null; // OIMClient API

        this.log("__BEGIN__");

        // umgr = this.getUserManager();

        /*
       * Create a set of attribute names, used to indicate which ones to return
       * If null / empty ... all will be returned
       */

        //      attrNames = new HashSet<String>();
        //      attrNames.add("act_key");
         //    attrNames.add("User Login");
          //   attrNames.add("First Name");
        //      attrNames.add("Last Name");
        //      attrNames.add("usr_password");
        //      attrNames.add("Email");
        //      attrNames.add("act_key");
        //      attrNames.add("Xellerate Type");
              attrNames.add("usr_manager_key");
            //Manager Login

        user = umgr.getDetails("TESTADCONN", attrNames, true);
        this.log("Got user detail");
        buf.append(NL);
      
        
        str =   user.getOrganizationKey();
        buf.append("Organization: ").append((str != null ? str : NULL)).append(NL);
        
        str = user.getId();
        buf.append("Id: ").append((str != null ? str : NULL)).append(NL);

        str = user.getEntityId();
        buf.append("Entity Id: ").append((str != null ? str : NULL)).append(NL);

        str = user.getLogin();
        buf.append("Login: ").append((str != null ? str : NULL)).append(NL);

        str = user.getFirstName();
        buf.append("First Name: ").append((str != null ? str : NULL)).append(NL);

        str = user.getMiddleName();
        buf.append("Middle Name: ").append((str != null ? str : NULL)).append(NL);

        str = user.getLastName();
        buf.append("Last Name: ").append((str != null ? str : NULL)).append(NL);

        str = user.getCommonName();
        buf.append("Common Name: ").append((str != null ? str : NULL)).append(NL);

        str = user.getDisplayName();
        buf.append("Display Name: ").append((str != null ? str : NULL)).append(NL);

        str = user.getEmployeeNumber();
        buf.append("Employee Number: ").append((str != null ? str : NULL)).append(NL);

        str = user.getEmployeeType();
        buf.append("Employee Type: ").append((str != null ? str : NULL)).append(NL);

        str = user.getEmail();
        buf.append("Email : ").append((str != null ? str : NULL)).append(NL);

        str = user.getUserType();
        buf.append("User Type : ").append((str != null ? str : NULL)).append(NL);

        str = user.getCountry();
        buf.append("Country: ").append((str != null ? str : NULL)).append(NL);

        str = user.getDescription();
        buf.append("Description: ").append((str != null ? str : NULL)).append(NL);

        str = user.getStatus();
        buf.append("Status : ").append((str != null ? str : NULL)).append(NL);

        str = user.getGenerationQualifier();
        buf.append("Generation Qualifier : ").append((str != null ? str : NULL)).append(NL);

        str = user.getAccountStatus();
        buf.append("Account Status: ").append((str != null ? str : NULL)).append(NL);

        str = user.getManagerKey();
        buf.append("Manager Key: ").append((str != null ? str : NULL)).append(NL);

        str = user.getManuallyLocked();
        buf.append("Manually Locked: ").append((str != null ? str : NULL)).append(NL);

        str = user.getUserDisabled();
        buf.append("User Disabled : ").append((str != null ? str : NULL)).append(NL);

        str = user.getPolicyUpdateEnabled();
        buf.append("Policy Update Enabled: ").append((str != null ? str : NULL)).append(NL);

        str = user.getChangePasswordAtNextLogin();
        buf.append("Change Password At Next login : ").append((str != null ? str : NULL)).append(NL);

        str = user.getPasswordCantChange();
        buf.append("Password Cant Change: ").append((str != null ? str : NULL)).append(NL);

        str = user.getPasswordExpired();
        buf.append("Password Expired : ").append((str != null ? str : NULL)).append(NL);

        str = user.getPasswordGenerated();
        buf.append("Password Generated  : ").append((str != null ? str : NULL)).append(NL);

        str = user.getPasswordMustChange();
        buf.append("Password Must Change : ").append((str != null ? str : NULL)).append(NL);

        str = user.getPasswordNeverExpires();
        buf.append("Password Never Expires: ").append((str != null ? str : NULL)).append(NL);

        str = user.getPasswordWarned();
        buf.append("Password Warned: ").append((str != null ? str : NULL)).append(NL);

        mapAttrs = user.getAttributes();

        buf.append("Attributes : ").append(NL);

        iter = mapAttrs.keySet().iterator();
        while (iter.hasNext()) {
            key = iter.next();
            val = mapAttrs.get(key);
            buf.append(key).append("=");
            if (val != null) {
                buf.append("'").append(val).append(NL);
            } else {
                buf.append(NL);
            }
        }
        this.log(buf.toString());
        this.log("__END__");

        return;
    }
    
}
