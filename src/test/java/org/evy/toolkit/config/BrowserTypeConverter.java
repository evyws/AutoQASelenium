package org.evy.toolkit.config;

import org.aeonbits.owner.Converter;
import org.evy.toolkit.drivers.BrowserType;

import java.lang.reflect.Method;

/**
 * Converts a String representation of a browser type into the corresponding {@link BrowserType} enum.
 */
public class BrowserTypeConverter implements Converter<BrowserType> {

    @Override
    public BrowserType convert(Method method, String browserType) {
        try {
            return BrowserType.valueOf(browserType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid browser type: " + browserType);
        }
    }
}
