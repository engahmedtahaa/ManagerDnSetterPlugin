package com.asset.oim.core.transform;

import java.util.HashMap;
import oracle.core.ojdl.logging.ODLLevel;
import oracle.core.ojdl.logging.ODLLogger;

/**
 * Reconciliation Transformation 
 * Uses manager name to set manager dn .
 * @author Ahmed Taha
 */

public class ReconciliationEventDataTransformer {
    private static final ODLLogger LOGGER = ODLLogger.getODLLogger(ReconciliationEventDataTransformer.class.getName());

    public ReconciliationEventDataTransformer() {
        super();
    }
    public Object transform(HashMap<String,Object> parentData, HashMap<String,Object> childData, String reconField)
       {
           LOGGER.log(ODLLevel.NOTIFICATION, "Parameters: Parent Data = {0}, Child Data = {1}, Field = {2}", new Object[]{parentData, childData, reconField});
           
           // Get values using the target data. Use reconciliation field name to fetch value.
           String firstName = (String) parentData.get("First Name");
           LOGGER.log(ODLLevel.NOTIFICATION, "First Name = {0}", new Object[]{firstName});
           String lastName = (String) parentData.get("Last Name");
           LOGGER.log(ODLLevel.NOTIFICATION, "Last Name = {0}", new Object[]{lastName});
           
           // Construct Display Name
           String displayName = firstName + "." + lastName;
           LOGGER.log(ODLLevel.NOTIFICATION, "Populating {1} with value = {0}", new Object[]{displayName, reconField});
           return displayName;
       }
}
