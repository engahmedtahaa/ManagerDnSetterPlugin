package com.asset.oim.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Ahemd Taha
 */
public class ConfigReader {
    private static final Object[][] contents = { { "RR", "rr" } };

    public ConfigReader() {
        super();
    }

    public Properties read() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
           input= this.getClass().getClassLoader().getResourceAsStream(Defines.CONFIG_FILE_NAME);
            if (input != null) {
                prop.load(input);
                System.out.println("File Loaded");
            } else {
                input = new FileInputStream(Defines.CONFIG_FILE_NAME);
                if (input != null) {
                    prop.load(input);
                }else{
                    System.out.println("Property File Not Found");
                    } 
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;

    }

    public static void main(String[] args) {
        ConfigReader ConfigReader = new ConfigReader();
//        try {
//            ConfigReader.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
}
