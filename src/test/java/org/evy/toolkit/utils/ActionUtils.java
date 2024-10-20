package org.evy.toolkit.utils;

import org.evy.toolkit.pages.BasePage;

import java.util.concurrent.Callable;

/**
 * Utility class for executing actions on web elements with consistent logging.
 * <p>
 * Provides methods to perform actions using {@link Callable} and {@link Runnable},
 * logging success and error messages to aid in debugging and tracking element interactions.
 * utilized in the {@link BasePage} class for handling web element actions.
 * </p>
 */
public final class ActionUtils {


    private ActionUtils(){}


    public static <T>T performAction(Class<?>cls, Callable<T>callable,String successMsg,String errorMsg){
        try {
            T result=callable.call();
            LoggerUtils.info(cls,successMsg);
            return result;
        }catch (Exception e){
            LoggerUtils.error(cls,errorMsg,e);
            throw new RuntimeException(errorMsg,e);
        }
    }

    public static void performAction(Class<?>cls, Runnable runnable,String successMsg,String errorMsg){
        try {
            runnable.run();
            LoggerUtils.info(cls,successMsg);
        }catch (Exception e){
            LoggerUtils.error(cls,errorMsg,e);
            throw new RuntimeException(errorMsg,e);
        }
    }
}
