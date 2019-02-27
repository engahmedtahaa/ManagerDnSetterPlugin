package com.asset.oim.core.common;


import com.bea.common.security.xacml.attr.LongAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Set;

import oracle.iam.identity.usermgmt.api.UserManager;
import oracle.iam.provisioning.api.ProvisioningService;
import oracle.iam.provisioning.vo.Account;
import oracle.iam.provisioning.vo.AccountData;
import oracle.iam.provisioning.vo.ApplicationInstance;
import oracle.iam.provisioning.vo.ChildTableRecord;
import oracle.iam.provisioning.vo.FormField;
import oracle.iam.provisioning.vo.FormInfo;
import oracle.iam.selfservice.uself.uselfmgmt.api.UnauthenticatedSelfService;

/**
 *
 * @author Ahemd Taha
 */
public abstract class ClientInterfaceConnector extends ClientConnector {
    public ClientInterfaceConnector() throws Exception {
        super();
        return;
    }

    protected UserManager getUserManager() throws Exception {
        UserManager umgr = null;
        umgr = _oimClient.getService(UserManager.class);
        this.log("UserManager ready");
        return umgr;
    }

    protected UnauthenticatedSelfService getUnauthSelfService() {
        UnauthenticatedSelfService unauthn = null;

        unauthn = _oimClient.getService(UnauthenticatedSelfService.class);

        this.log("UnauthenticatedSelfService ready");

        return unauthn;
    }
}
