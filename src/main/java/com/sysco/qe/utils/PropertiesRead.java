package com.sysco.qe.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesRead {
    private static final Properties p = new Properties();
    static {
        try(InputStream is = PropertiesRead.class.getClassLoader().getResourceAsStream("locators.properties")){
            p.load(is);
        } catch (Exception e){
            throw new RuntimeException("Could not load locators.properties", e);
        }
    }

    public static String get(String key){
        String v = p.getProperty(key);
        if(v == null) throw new RuntimeException("Missing property:  "+key);
        return v;
    }
}
