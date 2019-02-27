package com.asset.oim.core.service;

import com.asset.oim.core.common.ClientInterfaceConnector;
import com.asset.oim.core.models.UserModel;

import com.asset.oim.core.utils.ConfigReader;

import com.asset.oim.core.utils.Defines;

import oracle.iam.identity.usermgmt.vo.User;

import java.util.HashMap;
import java.util.Properties;

public class ModifierService extends ClientInterfaceConnector{
    public ModifierService() throws Exception {
        super();
    }

    @Override
    protected void execute() {
    }
    public void modifyUser(UserModel userModel){
           Properties prop = null;
           prop = new ConfigReader().read();
           try {            
                  HashMap<String, Object> userAttributeValueMap = new HashMap<String, Object>();
                  userAttributeValueMap.put(prop.getProperty(Defines.MANAGER_FIELD), userModel.getManagerDn());               
                  User retrievedUser = new UserSearchService().searchUser(userModel.getUserId());
                  User user = new User(retrievedUser.getEntityId(),userAttributeValueMap);                   
                  this.getUserManager().modify(user);
                  System.out.println("User Updated");
             } catch (Exception e) {
               e.printStackTrace();
             } 
       }
}
