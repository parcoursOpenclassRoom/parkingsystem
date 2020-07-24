package com.parkit.parkingsystem.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
    private String username;
    private String password;
    private String connectionURL;

    public PropertiesConfig() throws IOException {
        getPropValues();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    private void getPropValues() throws IOException {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out
            this.username = prop.getProperty("database.jdbc.username");
            this.password = prop.getProperty("database.jdbc.password");
            this.connectionURL = prop.getProperty("database.jdbc.connectionURL");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if(inputStream != null)
                inputStream.close();
        }
    }
}
