package com.asset.oim.core.service;

import com.asset.oim.core.common.ClientInterfaceConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oracle.iam.identity.usermgmt.api.UserManager;
import oracle.iam.identity.usermgmt.api.UserManagerConstants;
import oracle.iam.identity.usermgmt.vo.User;
import oracle.iam.platform.entitymgr.vo.SearchCriteria;

/**
 * @author Ahemd Taha
 */

public class UserSearchService extends ClientInterfaceConnector {

    private UserManager umgr = null; // OIMClient API


    public UserSearchService() throws Exception {
        super();
        return;
    }

    public static void main(String[] args) throws Exception {

        UserSearchService test = new UserSearchService();
        try {
         System.out.println(test.getAllUsersLoginName("*"));
            //test.execute();
        } catch (Exception ex) {
            test.log("EXCEPTION: " + ex.getMessage());
        }

        return;
    }
    public String getUserLoginName(String userKey) throws Exception {
            String loginName="";
            List<User> users = null;
            HashMap<String, Object> attributes = null;
            HashMap<String, Object> parameters = null;
            Set<String> attrNames = null;
            UserManager umgr = null; // OIMClient API
            SearchCriteria criteria = null; // OIMClient API
            umgr = this.getUserManager();
            criteria = new SearchCriteria("usr_key", userKey, SearchCriteria.Operator.EQUAL);
            attrNames = new HashSet<String>();
            attrNames.add(UserManagerConstants.AttributeName.USER_LOGIN.getId());
            users = umgr.search(criteria, attrNames, parameters);
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
                    attributes = user.getAttributes();
                    loginName = (String)attributes.get(UserManagerConstants.AttributeName.USER_LOGIN.getId());
                }       
            } else {
                this.log("search result is empty");
            }

        return loginName;
        }

    public String[] getAllUsersLoginName(String creiteria) throws Exception {
        ArrayList<String> usersList = new ArrayList<String>();
        List<User> users = null;
        HashMap<String, Object> attributes = null;
        HashMap<String, Object> parameters = null;
        Set<String> attrNames = null;
        UserManager umgr = null; // OIMClient API
        SearchCriteria criteria = null; // OIMClient API
        umgr = this.getUserManager();
        criteria = new SearchCriteria("Organization Name", creiteria, SearchCriteria.Operator.EQUAL);
        attrNames = new HashSet<String>();
        attrNames.add(UserManagerConstants.AttributeName.USER_LOGIN.getId());
        users = umgr.search(criteria, attrNames, parameters);
        ArrayList<String> excludedList = new ArrayList<String>();
        excludedList.add("OIMINTERNAL");
        excludedList.add("XELSYSADM"); 
        excludedList.add("WEBLOGIC");
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                attributes = user.getAttributes();
                if(!excludedList.contains((String)attributes.get(UserManagerConstants.AttributeName.USER_LOGIN.getId())))
                usersList.add((String)attributes.get(UserManagerConstants.AttributeName.USER_LOGIN.getId()));
            }
        } else {
            this.log("search result is empty");
        }

        String[] usersArray = usersList.toArray(new String[usersList.size()]);

        return usersArray;
    }
    public User searchUser(String userId) {
     
            Set<String> resAttrs = new HashSet<String>();
            User user = null;
            try {
                  user = this.getUserManager().getDetails(userId, resAttrs, true);
                } catch (Exception e) {
                  e.printStackTrace();
                 
                }      
            return user;            
            }

    protected void execute() throws Exception {
        Object val = null;
        StringBuilder buf = null;
        List<User> users = null;
        HashMap<String, Object> attributes = null;
        HashMap<String, Object> parameters = null;
        Set<String> keys = null;
        Set<String> attrNames = null;

        UserManager umgr = null; // OIMClient API
        SearchCriteria criteria = null; // OIMClient API

        this.log("__BEGIN__");
        umgr = this.getUserManager();

        /*
       * Create a SearchCriteria
       */

        //criteria = new SearchCriteria("First Name", "mostfatatorky", SearchCriteria.Operator.EQUAL);
        //      criteria = new SearchCriteria("Email", "John.Wayne@openptk.org", SearchCriteria.Operator.EQUAL);
        //      criteria = new SearchCriteria("First Name", "scott", SearchCriteria.Operator.EQUAL);
        //      criteria = new SearchCriteria("User Login", "*", SearchCriteria.Operator.EQUAL);
        criteria = new SearchCriteria("usr_key", "1002", SearchCriteria.Operator.EQUAL);

        /*
       * What attributes to return
       * if null / empty, all attributes are returned
       */

        attrNames = new HashSet<String>();
        attrNames.add("User Login");
        attrNames.add("First Name");
        attrNames.add("Last Name");
        attrNames.add("Organization Name");
        attrNames.add("UD_ADUSER_ORGNAM");


        //      attrNames.add("Xellerate Type");
        //      attrNames.add("Role");

        /*
       * Parameter map: determine how many rows to return, how to sort results
       * can be empty / null ... all data, default internal sorting
       */

        //      mapParams = new HashMap<String, Object>();
        //      mapParams.put("STARTROW", 0);
        //      mapParams.put("ENDROW", -1);
        //      mapParams.put("SORTEDBY", "Last Name");
        //      mapParams.put("SORTORDER", "SortOrder.ASCENDING");

        /*
       * run the search
       */

        users = umgr.search(criteria, attrNames, parameters);

        /*
       * Display the results
       */

        if (users != null && !users.isEmpty()) {
            this.log("search results, quantity=" + users.size());
            for (User user : users) {
                attributes = user.getAttributes();
                buf = new StringBuilder();

                keys = attributes.keySet();
                for (String key : keys) {
                    val = attributes.get(key);
                    buf.append(key).append("='").append(val).append("', ");

                }
                this.log("EntityId: " + user.getEntityId() + ", Id: " + user.getId() + ", Attributes: " +
                         buf.toString());
            }
        } else {
            this.log("search result is empty");
        }

        this.log("__END__");

        return;
    }

    public void setUmgr(UserManager umgr) {
        this.umgr = umgr;
    }

    public UserManager getUmgr() throws Exception {
        return super.getUserManager();
    }
}
