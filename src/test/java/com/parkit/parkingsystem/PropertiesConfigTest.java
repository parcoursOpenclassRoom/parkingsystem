package com.parkit.parkingsystem;


import com.parkit.parkingsystem.config.PropertiesConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PropertiesConfigTest {
    private static PropertiesConfig propertiesConfig;

    @Test
    public void getPropValuesWithFileFind() throws IOException {
        propertiesConfig = new PropertiesConfig();
        assertNotNull(propertiesConfig.getConnectionURL());
        assertNotNull(propertiesConfig.getPassword());
        assertNotNull(propertiesConfig.getUsername());
    }
}
