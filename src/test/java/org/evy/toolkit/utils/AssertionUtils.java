package org.evy.toolkit.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

/**
 * Utility class for performing assertions using AssertJ.
 * <p>
 * This class provides static methods to facilitate assertions in tests,
 * ensuring better readability and maintainability. It includes methods to
 * verify boolean conditions, check if a collection contains a specific value,
 * and assert equality between two objects.
 * </p>
 */
public final class AssertionUtils {

    private AssertionUtils() {}

    private static final Logger logger = LogManager.getLogger(AssertionUtils.class);

    public static void verifyTrue(boolean condition, String message) {
        try {
            LoggerUtils.info(AssertionUtils.class, String.format("Verifying condition is true: %s - Condition: %b", message, condition));
            Assertions.assertThat(condition)
                    .describedAs(message)
                    .isTrue();
            LoggerUtils.info(AssertionUtils.class, "Verification successful: Condition is true.");
        } catch (AssertionError e) {
            LoggerUtils.error(AssertionUtils.class, String.format("Verification failed: %s - Condition was false", message), null);
            throw e;
        }
    }

    public static void verifyContains(String actual, String expected, String message) {
        try {
            LoggerUtils.info(AssertionUtils.class, String.format("Verifying that the actual string contains the expected value. %s - Expected: '%s', Actual: '%s'", message, expected, actual));
            Assertions.assertThat(actual)
                    .as(String.format("%s - Expected to contain: '%s', but was: '%s'", message, expected, actual))
                    .contains(expected);
            LoggerUtils.info(AssertionUtils.class, "Verification successful: the actual string contains the expected value.");
        } catch (AssertionError e) {
            LoggerUtils.error(AssertionUtils.class, String.format("Verification failed: %s - Expected: '%s', but was: '%s'", message, expected, actual), null);
            throw e;
        }
    }

    public static void verifyEquals(Object actual, Object expected, String message) {
        try {
            LoggerUtils.info(AssertionUtils.class, String.format("Verifying equality. %s - Expected: '%s', Actual: '%s'", message, expected, actual));
            Assertions.assertThat(actual)
                    .as(message)
                    .isEqualTo(expected);
            LoggerUtils.info(AssertionUtils.class, "Verification successful: the actual object is equal to the expected object.");
        } catch (AssertionError e) {
            LoggerUtils.error(AssertionUtils.class, String.format("Verification failed: %s - Expected: '%s', but was: '%s'", message, expected, actual), null);
            throw e;
        }
    }
}
