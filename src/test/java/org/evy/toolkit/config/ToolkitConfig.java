package org.evy.toolkit.config;

import org.aeonbits.owner.Config;
import org.evy.toolkit.drivers.BrowserType;

/**
 * Configuration interface for the application using the Owner library.
 */
@Config.Sources("file:${user.dir}/src/test/resources/config.properties")
public interface ToolkitConfig extends Config {

    @Key("browserType")
    @ConverterClass(BrowserTypeConverter.class)
    @DefaultValue("CHROME")
    BrowserType browserType();

    @Key("headlessMode")
    boolean headlessMode();

    @Key("baseUrl")
    String baseUrl();

    @Key("email")
    String email();

    @Key("password")
    String password();

    @Key("pageLoadTime")
    int pageLoadTime();

    @Key("implicitTime")
    int implicitTime();
}
