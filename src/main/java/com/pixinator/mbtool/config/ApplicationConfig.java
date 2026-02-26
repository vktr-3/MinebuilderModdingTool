package com.pixinator.mbtool.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfig {
    private static final String CONFIG_FILENAME = "application.properties";

    private static ApplicationConfig instance;

    private String modDirectory;

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = init();
        }
        return instance;
    }

    public static ApplicationConfig init() {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILENAME);
            props.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ApplicationConfig config = new ApplicationConfig();
        config.modDirectory = props.get("mod.directory").toString();

        return config;
    }

    public String getModDirectory() {
        return modDirectory;
    }
}
