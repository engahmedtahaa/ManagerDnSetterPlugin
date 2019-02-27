package com.asset.oim.core.tasks;

public class UnitTest {
    public UnitTest() {
        super();
    }

    public static void main(String[] args) {
        UnitTest unitTest = new UnitTest();
        ManagerDNSettterScheduler ManagerDNSettterScheduler = new ManagerDNSettterScheduler();
        ManagerDNSettterScheduler.execute(null);
    }
}
