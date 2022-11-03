package com.oneself.common.utils;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @Description:
 * @Title: ProperitesUtils
 * @Author wen
 * @Date: 2022/7/30 15:47
 */
public class PropertiesUtil {


    private String propertiesName;

    private Properties properties;

    public PropertiesUtil() {

    }

    public PropertiesUtil(String fileName) {
        this.propertiesName = fileName;
        this.properties = getProperties();
    }


    public Properties getProperties() {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
                    propertiesName);
            p.load(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return p;
    }

    public static Properties getProperties(String fileName) {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
                    fileName);
            p.load(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return p;
    }


    public String getString(String key) {
        return this.properties.getProperty(key);
    }
    public String getString(String key,String defaultValue ) {
        String value = this.properties.getProperty(key);
        return Objects.isNull(value)?defaultValue:value;
    }

    public Integer getInteger(String key) {
        String value = this.properties.getProperty(key);
        return Objects.isNull(value)?null:Integer.valueOf(value);
    }

    public Integer getInteger(String key,int defaultValue) {
        String value = this.properties.getProperty(key);
        return Objects.isNull(value)?defaultValue:Integer.valueOf(value);
    }

    public Long getLong(String key) {
        String value = this.properties.getProperty(key);
        return Objects.isNull(value)?null:Long.valueOf(value);
    }
    public Long getLong(String key,long defaultValue) {
        String value = this.properties.getProperty(key);
        return Objects.isNull(value)?defaultValue:Long.valueOf(value);
    }

    public Double getDouble(String key) {
        String value = this.properties.getProperty(key);
        return Objects.isNull(value)?null:Double.valueOf(value);
    }

    public Double getDouble(String key,double defaultValue) {
        String value = this.properties.getProperty(key);
        return Objects.isNull(value)?defaultValue:Double.valueOf(value);
    }



}
