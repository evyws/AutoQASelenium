package org.evy.toolkit.config;

import org.aeonbits.owner.ConfigCache;

/**
 * Manages configuration retrieval using the Owner library.
 */
public final class ConfigManager {

    private ConfigManager(){}

    /**
     * Retrieves the configuration instance for the application.
     *
     * @return the {@link ToolkitConfig} instance containing configuration values.
     */
    public static ToolkitConfig getConfig(){
        return ConfigCache.getOrCreate(ToolkitConfig.class);
    }
}
