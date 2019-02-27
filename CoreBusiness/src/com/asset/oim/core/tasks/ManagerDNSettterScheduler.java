package com.asset.oim.core.tasks;

import com.asset.oim.core.service.UserSearchService;


import com.asset.oim.core.models.UserModel;
import com.asset.oim.core.service.ModifierService;
import com.asset.oim.core.service.ProvisioningServiceProvider;
import com.asset.oim.core.service.UserReaderService;

import com.asset.oim.core.utils.Defines;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import oracle.iam.scheduler.vo.TaskSupport;
import oracle.iam.identity.usermgmt.vo.User;
import oracle.iam.provisioning.vo.Account;
import oracle.iam.provisioning.vo.AccountData;

public class ManagerDNSettterScheduler extends TaskSupport {
    public ManagerDNSettterScheduler() {
        super();
    }

    public void execute(HashMap taskParameters) {
        System.out.println("Enter ManagerDNSettterScheduler *************");
        UserSearchService userSearchService = null;
        UserReaderService userReaderService = null;
        ProvisioningServiceProvider provisioningServiceProvider = null;
        ModifierService modifierService = null;
        User managerInfo = null;
        boolean managerDnIsSet = false;
        String tempManagerOUHolder = "";
        String managerCN = "";
        String managerOU = "";
        String managerKey = "";
        UserModel userModel = null;
        try {
            userSearchService = new UserSearchService();
            userReaderService = new UserReaderService();
            modifierService = new ModifierService();
            provisioningServiceProvider = new ProvisioningServiceProvider();
             for (String userLogin : userSearchService.getAllUsersLoginName("*")) {
            System.out.println("Enter ManagerDNSettterScheduler ************* for User :" + userLogin);
            userModel = new UserModel();
            User user = userReaderService.getUserInfo(userLogin);
            if (user != null) {
                managerKey = user.getManagerKey();
            }
            if (managerKey != null) {
                String managerLoginName = userSearchService.getUserLoginName(managerKey);
                managerInfo = userReaderService.getUserInfo(managerLoginName);
            }
            if (managerInfo != null) {
                java.util.List<Account> ManagerAccountsList =  provisioningServiceProvider.getProvisioningService().getAccountsProvisionedToUser(managerInfo.getId());
                if (ManagerAccountsList != null && !ManagerAccountsList.isEmpty()) {
                    for (Account ac : ManagerAccountsList) {
                        if (ac.getAppInstance().getItResourceName().equalsIgnoreCase(Defines.ACTIVE_DIRECTORY_ACCOUNT) &&
                            ac.getAccountType().equals(Account.ACCOUNT_TYPE.Primary)) {
                            Account managerAccount =
                                provisioningServiceProvider.getProvisioningService().getAccountDetails(Long.parseLong(ac.getAccountID()));
                            AccountData managerAccountData = managerAccount.getAccountData();
                            Map<String, Object> managerParentData = managerAccountData.getData();
                            if (!managerDnIsSet) {
                                tempManagerOUHolder = (String)managerParentData.get(Defines.USER_OU);
                                managerOU = tempManagerOUHolder;
                                managerDnIsSet = true;
                            }
                            managerCN = (String)managerParentData.get(Defines.USER_CN);
                        }
                    }
                }
            }
            String tempManagerOU = managerOU.substring(managerOU.indexOf("~") + 1);
            String managerDN = "";
            managerDN = "CN=" + managerCN + "," + tempManagerOU;
            System.out.println("Manger DN for User " + userLogin + "is :" + managerDN);
            userModel.setManagerDn(managerDN);
            userModel.setUserId(userLogin);
            modifierService.modifyUser(userModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap getAttributes() {
        return null;
    }

    public void setAttributes() {
    }

}
