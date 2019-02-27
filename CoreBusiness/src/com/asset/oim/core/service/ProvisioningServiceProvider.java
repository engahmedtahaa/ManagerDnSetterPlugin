package com.asset.oim.core.service;

import com.asset.oim.core.common.ClientInterfaceConnector;

import oracle.iam.identity.usermgmt.api.UserManager;
import oracle.iam.provisioning.api.ProvisioningService;

/**
 * @author  Ahmed Taha
 * @version 1.0
 */
public class ProvisioningServiceProvider extends ClientInterfaceConnector {
    private UserManager umgr = null;
    private ProvisioningService provisioningService=null;
    public ProvisioningServiceProvider() throws Exception {
        super();
        umgr=this.getUserManager();
        provisioningService = _oimClient.getService(ProvisioningService.class);
        return;
    }

    @Override
    protected void execute() {
    }

    public void setProvisioningService(ProvisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }

    public ProvisioningService getProvisioningService() {
        return provisioningService;
    }
}
